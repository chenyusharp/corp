package validator;

import com.univocity.parsers.csv.CsvParser;
import java.beans.beancontext.BeanContext;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import validator.EpImportBeanRule.EpImportEndRowIdxRule;

/**
 * Date: 2023/1/14
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class EpImportBeanValidateContext {

    public static final String BEAN_RULE_MAP = "beanRuleMap";

    public static final String CELL_IDX_FIELD_MAP = "cellIdxFieldMap";

    public static final String TITLE_ROW_IDX = "EpImportBeanRule#titleRowIdx()";

    public static final String END_ROW_BY_CELL_REGX = "EpImportBeanRule#endRowByCellRegx()";

    public static final String AUTO_RELEASE_BEAN_LIST = "EpImportBeanRule#autoReleaseBeanListWhenHasValidationErrors()";

    public static final String IMPORT_RESULT = "epImportResult";

    public static final String SHEET_NUMBER = "sheetNumber";

    public static final String CURRENT_ROW = "currentRow";

    public static final String CURRENT_CELL = "currentCell";

    public static final String CURRENT_CELL_HEAD_NAME = "currentCellHeadName";

    public static final String CURRENT_CELL_HAS_ERROR = "currentCellHasError";

    public static final String IS_REACHED_END_ROW = "isReachEndRow";

    public static final String REACHED_END_REGX_PATTERN = "reachedEndPattern";

    public static final String CSV_PARSER = "csvParser";

    public static final String EP_ASYNC_TASK_KEY = "epAsyncTaskKey";

    private static ThreadLocal<Map<String, Object>> internMapThreadLocal = new ThreadLocal<>();

    public static void iniParams() {
        put(CURRENT_CELL_HAS_ERROR, false);
        put(IS_REACHED_END_ROW, false);
        put(REACHED_END_REGX_PATTERN, null);
    }


    public static void setEpAsyncTaskKey(String asyncTaskKey) {
        put(EP_ASYNC_TASK_KEY, asyncTaskKey);
    }


    public static String getEpAsyncTaskKey() {
        return (String) get(EP_ASYNC_TASK_KEY);
    }

    public static void markCurrentRowError() {
        put(CURRENT_CELL_HAS_ERROR, true);
    }

    public static void markCurrentRowOk() {
        put(CURRENT_CELL_HAS_ERROR, false);
    }


    public static void setCurrentRowIdx(int currentRow) {
        put(CURRENT_ROW, currentRow);
    }


    public static int getCurrentRowIdx() {
        return get(CURRENT_ROW) == null ? 0 : get(CURRENT_ROW, int.class);
    }


    public static int getCurrentCellIdx() {
        return get(CURRENT_CELL, int.class);
    }


    public static void setReachedEndRow(boolean isReachEndRow) {
        put(IS_REACHED_END_ROW, isReachEndRow);
    }

    public static boolean getReachedEndRow() {
        return get(IS_REACHED_END_ROW, boolean.class);
    }

    public static EpImportResult getEpImportResult() {
        return get(IMPORT_RESULT, EpImportResult.class);
    }

    public static EpImportBeanRule.EpImportEndRowIdxRule getEndRowIdxRule() {
        return (EpImportEndRowIdxRule) internMapThreadLocal.get().get(END_ROW_BY_CELL_REGX);
    }


    public static Pattern getEndRowIdxRulePattern() {
        Pattern pattern = null;
        if ((pattern = (Pattern) internMapThreadLocal.get().get(REACHED_END_REGX_PATTERN)) == null) {
            final EpImportEndRowIdxRule idxRule = getEndRowIdxRule();
            if (idxRule.cellIdx() != -1 && StringUtils.isNotBlank(idxRule.regExpression())) {
                pattern = Pattern.compile(idxRule.regExpression());
            }
        }
        return pattern;
    }


    public static void setCsvParser(CsvParser csvParser) {
        put(CSV_PARSER, csvParser);
    }


    public CsvParser getCsvParse() {
        return get(CSV_PARSER, CsvParser.class);
    }


    public static void setCurrentCellHeadName(String headName) {
        put(CURRENT_CELL_HEAD_NAME, headName);
    }

    public static String getCurrentCellHeadName() {
        return get(CURRENT_CELL_HEAD_NAME, String.class);
    }


    public static void setCellIdxFieldNameMapping(Integer cellIdx, String headName) {
        Map<Integer, String> cellIdxFieldMap = (Map<Integer, String>) get(CELL_IDX_FIELD_MAP);
        if (cellIdxFieldMap == null) {
            cellIdxFieldMap = new HashMap<>();
            put(CELL_IDX_FIELD_MAP, cellIdxFieldMap);
        }
        cellIdxFieldMap.put(cellIdx, headName);
    }


    public static String getFieldNameByCellIdx(int cellIdx) {
        Map<Integer, String> cellIdxFieldMap = (Map<Integer, String>) get(CELL_IDX_FIELD_MAP);
        return cellIdxFieldMap != null ? cellIdxFieldMap.get(cellIdx) : null;
    }


    public static void put(String key, Object object) {
        if (internMapThreadLocal.get() == null) {
            internMapThreadLocal.set(new HashMap<>());
            iniParams();
        }
        internMapThreadLocal.get().put(key, object);
    }


    public static Object get(String key) {
        return internMapThreadLocal.get() == null ? null : internMapThreadLocal.get().get(key);
    }


    public static <T> T get(String key, Class<T> tClass) {
        if (tClass == null) {
            throw new IllegalArgumentException("tClass is null");
        }
        return null == internMapThreadLocal.get() ? null : (T) internMapThreadLocal.get().get(key);
    }


    public static void clearContext() {
        if (internMapThreadLocal.get() != null && internMapThreadLocal.get().get(BEAN_RULE_MAP) != null) {
            cleaRuleMap();
        }
        internMapThreadLocal.remove();
    }


    public static void cleaRuleMap() {
        Map<String, Map<Class, ImportValidator>> beanRuleMap = (Map<String, Map<Class, ImportValidator>>) internMapThreadLocal.get()
                .get(BEAN_RULE_MAP);

        if (beanRuleMap != null && !beanRuleMap.isEmpty()) {
            for (Map<Class, ImportValidator> validatorMap : beanRuleMap.values()) {
                if (validatorMap != null && !validatorMap.isEmpty()) {
                    for (ImportValidator validator : validatorMap.values()) {
                        validator.clearThreadLocal();
                    }
                }
            }
        }
    }


}