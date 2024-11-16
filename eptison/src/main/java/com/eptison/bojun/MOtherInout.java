package com.eptison.bojun;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * Date: 2024/11/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
@BojunAPITable(table = "M_OTHER_BATIMP")
public class MOtherInout implements Serializable {


//    @BojunAPIRequest(column = "id")
    private Integer id;

    @BojunAPIRequest(column = "DESCRIPTION")
    private String description;


    @BojunAPIRequest(column = "BILLDATE")
    private Integer billDate;


    @BojunAPIRequest(reftableId = 4449)
    private List<MOtherInoutItem> mOtherInoutItemList;


}