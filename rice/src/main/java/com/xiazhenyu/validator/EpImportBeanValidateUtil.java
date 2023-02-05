package com.xiazhenyu.validator;

import com.univocity.parsers.annotations.Parsed;
import com.xiazhenyu.validator.parser.ImportParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.experimental.UtilityClass;
import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Date: 2023/1/15
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@UtilityClass
public class EpImportBeanValidateUtil {

    public static final String INIT_PARSING_AYNC_PROCESS_MESSAGE = "文件数据检验中，请稍后";

    public static final List<String> SUPPORT_FILES = Lists.newArrayList(".xls", ".csv", ".xlsx");

    private static final List<Class<?>> SUPPORT_VALID_ANNOTATIONS = Lists.newArrayList(Min.class, Max.class, Size.class,
            NotNull.class,
            NotBlank.class);

    public static <T> EpImportResult<T> validate(Class<T> ruleBeanClass, MultipartFile multipartFile) {
        try {
            return validate(ruleBeanClass, multipartFile.getOriginalFilename(), multipartFile.getInputStream(), null);
        } catch (Exception e) {
            // do nothing
        }
        return null;
    }

    public static <T> EpImportResult<T> validate(Class<T> ruleBeanClass, File file) {
        try {
            return validate(ruleBeanClass, file.getName(), new FileInputStream(file), null);
        } catch (Exception e) {
            //do nothing;
        }
        return null;
    }


    public static <T> EpImportResult<T> validate(Class<T> ruleBeanClass, File file, String epAysncTaskKey) {
        try {
            return validate(ruleBeanClass, file.getName(), new FileInputStream(file), epAysncTaskKey);
        } catch (Exception e) {
            //do nothing;
        }
        return null;
    }


    private static <T> EpImportResult<T> validate(Class<T> ruleBeanClass, String fieldName, InputStream inputStream,
            String epAyncTaskKey) {
        try {
            checkFileExtention(fieldName);
            extractBeanRuleToContext(ruleBeanClass);
            EpImportResult<T> importResult = new EpImportResult<>();
            EpImportBeanValidateContext.put(EpImportBeanValidateContext.IMPORT_RESULT, importResult);
            EpImportBeanValidateContext.setEpAsyncTaskKey(epAyncTaskKey);
            final ImportParser parser = ImportParserFactory.getSingletonInstance(extractFileExtention(fieldName));
            final EpImportResult<T> result = parser.parse(ruleBeanClass, inputStream);
            doAfterPassed(importResult);
            return importResult;
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        } finally {
            EpImportBeanValidateContext.clearContext();
        }
    }

    private static <T> void doAfterPassed(EpImportResult<T> importResult) {
        if ((Boolean) EpImportBeanValidateContext.get(EpImportBeanValidateContext.AUTO_RELEASE_BEAN_LIST)
                && !importResult.isPassed()
                && !CollectionUtils.isEmpty(importResult.getValidBeanList())) {
            importResult.setValidBeanList(Lists.newArrayList());
        }

        if (!StringUtils.isEmpty(EpImportBeanValidateContext.getEpAsyncTaskKey())) {
            final String asyncTaskKey = EpImportBeanValidateContext.getEpAsyncTaskKey();
            //把错误放入通用线程池的上下文中
        }
    }

    private static void checkFileExtention(String fieldName) {
        if (!SUPPORT_FILES.contains(extractFileExtention(fieldName))) {
            throw new RuntimeException("上传文件暂时支持.csv,.xls,.xlsx");
        }

    }

    private static String extractFileExtention(String fileFullName) {
        return fileFullName.substring(fileFullName.lastIndexOf('.'));
    }


    private void extractBeanRuleToContext(Class ruleBeanClass) {
        Map<String, Map<Class, ImportValidator>> fieldValidatorMap = new HashMap<>();
        EpImportBeanValidateContext.put(EpImportBeanValidateContext.BEAN_RULE_MAP, fieldValidatorMap);
        for (Field field : ruleBeanClass.getDeclaredFields()) {
            if (fieldValidatorMap.get(field.getName()) == null) {
                fieldValidatorMap.put(field.getName(), new HashMap<>());
            }
            final Map<Class, ImportValidator> validatorMap = fieldValidatorMap.get(field.getName());
            for (Annotation annotation : field.getAnnotations()) {
                for (Class<?> aClass : SUPPORT_VALID_ANNOTATIONS) {
                    if (aClass.isAssignableFrom(annotation.getClass())) {
                        final ImportValidator validator = ImportValidatorFactory.getSingletonByClass(aClass);
                        validatorMap.put(aClass, validator);
                        validator.setThreadLocalValidatorClass(field.getName(), annotation);
                    }
                }
                if (Parsed.class.isAssignableFrom(annotation.getClass())) {
                    Parsed parsed = (Parsed) annotation;
                    EpImportBeanValidateContext.setCellIdxFieldNameMapping(parsed.index(), field.getName());
                }
            }
        }
        initEpImportBeanRule(ruleBeanClass);
    }

    private static void initEpImportBeanRule(Class ruleBeanClass) {
        if (ruleBeanClass.isAnnotationPresent(EpImportBeanRule.class)) {
            EpImportBeanRule annotation = (EpImportBeanRule) ruleBeanClass.getAnnotation(EpImportBeanRule.class);
            EpImportBeanValidateContext.put(EpImportBeanValidateContext.TITLE_ROW_IDX, annotation.titleRowIdx());
            EpImportBeanValidateContext.put(EpImportBeanValidateContext.AUTO_RELEASE_BEAN_LIST,
                    annotation.autoReleaBeanListWhenHasValidationErrors());
            EpImportBeanValidateContext.put(EpImportBeanValidateContext.END_ROW_BY_CELL_REGX, annotation.endRowByCellRegx());
            EpImportBeanValidateContext.put(EpImportBeanValidateContext.SHEET_NUMBER, annotation.sheetNumber());
        } else {
            EpImportBeanValidateContext.put(EpImportBeanValidateContext.TITLE_ROW_IDX, 1);
            EpImportBeanValidateContext.put(EpImportBeanValidateContext.AUTO_RELEASE_BEAN_LIST, true);
            EpImportBeanValidateContext.put(EpImportBeanValidateContext.SHEET_NUMBER, 1);
        }
    }


}