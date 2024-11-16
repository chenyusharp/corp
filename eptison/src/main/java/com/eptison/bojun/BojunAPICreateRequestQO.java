package com.eptison.bojun;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 * Date: 2024/10/25
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
@Builder
public class BojunAPICreateRequestQO {

    private String command;

    /**
     * 通过ID使得客户端能获取transaction的执行情况
     */
    private String id;
    private Map<String, Object> params;

}