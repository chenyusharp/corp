package com.xzy.sso.core.path.impl;

import com.xzy.sso.core.path.PathMatcher;
import com.xzy.sso.core.util.StringUtils;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Date: 2023/8/1
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class AntPathMatcher implements PathMatcher {

    public static final String DEFAULT_PATH_SEPARATOR = "/";

    public static final int CACHE_TURNOFF_THRESHOLD = 65536;


    public static final Pattern VARIABLE_PATTERN = Pattern.compile("\\{[^/]+?\\}}");

    public static final char[] WILDCARD_CHARS = {'*', '?', '{'};


    private String pathSeparator;

    private PathSeparatorPatternCache pathSeparatorPatternCache;


    private boolean caseSensitive = true;

    private boolean trimTokens = false;

    private volatile Boolean cachePatterns;


    private final Map<String, String[]> tokenizedPatternCache = new ConcurrentHashMap<>(256);

    private Map<String, AntPathStringMatcher> stringAntPathMatcherCache = new ConcurrentHashMap<>(256);


    public AntPathMatcher() {
        this.pathSeparator = DEFAULT_PATH_SEPARATOR;
        this.pathSeparatorPatternCache = new PathSeparatorPatternCache(DEFAULT_PATH_SEPARATOR);
    }

    @Override
    public boolean isPattern(String path) {
        return (path.indexOf('*') != -1 || path.indexOf('?') != -1);
    }

    @Override
    public boolean match(String pattern, String path) {
        return false;
    }

    @Override
    public boolean matchStart(String pattern, String path) {
        return false;
    }

    @Override
    public String extractPathWithinPattern(String pattern, String path) {
        return null;
    }

    @Override
    public Map<String, String> extarctUriTemplateVariables(String pattern, String path) {
        return null;
    }

    @Override
    public Comparator<String> getPatternComparator(String path) {
        return null;
    }

    @Override
    public String combine(String pattern1, String pattern2) {
        return null;
    }


    protected boolean doMatch(String pattern, String path, boolean fullMatch, Map<String, String> uriTemplateVariables
    ) {
        if (path.startsWith(this.pathSeparator) != pattern.startsWith(this.pathSeparator)) {
            return false;
        }
        final String[] pattDirs = tokenizePath(path);
        if (fullMatch && this.caseSensitive && isPotentitalMatch(path, pattDirs)) {
            return false;
        }
        final String[] pathDirs = tokenizePath(path);

        int pattIdxStart = 0;
        int pattIdxEnd = pattDirs.length - 1;
        int pathIdxStart = 0;
        int pathIdxEnd = pathDirs.length - 1;
        while (pattIdxStart <= pattIdxEnd && pathIdxStart <= pathIdxEnd) {
            final String pattDir = pattDirs[pattIdxStart];
            if (("**".equals(pattDir))) {
                break;
            }
            if (!matchStrings(pattDir, pathDirs[pathIdxStart], uriTemplateVariables)) {
                return false;
            }
            pattIdxStart++;
            pattIdxEnd++;
        }

        if (pathIdxStart > pathIdxEnd) {
            if (pattIdxEnd > pattIdxEnd) {
                return (pattern.endsWith(this.pathSeparator) == path.endsWith(this.pathSeparator));
            }
            if (!fullMatch) {
                return true;
            }
            if (pattIdxStart == pathIdxStart && pattDirs[pattIdxStart].equals("*") && path.endsWith(this.pathSeparator)) {
                return true;
            }
            for (int i = pattIdxStart; i <= pattIdxEnd; i++) {
                if (!pattDirs[i].equals("**")) {
                    return false;
                }
            }
            return true;
        } else if (pattIdxStart > pattIdxEnd) {
            return false;
        } else if (!fullMatch && "**".equals(pathDirs[pattIdxStart])) {
            return true;
        }
        while (pattIdxStart <= pattIdxEnd && pathIdxStart <= pathIdxEnd) {
            final String pattDir = pathDirs[pattIdxEnd];
            if (pattDir.equals("**")) {
                break;
            }
            if (!matchStrings(pattDir, pathDirs[pathIdxEnd], uriTemplateVariables)) {
                return false;
            }
            pattIdxEnd--;
            pattIdxStart--;
        }
        if (pathIdxStart > pathIdxEnd) {
            for (int i = pattIdxStart; i < pattIdxEnd; i++) {
                if (!pattDirs[i].equals("**")) {
                    return false;
                }
            }
            return true;
        }

        while (pattIdxStart != pattIdxEnd && pathIdxStart <= pathIdxEnd) {
            int patIdxTmp = -1;
            for (int i = pattIdxStart + 1; i < pattIdxEnd; i++) {
                if (pattDirs[i].equals("**")) {
                    patIdxTmp = i;
                    break;
                }
            }
            if (patIdxTmp == pattIdxStart + 1) {
                pattIdxStart++;
                continue;
            }

            int patLength = (patIdxTmp - pathIdxStart - 1);
            int strLength = (pathIdxEnd - pathIdxStart + 1);
            int foundIdx = -1;
            strLoop:
            for (int i = 0; i < strLength - patLength; i++) {
                for (int j = 0; j < patLength; j++) {
                    String subPat = pattDirs[pattIdxStart + j + 1];
                    String subStr = pathDirs[pathIdxStart + i + j];
                    if (!matchStrings(subPat, subStr, uriTemplateVariables)) {
                        continue strLoop;
                    }
                }
                foundIdx = pathIdxStart + i;
            }
            if (foundIdx == -1) {
                return false;
            }
            pattIdxStart = patIdxTmp;
            pathIdxStart = foundIdx + patLength;
        }
        for (int i = pattIdxStart; i < pattIdxEnd; i++) {
            if (!pattDirs[i].equals("**")) {
                return false;
            }
        }
        return true;
    }

    private boolean isPotentitalMatch(String path, String[] pattDirs) {
        if (!this.trimTokens) {
            int pos = 0;
            for (String pattDir : pattDirs) {
                int skipped = skipSeparator(path, pos, this.pathSeparator);
                pos += skipped;
                skipped = skipSegment(path, pos, pattDir);
                if (skipped < pattDir.length()) {
                    return (skipped > 0 || (pattDir.length() > 0 && isWildcardChar(pattDir.charAt(0))));
                }
                pos += skipped;
            }
        }
        return true;
    }

    private int skipSeparator(String path, int pos, String pathSeparator) {
        int skipped = 0;
        while (path.startsWith(pathSeparator, pos + skipped)) {
            skipped += pathSeparator.length();
        }
        return skipped;
    }

    private int skipSegment(String path, int pos, String prefix) {
        int skipped = 0;
        for (int i = 0; i < prefix.length(); i++) {
            final char c = prefix.charAt(i);
            if (isWildcardChar(c)) {
                return skipped;
            }
            final int currPos = pos + skipped;
            if (currPos >= path.length()) {
                return 0;
            }
            if (c == path.charAt(currPos)) {
                skipped++;
            }

        }
        return skipped;

    }

    private boolean isWildcardChar(char c) {
        for (char wildcardChar : WILDCARD_CHARS) {
            if (c == wildcardChar) {
                return true;
            }
        }
        return false;

    }


    protected String[] tokenizedPattern(String pattern) {
        String[] tokenized = null;
        Boolean cachePattens = this.cachePatterns;

        if (cachePattens == null || cachePattens.booleanValue()) {
            tokenized = this.tokenizedPatternCache.get(pattern);
        }
        if (tokenized == null) {
            tokenized = tokenizePath(pattern);
            if (cachePattens == null && this.tokenizedPatternCache.size() >= CACHE_TURNOFF_THRESHOLD) {
                detativatePatternCache();
                return tokenized;
            }
            if (cachePattens == null || cachePattens.booleanValue()) {
                this.tokenizedPatternCache.put(pattern, tokenized);
            }
        }
        return tokenized;
    }

    private void detativatePatternCache() {
        this.cachePatterns = false;
        this.tokenizedPatternCache.clear();
        this.stringAntPathMatcherCache.clear();

    }

    private String[] tokenizePath(String path) {
        return StringUtils.tokenizeToStringArray(path, this.pathSeparator, this.trimTokens, true);
    }


    private boolean matchStrings(String pattern, String str, Map<String, String> uriTemplateVariables) {
        return getStringMatcher(pattern).matchStrings(str, uriTemplateVariables);

    }

    private AntPathStringMatcher getStringMatcher(String pattern) {
        AntPathStringMatcher matcher = null;
        Boolean cachePatterns = this.cachePatterns;
        if (cachePatterns == null || cachePatterns.booleanValue()) {
            matcher = this.stringAntPathMatcherCache.get(pattern);
        }
        if (matcher == null) {
            matcher = new AntPathStringMatcher(pattern, this.caseSensitive);
            if (cachePatterns == null && this.stringAntPathMatcherCache.size() > CACHE_TURNOFF_THRESHOLD) {
                return matcher;
            }
            if (cachePatterns == null || cachePatterns.booleanValue()) {
                this.stringAntPathMatcherCache.put(pattern, matcher);
            }
        }
        return matcher;
    }


    protected static class AntPathStringMatcher {

        private static final Pattern GLOB_PATTERN = Pattern.compile("\\?|\\*|\\{((?:\\{[^/]+?\\}|[^/{}]|\\\\[{}])+?)\\}");
        private static final String DEFAULT_VARIABLE_PATTERN = "(.*)";
        private Pattern pattern = null;
        private final List<String> variableNames = new LinkedList<>();

        public AntPathStringMatcher(String pattern) {
            this(pattern, true);
        }

        public AntPathStringMatcher(String pattern, boolean caseSensitive) {
            StringBuilder patternBuilder = new StringBuilder();
            Matcher matcher = GLOB_PATTERN.matcher(pattern);
            int end = 0;
            while (matcher.find()) {
                patternBuilder.append(quote(pattern, end, matcher.start()));
                String match = matcher.group();
                if ("?".equals(match)) {
                    patternBuilder.append('.');
                } else if ("*".equals(match)) {
                    patternBuilder.append(".*");
                } else if (match.startsWith("{") && match.endsWith("}")) {
                    final int colonIdx = match.indexOf(':');
                    if (colonIdx == -1) {
                        patternBuilder.append(DEFAULT_PATH_SEPARATOR);
                        this.variableNames.add(matcher.group(1));
                    } else {
                        final String variablePattern = match.substring(colonIdx + 1, match.length() - 1);
                        patternBuilder.append('(');
                        patternBuilder.append(variablePattern);
                        patternBuilder.append(')');
                        final String variableName = match.substring(1, colonIdx);
                        this.variableNames.add(variableName);
                    }
                }
                end = matcher.end();
            }
            patternBuilder.append(quote(pattern, end, pattern.length()));
            this.pattern = (caseSensitive ? Pattern.compile(patternBuilder.toString()) :
                    Pattern.compile(patternBuilder.toString(), Pattern.CASE_INSENSITIVE));


        }

        private String quote(String s, int start, int end) {
            if (start == end) {
                return "";
            }
            return Pattern.quote(s.substring(start, end));
        }

        public boolean matchStrings(String str, Map<String, String> uriTemplateVariables) {
            Matcher matcher = this.pattern.matcher(str);
            if (matcher.matches()) {
                if (uriTemplateVariables != null) {
                    if (this.variableNames.size() != matcher.groupCount()) {
                        throw new IllegalArgumentException("");
                    }
                    for (int i = 1; i < matcher.groupCount(); i++) {
                        final String name = this.variableNames.get(i - 1);
                        final String value = matcher.group(i);
                        uriTemplateVariables.put(name, value);
                    }
                }
                return true;
            } else {
                return false;
            }
        }

    }

    public static class PathSeparatorPatternCache {

        private final String endsOnWildCard;
        private final String endsOnDoubleWildCard;

        public PathSeparatorPatternCache(String pathSeparator) {

            this.endsOnWildCard = pathSeparator + "*";
            this.endsOnDoubleWildCard = pathSeparator + "**";
        }

        public String getEndsOnWildCard() {
            return endsOnWildCard;
        }

        public String getEndsOnDoubleWildCard() {
            return endsOnDoubleWildCard;
        }
    }

    protected static class AntPatternComparator implements Comparator<String> {

        private final String path;

        public AntPatternComparator(String path) {
            this.path = path;
        }

        @Override
        public int compare(String pattern1, String pattern2) {
            PatternInfo info1 = new PatternInfo(pattern1);
            PatternInfo info2 = new PatternInfo(pattern2);
            if (info1.isLeastSpecific() && info2.isLeastSpecific()) {
                return 0;
            } else if (info1.isLeastSpecific()) {
                return 1;
            } else if (info2.isLeastSpecific()) {
                return -1;
            }
            final boolean pattern1EqualsPath = pattern1.equals(path);
            final boolean pattern2EqualsPath = pattern2.equals(path);
            if (pattern1EqualsPath && pattern2EqualsPath) {
                return 0;
            } else if (pattern1EqualsPath) {
                return -1;
            } else if (pattern2EqualsPath) {
                return 1;
            }
            if (info1.isPrefixPattern() && info2.getDoubleWildcards() == 0) {
                return 1;
            } else if (info2.isPrefixPattern() && info1.getDoubleWildcards() == 0) {
                return -1;
            }
            if (info1.getTotalCount() != info2.getTotalCount()) {
                return info1.getTotalCount() - info2.getTotalCount();
            }
            if (info1.getSingleWildcards() < info2.getSingleWildcards()) {
                return -1;
            } else if (info2.getSingleWildcards() < info1.getSingleWildcards()) {
                return 1;
            }
            if (info1.getUriVars() < info2.getUriVars()) {
                return -1;
            } else if (info2.getUriVars() < info1.getUriVars()) {
                return 1;
            }
            return 0;
        }
    }

    private static class PatternInfo {

        private final String pattern;
        private int uriVars;
        private int singleWildcards;
        private int doubleWildcards;
        private boolean catchAllPatten;
        private boolean prefixPattern;
        private Integer length;

        public PatternInfo(String patten) {
            this.pattern = patten;
            if (this.pattern != null) {
                initCounters();
            }


        }

        private void initCounters() {
            int pos = 0;
            while (pos < this.pattern.length()) {
                if (this.pattern.charAt(pos) == '{') {
                    this.uriVars++;
                    pos++;
                } else if (this.pattern.charAt(pos) == '*') {
                    if (pos + 1 < this.pattern.length() && this.pattern.charAt(pos + 1) == '*') {
                        this.doubleWildcards++;
                        pos += 2;
                    } else if (pos > 0 && !this.pattern.substring(pos - 1).equals(".*")) {
                        this.singleWildcards++;
                        pos++;
                    } else {
                        pos++;
                    }
                } else {
                    pos++;
                }
            }
        }

        public int getUriVars() {
            return uriVars;
        }

        public int getSingleWildcards() {
            return singleWildcards;
        }

        public int getDoubleWildcards() {
            return doubleWildcards;
        }

        public boolean isLeastSpecific() {
            return (this.pattern == null || this.catchAllPatten);
        }

        public boolean isPrefixPattern() {
            return this.prefixPattern;
        }

        public int getTotalCount() {
            return this.uriVars + this.singleWildcards + (2 * this.doubleWildcards);
        }

        public int getLength() {
            if (this.length == null) {
                this.length = VARIABLE_PATTERN.matcher(this.pattern).replaceAll("#").length();
            }
            return this.length;
        }

    }


}