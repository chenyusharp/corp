package com.xiazhenyu.validator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

/**
 * Date: 2023/1/15
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@UtilityClass
@Slf4j
public class ImportValidatorFactory {

    private static final Map<Class<?>, ImportValidator> cachedContainor = new ConcurrentHashMap<>();

    public static ImportValidator getSingletonByClass(Class<?> tClass) {
        try {
            if (cachedContainor.isEmpty()) {
                synchronized (ImportValidatorFactory.class) {
                    if (cachedContainor.isEmpty()) {
                        Reflections reflections = new Reflections("com/xiazhenyu/validator");
                        for (Class<? extends ImportValidator> clazz : reflections.getSubTypesOf(ImportValidator.class)) {
                            if (clazz == AbstractImportValidator.class) {
                                continue;
                            }
                            ImportValidator validator = clazz.newInstance();
                            cachedContainor.put(validator.getValidatorClass(), validator);
                        }
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("ImportValidatorFactory init error", e);
        }
        return cachedContainor.get(tClass);
    }
}