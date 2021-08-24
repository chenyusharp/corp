package com.xiazhenyu.protocol;

/**
 * Date: 2021/8/16
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Message<T> {

    private Header header;
    private T content;

    public Message(Header header, T content) {
        this.header = header;
        this.content = content;
    }


    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}