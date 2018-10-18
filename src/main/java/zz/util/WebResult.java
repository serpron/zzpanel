package zz.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;

/**
 * API标准结果
 */
public class WebResult<T> {
    private Integer code;
    private String message;
    private T data;

    public WebResult(){
        this(WebResultCodeType.OK,"",null);
    }
    public WebResult(Integer code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public WebResult(String message,T data){
        this(WebResultCodeType.OK,message,data);
    }
    public WebResult(T data){
        this("操作成功",data);
    }
    public String toString(){
       return JSONObject.toJSONString(this);
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
