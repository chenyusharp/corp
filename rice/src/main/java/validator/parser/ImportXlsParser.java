package validator.parser;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import validator.EpEasyExcelListener;
import validator.EpImportBeanValidateContext;
import validator.EpImportResult;

/**
 * Date: 2023/1/15
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ImportXlsParser implements ImportParser {

    @Override
    public String getExtention() {
        return ".xls";
    }

    @Override
    public <T> EpImportResult<T> parse(Class<T> ruleBeanClass, InputStream inputStream) throws IOException {
        EpEasyExcelListener excelListener = new EpEasyExcelListener();
        int sheetNumber = (int) EpImportBeanValidateContext.get(EpImportBeanValidateContext.SHEET_NUMBER);
        int titleRowIdx = (int) EpImportBeanValidateContext.get(EpImportBeanValidateContext.TITLE_ROW_IDX);
        EasyExcelFactory.readBySax(new BufferedInputStream(inputStream), new Sheet(sheetNumber, titleRowIdx,
                (Class<? extends BaseRowModel>) ruleBeanClass), excelListener);
        return (EpImportResult<T>) EpImportBeanValidateContext.get(EpImportBeanValidateContext.IMPORT_RESULT);
    }
}