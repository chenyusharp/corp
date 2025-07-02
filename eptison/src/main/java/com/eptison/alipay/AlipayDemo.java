package com.eptison.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayDataBillAccountlogQueryModel;
import com.alipay.api.request.AlipayDataBillAccountlogQueryRequest;
import com.alipay.api.response.AlipayDataBillAccountlogQueryResponse;

/**
 * Date: 2024/12/24
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class AlipayDemo {


    public static void main(String[] args) throws AlipayApiException {

        //构造client
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipay.com/gateway.do",
                "2021004197628989",
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCEWHuhqOR+eSEvlD1/pBoBjEwReF0RfXu9NsTyJVUDdUoJvltnUWAv7DD9ALZ8kIlw3qHf2bhuqbFC3/1NRWSefT91lIeezy89HNj+7FGZZcVlMV7RXkpCMm3iKeQqVjTGmD+UB5gsrsGoz3YBLMqMY/CQ1seyxz+v2xhpw2gYjbWXKmhpubUNQQ/97npcneoUx7erxXpnRU9r8mGVvMPadjBs1e9uNAA+Op5S54Gmqnr0vv6XYAk1VKFeDsvIlCpTQ7LokBqp9KDTIvXHSpLuKHMl7mhvckT/g2fFIa4yTcCC0qJAIrMKifjVSo9KS0HBwlm7CRbVBoreLIEGCHiHAgMBAAECggEAYz5bD4IwPmslKKG4cFQlsE196Yfh+rCnrt84VwEJvDsjFyVpM5QBVk5"
                        + "++eqj7csRVdONfeitdkbIB4ciUuSbrIHtqYWFIeDp4qEUVC3wjuCTYuvf148mjnd0kSmq0NiuYNJgsufzBGbUEHvqWL028oorJuH5BKaHqp7Yss7265mcpyex2stdpWU4cFW1RaWWDRH2Z6NqFEDvdIVUdeEyRR1jmWbFIpdrEDF8kdrem4mlJmwP57B6mYc1mOiFZiGikZs9/4ePMSanBBTTRXn1jkjcpY5KyJKdhUtAxK7aprOxE+Y9P9OaTTvF0uWtyVq1soE0rjV9EjJaq/dl/kWbYQKBgQC5IGYGKcT11dN33jr297rLX/SEhoeV8SXSBUgqs7wdcMEo2EIQ2cbIvBkbIzU+r5ekKX5p4mSDdVUVU2V4ExO1cWEos9QgRdLFcCiOMmusbF3dBOcr3DCzY28T3JqPDx/N2tTU2GSyVG6IilGnrEZsPd"
                        + "+g3AqXXGeBnshkojpHkQKBgQC3AzZfueDl59LetDNl6gF+4h9/9ehoY2Aez/EBfRwYjB69JiSvJOG+4kBIn8XDDzNhuuUI7uJ0c1uskQYRWOEIzZ9i85uG6aRD6yTp/o5yMo8kDaVHN9MJdJHbEYp0JYmTn70ULjwaeJkYzDjyX7M7zf7i+/n+7wVz0tlkuDcilwKBgDBZQcwCRKpfueboZ0Xw1RwgZ1/lMqv6WwJK1towLsURmaDtOfPkrIHofjYUL+e7PENngthshAvBLQNw0GNXuCH/Bnz22kPDeJSQAkeA65YQh0Md8LIHw8BWC8CIMOWD8a51pHgiO8pHOsb2OPUtjQbf8fO1BB8bxvyXV4bQx5/BAoGBALRy82sBkRG/ObkivscPKzhbZym5YtWCnqTi7URFgq1lg22HLYaP/BJFbZLuDoWc+FG1gN0FBb5CfDKzr"
                        + "+Ls2r3eWPitMD2fI7tgID6kIvnQhjjLR5roHSpmiOYS7oxE8tjfGQTL7bFAFdxQiRnaaJRgMaDHSKEoNh0U2gsoZByzAoGBAKWWaoQg6PDnjWUjdlpuvIcu4RgCN1WA7PtGnqRnw7oslZyBUqg3VBuAMcuq+xKem6+2O4GbOqoeJNZbU4gpyRB09JbQYcjf/FR+kQcGYmfrYOcn/1nteHXrUaUFfjQwMjzAQe+3AG70q3YovQ3jGVjGlXmdh8uZcVVp/7K+WeZy",
                "json",
                "UTF-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApRc3e5o2JRFj19ml8O6YxuB3NI9rNXkPXLvjGdKDUr6ZtKX16XMUB9ers9t0l+/ZxA2vkGtTdNLRIy6oQZw/oZb0d5CwhkSRV4ovweJu56HUDwOLwvpHj5p22NTAOVCKdVwqvNE0FCy7o6VNEPxBYk1N3TSRLkrevViSu8FRrJPdVoMoFalDaThU3qf7/s4atDH7fkn3B0deC16LOr5tf3pajdSC3gOPX9Y05heG9hzMC62neqsgRbCHQcTcuwwKTV/KzJcNi0/I3ebGiAd3jq3+y59CrvREfdI2UWfxoeLkgLcGk9hPsW+r8LI4ZMnAMF7TFvpHUHTBz4KjDNCYUwIDAQAB",
                "RSA2"
        );
        AlipayDataBillAccountlogQueryRequest request = new AlipayDataBillAccountlogQueryRequest();
        AlipayDataBillAccountlogQueryModel model = new AlipayDataBillAccountlogQueryModel();
// 设置账务流水创建时间的起始范围
        model.setStartTime("2024-12-24 00:00:00");

        // 设置账务流水创建时间的结束范围
        model.setEndTime("2024-12-24 23:59:59");

        // 设置支付宝订单号
//        model.setAlipayOrderNo("20190101***");

        // 设置商户订单号
//        model.setMerchantOrderNo("TX***");

        // 设置分页号
        model.setPageNo("1");

        // 设置分页大小1000-2000
        model.setPageSize("2000");

        request.setBizModel(model);
        AlipayDataBillAccountlogQueryResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());

        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
            // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
            // String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
            // System.out.println(diagnosisUrl);
        }
    }
}