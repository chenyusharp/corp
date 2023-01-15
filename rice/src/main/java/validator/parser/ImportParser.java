package validator.parser;

import java.io.IOException;
import java.io.InputStream;
import validator.EpImportResult;

/**
 * Date: 2023/1/15
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface ImportParser {


    String getExtention();


    <T> EpImportResult<T> parse(Class<T> ruleBeanClass, InputStream inputStream) throws IOException;

}
