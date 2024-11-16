package com.eptison.bojun;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * Date: 2024/10/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
@BojunAPITable(table = "M_RETAIL")
public class SalesOrder implements  Serializable{


    @BojunAPIRequest(column = "id")
    private Integer id;

    @BojunAPIRequest(column = "BILLDATE")
    private String saleDate;


    @BojunAPIRequest(column = "C_STORE_ID__NAME")
    private String saleStoreName;

    @BojunAPIRequest(reftableId = 710, reftableType = "add")
    private List<SalesOrderDetail> addDetailist;

    @BojunAPIRequest(reftableId = 710, reftableType = "delete")
    private List<SalesOrderDetail> deleteDetailist;


    @Data
    @BojunAPITable(table = "M_RETAILITEM")
    public static class SalesOrderDetail implements Serializable {


        @BojunAPIRequest(column = "id")
        private Integer id;


        @BojunAPIRequest(column = "M_PRODUCT_ID__NAME")
        private String merchantCode;


        @BojunAPIRequest(column = "TOT_AMT_ACTUAL")
        private BigDecimal saleAmount;

    }


}