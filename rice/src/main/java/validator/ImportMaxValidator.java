package validator;

import javax.validation.constraints.Max;
import org.apache.commons.lang3.StringUtils;

/**
 * Date: 2023/1/15
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ImportMaxValidator extends AbstractImportValidator {


    @Override
    public Class<?> getValidatorClass() {
        return Max.class;
    }

    @Override
    public boolean isNumberValidator() {
        return true;
    }


    @Override
    public void doParsingValidate(String fieldName, Object cellValue, String cellHeadName) {
        Number number = (Number) cellValue;
        Max max = (Max) getThreadLocalValidatorClass(fieldName);
        if (number != null && number.doubleValue() > max.value()) {
            String errorMsg =StringUtils.isBlank(cellHeadName) ? String.format("第%d列值不能超过%d", getCurrentCellNumber(), max.value()) :
                            String.format("列[%s]不能超过%d", cellHeadName, max.value());
            getImportRowErrorVO().appendErrorMsg(errorMsg);
            EpImportBeanValidateContext.markCurrentRowError();

        }

    }


}