package com.xiazhenyu.validator;

import java.lang.annotation.Annotation;

/**
 * Date: 2023/1/14
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface ImportValidator {


    Annotation getThreadLocalValidatorClass(String fieldName);

    void setThreadLocalValidatorClass(String fieldName, Annotation annotation);

    void clearThreadLocal();

    Class<?> getValidatorClass();

    void doParsingValidate(String fieldName, Object cellValue, String cellHeadName);


    default boolean isNumberValidator() {
        return false;
    }


    default int getCurrentRowNumber() {
        return EpImportBeanValidateContext.getCurrentRowIdx();
    }

    default int getCurrentCellNumber() {
        return EpImportBeanValidateContext.getCurrentCellIdx() + 1;
    }


    default ImportRowErrorVO getImportRowErrorVO() {
        final EpImportResult importResult = (EpImportResult) EpImportBeanValidateContext.get(
                EpImportBeanValidateContext.IMPORT_RESULT);
        ImportRowErrorVO rowErrorVO = null;
        final int currentRowNumber = getCurrentRowNumber();
        if ((rowErrorVO = (ImportRowErrorVO) importResult.getErrorVOMap().get(currentRowNumber)) == null) {
            rowErrorVO = new ImportRowErrorVO(currentRowNumber);
            importResult.getErrorVOMap().put(getCurrentCellNumber(), rowErrorVO);
        }
        return rowErrorVO;
    }


}
