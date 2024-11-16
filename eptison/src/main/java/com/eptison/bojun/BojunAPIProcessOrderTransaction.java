package com.eptison.bojun;

import com.eptison.bojun.BojunAPICommonRequestQO.BojunAPICommandBaseParam;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Date: 2024/10/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BojunAPIProcessOrderTransaction {


    /**
     * 请求类型,有如下几种类型：
     * "ObjectCreate"|"ObjectModify"|"ObjectDelete"|"ObjectSubmit"|"ExecuteWebAction"|"ProcessOrder"|"Query"|"Import"
     */
    private String command;

    /**
     * 通过ID使得客户端能获取transaction的执行情况
     */
    private String id;


    private  BojunAPIProcessOrderParam params;




    /**
     * 为了和BojunAPICommandCreateParam区分开来，单独新增一个表单提交的类，增加了一个id的属性
     */
    @Data
    @SuperBuilder
    @EqualsAndHashCode(callSuper = false)
    @NoArgsConstructor
    public static class BojunAPIProcessOrderParam {

        /**
         * 表头记录对象，其实就是一个ObjectCreate对象。
         */
        private Map<String,Object> masterobj;


        /**
         * 明细记录对象，可以包含多个标签页的内容
         */
        private BojunAPICommandDetailObjParam detailobjs;


        /**
         * 定义在操作前的行为，具体的可以参考单据上的动作定义
         */
        private List<Integer> preActions;


        /**
         * 定义在操作后的行为，具体的可以参考单据上的动作定义
         */
        private List<Integer> postActions;


        /**
         * 是否提交
         */
        @Default
        private Boolean submit = true;


    }

    @Data
    @SuperBuilder
    @EqualsAndHashCode(callSuper = false)
    @NoArgsConstructor
    public static class BojunAPICommandDetailObjParam extends BojunAPICommandBaseParam {

        private List<Integer> reftables;

        /**
         * 建议使用表名
         */
        private List<String> tables;


        /**
         * 对象数组
         */
        private List<BojunAPICommandRefObjParam> refobjs;
    }

    /**
     * 创建明细的对象，同时支持1:1与1:m
     */
    @Data
    @SuperBuilder
    @EqualsAndHashCode(callSuper = false)
    @NoArgsConstructor
    public static class BojunAPICommandRefObjParam extends BojunAPICommandBaseParam {

        /**
         * 要修改的记录id
         */
        private Integer id;


        /**
         * map中的内容，表示具体的新增的字段，key：字段名：value：字段的值,层级需要根据具体的业务来定
         * 需要注意的是，这个map不会当作一个参数传到伯俊的入参中
         * 这个参数在1；m的时候没有用
         */
        private Map<String, Object> createContent;


        /**
         * 1:m属性的时候，新增数组
         */
        private List<Map<String, Object>> addList;


        /**
         * 1:m属性的时候，修改的数组
         */
        private List<Map<String, Object>> modifyList;


        /**
         * 1:m属性的时候，删除的数组,这个时候，理论上这个map里面应该只需要一个id就可以了
         */
        private List<Map<String, Object>> deleteList;


    }


}



