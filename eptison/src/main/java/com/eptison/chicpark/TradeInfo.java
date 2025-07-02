package com.eptison.chicpark;

import java.util.List;
import lombok.Data;

@Data
public class TradeInfo {

    private String collectionTerminalNo;
    private String ticketNumber;
    private boolean offsetMark;
    private String tradeTime;
    private String tradeType;
    private int tradeAmount;
    private int discountAmount;
    private int qty;
    private boolean member;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private List<ArticleItem> articleItems;
    private List<PaymentItem> paymentItems;
    private List<DiscountItem> discountItems;
}

@Data
class ArticleItem {

    private String name;
    private int qty;
    private int price;
    private int amount;
    private String businessCategoryCode;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;

    // Getters and Setters
}

@Data
class PaymentItem {

    private String payTypeName;
    private String ticketPayTypeName;
    private int amount;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;

    // Getters and Setters
}

@Data
class DiscountItem {

    private String name;
    private double amount;

    // Getters and Setters
}