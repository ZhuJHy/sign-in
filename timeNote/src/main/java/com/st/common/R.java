package com.st.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: R
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/7 19:25
 */
@Data
public class R<T> implements Serializable {

    private int code; //200为正常 ，非200表示异常

    private String msg;

    private Object data;

    private String server;

    public static <T> R<T> succ(){
        return succ(201,"操作成功",null,"Java服务器");
    }

    public static <T>R<T> succ(Object data){
        return succ(201,"操作成功",data,"Java服务器");
    }

    public static <T>R<T> succ(int code,String msg,Object data,String server){
        R<T> r = new R<T>();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        r.setServer(server);
        return r;
    }

    public static <T>R<T> fail(String msg,String server){
        return fail(200,msg,null,server);
    }

    public static <T>R<T> fail(String msg,Object data,String server){
        return fail(200,msg,data,server);
    }

    public static <T>R<T> fail(int code,String msg,Object data,String server){
        R<T> r = new R<T>();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        r.setServer(server);
        return r;
    }
}
