package com.eptison.bojun;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Date: 2024/10/25
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Expression {

    private String column;
    private String condition;
    @Default
    private String operator = "=";


    public Expression(String column, String condition) {
        this.column = column;
        this.condition = condition;
        this.operator = "=";
    }


}