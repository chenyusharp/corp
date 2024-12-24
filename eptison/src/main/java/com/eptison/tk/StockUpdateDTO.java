package com.eptison.tk;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Date: 2024/12/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
public class StockUpdateDTO {


    private String code;

    @JSONField(name = "warehouse_code")
    private String warehouseCode = "V_TEST_FCZS";

    private Integer quantity;

}