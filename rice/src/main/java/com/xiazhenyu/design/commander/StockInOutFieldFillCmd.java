package com.xiazhenyu.design.commander;

import java.util.List;

/**
 * Date: 2024/1/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface StockInOutFieldFillCmd<M, D> {

    void startFromHeadNode();

    StockInOutFieldFillCmd getHeadCmdNode();

    StockInOutFieldFillCmd setNextCmd(Class cmdBeanClass);


    StockInOutFieldFillCmd setCurrentCmdExtraArgs(Object... args);

    StockInOutFieldFillCmd setEssentialArgs(M mainDTO, List<D> detailDTO);


    StockInOutFieldFillCmd setBeforeCmd(StockInOutFieldFillBeforeCmdCallBack<M, D> beforeCmdCallBacke);

    StockInOutFieldFillCmd setAfterCmd(StockInOutFieldFillAfterCmdCallBack<M, D> afterCmdCallBack);


    StockInOutFieldFillCmd setGlobalContextArgs(String key, Object value);

    Object[] getCurrentCmdExtraArgs();


    Object getGlobalContextArgs(String key);


}
