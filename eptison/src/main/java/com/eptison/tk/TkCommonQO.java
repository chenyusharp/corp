package com.eptison.tk;

import com.google.common.base.CaseFormat;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import net.sf.cglib.beans.BeanMap;

/**
 * Date: 2024/10/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
public class TkCommonQO {


    private String appKey;
    private String sign;
    private String timestamp;

    private Integer pageOffset;

    private Integer pageSize;


    public static Map convert2LowerUnderScore(TkCommonQO tkCommonQO) {
        Map<String, Object> objectMap = BeanMap.create(tkCommonQO);
        Map<String, String> paramMap = new HashMap<>(objectMap.size());
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            if (null != entry.getValue()) {
                paramMap.put(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entry.getKey()), String.valueOf(entry.getValue()));
            }
        }
        return paramMap;
    }


}