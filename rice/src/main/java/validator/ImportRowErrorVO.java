package validator;

import lombok.Getter;
import lombok.Setter;

/**
 * 导入错误结果(包含验证错误)
 * @author 朱智贤
 * @since 2019-08-15
 */
public class ImportRowErrorVO {

    private static final char MSG_SEPERATOR = ';';

    public ImportRowErrorVO() {
    }

    public ImportRowErrorVO(int row) {
        this.row = row;
        this.errorMsg.append("第"+row+"行错误如下：");
    }

    @Setter
    @Getter
    /**1开始的行号*/
    private int row;

    /**每列的错误,列号需要指明*/
    private StringBuilder errorMsg = new StringBuilder();

    public ImportRowErrorVO appendErrorMsg(String colMsg){
        errorMsg.append(colMsg).append(MSG_SEPERATOR);
        return this;
    }

    public String getErrorMsg(){
        return this.errorMsg.toString();
    }
}
