package com.eptison.bojun;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Date: 2024/10/25
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Getter
@AllArgsConstructor
public enum BojunAPIType {
    OBJECT_CREATE("ObjectCreate", "单据创建"),

    OBJECT_MODIFY("ObjectModify", "修改"),

    OBJECT_DELETE("ObjectDelete", "删除"),

    OBJECT_SUBMIT("ObjectSubmit", "提交"),

    PROCESS_ORDER("ProcessOrder", "主子表单据创建"),

    QUERY("Query", "查询"),

    IMPORT("Import", "导入");


    private final String code;
    private final String name;

}
