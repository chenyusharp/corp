package com.eptison.bojun;

import java.io.Serializable;
import lombok.Data;

/**
 * Date: 2024/11/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
@BojunAPITable(table = "M_OTHER_BATIMPITEM")
public class MOtherInoutItem implements Serializable {


    @BojunAPIRequest(column = "C_STORE_ID__NAME")
    private String cStoreName;


    @BojunAPIRequest(column = "C_OTHER_INOUTTYPE_ID__NAME")
    private String cOtherInoutTypeName;


    @BojunAPIRequest(column = "M_PRODUCTALIAS_ID__NO")
    private String merchantCode;


    @BojunAPIRequest(column = "QTY")
    private Integer count;


    @BojunAPIRequest(column = "DESCRIPTION")
    private String description;


}