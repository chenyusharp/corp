package com.xiazhenyu.corp.orm.plugins;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.Getter;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;

/**
 * Date: 2022/12/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DialectModel {


    public static final String FIRST_PARAM_NAME = "mybatis_plus_first";

    public static final String SECOND_PARAM_NAME = "mybatis_plus_second";


    @Getter
    private final String dialectSql;

    private Configuration configuration;


    private Consumer<List<ParameterMapping>> firstParamConsumer = i -> {
    };

    private Consumer<Map<String, Object>> firstParamMapConsumer = i -> {
    };

    private Consumer<List<ParameterMapping>> secondParamConsumer = i -> {
    };

    private Consumer<Map<String, Object>> secondParamMapConsumer = i -> {
    };


    private final long firstParam;
    private final long secondParam;

    public DialectModel(String dialectSql, long firstParam, long secondParam) {
        this.dialectSql = dialectSql;
        this.firstParam = firstParam;
        this.secondParam = secondParam;
    }


    public DialectModel setConsumer(boolean isFirstParam, Function<List<ParameterMapping>, Integer> function) {
        if (isFirstParam) {
            this.firstParamConsumer=i-> i.add(function.apply(i),new ParameterMapping.Builder(configuration,FIRST_PARAM_NAME,long.class).build());
        }else {
            this.secondParamConsumer=i->i.add(function.apply(i),new ParameterMapping.Builder(configuration,SECOND_PARAM_NAME,long.class).build());
        }
        this.setParamMapConsumer(isFirstParam);
        return this;
    }


    public DialectModel setConsumerChain(){
        return setConsumer(true).setConsumer(false);
    }

    public DialectModel setConsumer(boolean isFirstParam){
        if (isFirstParam) {
            this.firstParamConsumer=i->i.add(new ParameterMapping.Builder(configuration,FIRST_PARAM_NAME,long.class).build());
        }else {
            this.secondParamConsumer=i->i.add(new ParameterMapping.Builder(configuration,SECOND_PARAM_NAME,long.class).build());
        }
        this.setParamMapConsumer(isFirstParam);
        return this;
    }


    private void setParamMapConsumer(boolean isFirstParam) {
        if (isFirstParam) {
            this.firstParamMapConsumer = i -> i.put(FIRST_PARAM_NAME, firstParam);
        }else {
            this.secondParamMapConsumer = i -> i.put(SECOND_PARAM_NAME, secondParam);
        }
    }


}