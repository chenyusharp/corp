package com.eptison.bojun;

import com.alibaba.fastjson.JSON;
import java.util.List;
import lombok.Data;

/**
 * Date: 2024/10/25
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
public class BojunAPIResponseCommonDTO {


    private Integer code;
    private String message;
    private Integer count;
    private String id;
    private ResponseData data;

    /**
     * 会根据请求的信息，映射出对应的业务数据集合
     */
    private List bizDataList;

    private Integer objectid;
//    private List<List<String>> rows;

    @Data
    public static class ResponseData {

        private Integer count;
        private List<List<Object>> rows;
    }

    public static void main(String[] args) {
        String jsonArray1 = "[{\"code\":0,\"data\":{\"count\":1,\"rows\":[[\"DQXZB\",\"都市连衣裙\"]]},\"count\":1,\"id\":\"\",\"message\":\"完成:0.045 seconds\",\"rows\":[[\"DQXZB\",\"都市连衣裙\"]]}]";
        String jsonArray2 = "[{\"code\":0,\"data\":{\"count\":1,\"rows\":[[\"DQXZB\",\"都市连衣裙\"]]},\"count\":1,\"id\":\"\",\"message\":\"完成:0.003 seconds\",\"rows\":[[\"DQXZB\",\"都市连衣裙\"]]}]";
        List<BojunAPIResponseCommonDTO> responseList = JSON.parseArray(jsonArray2, BojunAPIResponseCommonDTO.class);
        System.out.println(responseList);

    }

}