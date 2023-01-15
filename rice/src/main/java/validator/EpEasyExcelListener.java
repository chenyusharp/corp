package validator;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

/**
 * Date: 2023/1/15
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class EpEasyExcelListener extends AnalysisEventListener {

    @Override
    public void invoke(Object object, AnalysisContext context) {
        EpImportResult epImportResult = (EpImportResult) EpImportBeanValidateContext.get(
                EpImportBeanValidateContext.IMPORT_RESULT);
        boolean currentCellHasError = (boolean) EpImportBeanValidateContext.get(
                EpImportBeanValidateContext.CURRENT_CELL_HAS_ERROR);
        if (!currentCellHasError && !EpImportBeanValidateContext.getReachedEndRow()) {
            epImportResult.getValidBeanList().add(object);
        }

        if (EpImportBeanValidateContext.getReachedEndRow()) {
            epImportResult.getErrorVOMap().remove(EpImportBeanValidateContext.getCurrentCellIdx());
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}