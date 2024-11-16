package com.eptison.bojun;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Date: 2024/10/24
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@BojunAPITable(table = "M_PRODUCT", resultMapDTO = Product.class)
@BojunAPITable(table = "M_COLOR", resultMapDTO = Product.class)
public class ProductQO {


    @BojunAPIRequest(column = "NAME")
    private String goodsNo;


    @BojunAPIRequest(column = "C_SUPPLIER_ID;CODE")
    private String supplierCode;


    @BojunAPIRequest(column = "NAME")
    private List<String> colorList;

    /**
     * 分页查询条件
     */
    @BojunAPIRequest(column = "orderByList")
    private List<BojunAPIQueryOrderBy> orderByList;


    /**
     * 是否分页查询
     */
    @BojunAPIRequest(column = "pageQuery")
    private Boolean pageQuery = Boolean.TRUE;


    /**分页大小*/
    @BojunAPIRequest(column = "pageSize")
    private Integer pageSize = 30;

    /**当前分页*/
    @BojunAPIRequest(column = "currentPage")
    private Integer currentPage = 1;


    @Data
    @AllArgsConstructor
    public static class BojunAPIQueryOrderBy {

        private boolean asc;
        private String column;
    }

}