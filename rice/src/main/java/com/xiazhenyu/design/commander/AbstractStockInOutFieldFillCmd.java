package com.xiazhenyu.design.commander;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;

/**
 * Date: 2024/1/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public abstract class AbstractStockInOutFieldFillCmd<M, D> implements StockInOutFieldFillCmd {

    public static final ThreadLocal<Map<String, Map<String, Object>>> SHARED_CONTEXT = new ThreadLocal<>();

    public static final String key_head_node = "$headNode";

    public static final String key_next_node = "$nextNode";

    public static final String key_main_DTO = "$mainDTO";

    public static final String key_detail_DTO_List = "$detailDTOList";

    public static final String key_before_cmd = "$beforeCmd";

    public static final String key_after_cmd = "$afterCmd";

    public static final String key_extra_args = "$extraArgs";

    public static final String key_shared_context = "sharedContext";

    public Object internGetValFromSharedContext(String key1, String key2) {
        return SHARED_CONTEXT.get().get(key1).get(key2);
    }

    public void internInitNextNodeContext(String className) {
        SHARED_CONTEXT.get().put(className, new HashMap<>());
    }

    public void internInitHeadNode() {
        SHARED_CONTEXT.set(new HashMap<>());
        SHARED_CONTEXT.get().put(this.getClass().getName(), new HashMap<>());
        SHARED_CONTEXT.get().put(key_shared_context, new HashMap<>());
        SHARED_CONTEXT.get().get(key_shared_context).put(key_head_node, this);
    }

    private void destroyAll() {
        SHARED_CONTEXT.remove();
    }

    private void destroyCurrentNode() {
        SHARED_CONTEXT.get().remove(this.getClass().getName());
    }


    public void interRUn() {
        M mainDTO = (M) SHARED_CONTEXT.get().get(getClass().getName()).get(key_main_DTO);
        List<D> detailDTOList = (List<D>) SHARED_CONTEXT.get().get(getClass().getName()).get(key_detail_DTO_List);
        if (ObjectUtils.anyNull(mainDTO, detailDTOList)) {
            //  抛出异常
        }
        final StockInOutFieldFillBeforeCmdCallBack beforeCmd = (StockInOutFieldFillBeforeCmdCallBack) SHARED_CONTEXT.get().get(getClass().getName()).get(key_before_cmd);
        if (beforeCmd != null) {
            beforeCmd.beforeCommand(mainDTO, detailDTOList, this);
        }
        this.run(mainDTO, detailDTOList, getCurrentCmdExtraArgs());

        StockInOutFieldFillAfterCmdCallBack afterCmd = (StockInOutFieldFillAfterCmdCallBack)
                SHARED_CONTEXT.get().get(getClass().getName()).get(key_after_cmd);
        if (afterCmd != null) {
            afterCmd.afterCommand(mainDTO, detailDTOList, this);
        }
        AbstractStockInOutFieldFillCmd abstractNextCmd = (AbstractStockInOutFieldFillCmd) SHARED_CONTEXT.get().get(getClass().getName()).get(key_next_node);

        if (null != abstractNextCmd) {
            abstractNextCmd.internGetValFromSharedContext(abstractNextCmd.getClass().getName(),key_main_DTO);
            abstractNextCmd.internGetValFromSharedContext(abstractNextCmd.getClass().getName(),key_main_DTO);

        }


    }


    @Override
    public Object[] getCurrentCmdExtraArgs() {
        return (Object[]) SHARED_CONTEXT.get().get(getClass().getName()).get(key_extra_args);
    }

    public abstract void run(M mainDTO, List<D> detailDTOList, Object[] extraArgs);


}