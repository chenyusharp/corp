package validator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import validator.parser.ImportParser;

/**
 * Date: 2023/1/15
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@UtilityClass
@Slf4j
public class ImportParserFactory {

    public static final Map<String, ImportParser> cachedCantainer = new ConcurrentHashMap<>();


    public static ImportParser getSingletonInstance(String extention) {
        try {
            if (cachedCantainer.isEmpty()) {
                synchronized (ImportParserFactory.class) {
                    if (cachedCantainer.isEmpty()) {
                        final Reflections reflections = new Reflections("validator.parser");
                        for (Class<? extends ImportParser> clazz : reflections.getSubTypesOf(ImportParser.class)) {
                            final ImportParser importParser = clazz.newInstance();
                            cachedCantainer.put(importParser.getExtention(), importParser);
                        }
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return cachedCantainer.get(extention);
    }


}