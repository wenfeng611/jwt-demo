package org.skytech.kefu.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseResult {

    private int code;
    private String msg;
    private Object data;

    public ResponseResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseResult success(Object data){
        return new ResponseResult(200,"success",data);
    }

    public static ResponseResult error(int code,String msg){
        return new ResponseResult(code,msg,null);
    }
}
