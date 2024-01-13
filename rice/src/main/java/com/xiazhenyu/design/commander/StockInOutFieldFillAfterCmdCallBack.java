package com.xiazhenyu.design.commander;

import java.util.List;

/**
 * Date: 2024/1/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface StockInOutFieldFillAfterCmdCallBack<M, D> {

    void afterCommand(M mainDTO, List<D> detailDTOList, StockInOutFieldFillCmd cms);


}