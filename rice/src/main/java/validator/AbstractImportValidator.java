package validator;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2023/1/14
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public abstract class AbstractImportValidator implements ImportValidator {


    public final ThreadLocal<Map<String, Annotation>> annotationThreadLocal = new ThreadLocal<>();


    public void setThreadLocalValidatorClass(String fieldName, Annotation annotation) {
        if (annotationThreadLocal.get() == null) {
            annotationThreadLocal.set(new HashMap<>());
        }
        annotationThreadLocal.get().put(fieldName, annotation);
    }

    @Override
    public Annotation getThreadLocalValidatorClass(String fieldName) {
        return annotationThreadLocal.get().get(fieldName);
    }


    @Override
    public void clearThreadLocal() {
        if (annotationThreadLocal.get() != null) {
            annotationThreadLocal.get().clear();
        }
        annotationThreadLocal.remove();
    }

    @Override
    public Class<?> getValidatorClass() {
        return null;
    }

    @Override
    public void doParsingValidate(String fieldName, Object cellValue, String cellHeadName) {
        // do nothing
    }
}