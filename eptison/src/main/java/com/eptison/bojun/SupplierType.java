package com.eptison.bojun;

import lombok.Data;

/**
 * Date: 2024/10/25
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
@BojunAPITable(table = "C_SUPPLIERTYPE")
public class SupplierType {


    @BojunAPIRequest(column = "id")
    private Integer id;

    @BojunAPIRequest(column = "NAME")
    private String supplierTypeCode;

    @BojunAPIRequest(column = "DESCRIPTION")
    private String supplierTypeName;

}