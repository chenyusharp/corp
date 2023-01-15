package validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Date: 2023/1/14
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EpImportBeanRule {

    int sheetNumber() default 1;

    int titleRowIdx() default 1;

    EpImportEndRowIdxRule endRowByCellRegx() default @EpImportEndRowIdxRule;


    boolean autoReleaBeanListWhenHasValidationErrors() default true;


    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface EpImportEndRowIdxRule {

        int cellIdx() default -1;

        boolean allowBlank() default false;

        String regExpression() default "";


    }


}