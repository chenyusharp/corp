package com.xiazhenyu.cucumber.mq;

import lombok.Getter;

/**
 * 延时消息
 * 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
 *
 * @author 朱智贤
 * @since 2019-10-28
 */
public enum EpMessageDelayLevel {
    SECONDS_ONE(1,"1s"),
    SECONDS_FIVE(2,"5s"),
    SECONDS_TEN(3,"10s"),
    SECONDS_THIRTY(4,"30s"),
    MINUTES_ONE(5,"1m"),
    MINUTES_TWO(6,"2m"),
    MINUTES_THREE(7,"3m"),
    MINUTES_FOUR(8,"4m"),
    MINUTES_FIVE(9,"5m"),
    MINUTES_SIX(10,"6m"),
    MINUTES_SEVEN(11,"7m"),
    MINUTES_EIGHT(12,"8m"),
    MINUTES_NINE(13,"9m"),
    MINUTES_TEN(14,"10m"),
    MINUTES_TWENTY(15,"20m"),
    MINUTES_THIRTY(16,"30m"),
    HOURS_ONE(17,"1h"),
    HOURS_TWO(18,"2h"),
    ;

    @Getter
    private int level;
    @Getter
    private String desc;

    EpMessageDelayLevel(int level, String desc) {
        this.level = level;
        this.desc = desc;
    }

    public static EpMessageDelayLevel getBylevel(int level){
        for(EpMessageDelayLevel epLevel : values()){
            if(epLevel.level==level){
                return epLevel;
            }
        }
        return null;
    }
}
