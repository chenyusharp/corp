package com.xzy.sso.core.path;

import java.util.Comparator;
import java.util.Map;

/**
 * Date: 2023/8/1
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface PathMatcher {


    boolean isPattern(String path);

    boolean match(String pattern, String path);

    boolean matchStart(String pattern, String path);

    String extractPathWithinPattern(String pattern, String path);

    Map<String, String> extarctUriTemplateVariables(String pattern, String path);

    Comparator<String> getPatternComparator(String path);

    String combine(String pattern1, String pattern2);


}
