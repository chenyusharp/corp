package com.eptison.bojun;

import com.eptison.bojun.BojunAPIProcessOrderTransaction.BojunAPICommandDetailObjParam;
import com.eptison.bojun.ProductQO.BojunAPIQueryOrderBy;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Date: 2024/10/24
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
@SuperBuilder
public class BojunAPICommonRequestQO {


    /**
     * 请求类型,有如下几种类型：
     * "ObjectCreate"|"ObjectModify"|"ObjectDelete"|"ObjectSubmit"|"ExecuteWebAction"|"ProcessOrder"|"Query"|"Import"
     */
    private String command;

    /**
     * 通过ID使得客户端能获取transaction的执行情况
     */
    private String id;

    private BojunAPICommandBaseParam params;


    /**
     * 查询所用param实体类
     */
    @Data
    @SuperBuilder
    @EqualsAndHashCode(callSuper = false)
    public static class BojunAPICommandQueryParam extends BojunAPICommandBaseParam {

        private Integer qlcid;

        private List<String> columns;

        @Default
        private List<String> columnMasks = Collections.singletonList("6");

        private BojunAPIQueryFilter params;

        private Integer start;

        private Integer range;

        private boolean count;

        private List<BojunAPIQueryOrderBy> orderby;

    }

//    /**
//     * 创建所用实体类
//     */
//    @Data
//    @SuperBuilder
//    @EqualsAndHashCode(callSuper = false)
//    public static class BojunAPICommandCreateParam extends HashMap<String,Object> {
//
//        /**
//         * map中的内容，表示具体的新增的字段，key：字段名：value：字段的值,层级需要根据具体的业务来定
//         * 需要注意的是，这个map不会当作一个参数传到伯俊的入参中
//         */
//        private Map<String, Object> createContent;
//    }


    @Data
    @SuperBuilder
    @EqualsAndHashCode(callSuper = false)
    public static class BojunAPICommandModifyParam extends BojunAPICommandBaseParam {

        private Integer id;


        @Default
        private Boolean partialUpdate = true;

        /**
         * map中的内容，表示具体的更新的字段，key：字段名：value：字段的值,层级需要根据具体的业务来定，
         * 需要注意的是，这个map不会当作一个参数传到伯俊的入参中
         */
        private Map<String, Object> createContent;


    }


    /**
     * 适用于ObjectDelete、ObjectSubmit、ObjectUnSubmit类型
     */
    @Data
    @SuperBuilder
    @EqualsAndHashCode(callSuper = false)
    public static class BojunAPICommandExecuteParam extends BojunAPICommandBaseParam {

        private Integer id;
    }




    @Data
    @EqualsAndHashCode(callSuper = false)
    @SuperBuilder
    @NoArgsConstructor
    public static class BojunAPICommandBaseParam {

        /**
         * 对应表的ID或名称，建议使用表名
         */
        private String table;

    }



    @Data
    @SuperBuilder
    @EqualsAndHashCode(callSuper = false)
    public static class BojunAPICommandImportParam extends BojunAPICommandBaseParam {

        /**
         * “yes”|”no”， 在插入行时，若遇到唯一索引出错，是否改插入为更新，默认为”no”
         */
        @Default
        private String updateOnUniqueConstraints = "no";

        /**
         * map中的内容，表示具体的新增的字段，key：字段名：value：字段的值,层级需要根据具体的业务来定
         * 需要注意的是，这个map不会当作一个参数传到伯俊的入参中
         */
        private Map<String, Object> createContent;


    }


    @Data
    @SuperBuilder
    @EqualsAndHashCode(callSuper = false)
    public static class BojunAPICommandGetObjectParam extends BojunAPICommandBaseParam {

        private Integer id;

        /**
         * 指定需要返回的关联的标签的内容
         */
        private List<Integer> reftables;


    }


    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BojunAPIQueryFilter {

        private BojunAPIQueryFilter expr1;

        private BojunAPIQueryFilter expr2;

        private String condition;

        private String column;

        private String combine;

    }

//    @Data
//    @Builder
//    public static class BojunAPIQueryOrderBy {
//
//        @Default
//        private boolean asc = true;
//        private String column;
//
//    }
}