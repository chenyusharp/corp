package com.eptison.tk;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.base.CaseFormat;
import java.util.HashMap;
import java.util.List;
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

    private Integer pageOffset;

    private Integer pageSize;

    private List<String> platformSpuCodes;


    @JSONField(name = "skus")
    private List<StockUpdateDTO> skus;


    @JSONField(name = "skus")
    private List<String> skuCode;


    public Map convert2LowerUnderScore() {
        Map<String, Object> objectMap = BeanMap.create(this);
        Map<String, Object> paramMap = new HashMap<>(objectMap.size());
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            if (null != entry.getValue()) {
                paramMap.put(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entry.getKey()), entry.getValue());
            }
        }
        return paramMap;
    }
}