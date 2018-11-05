package zz.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;

import java.util.List;

/**
 * API标准结果
 */
public class WebResult<T> {
    private Integer code;
    private Integer count;
    private String msg;
    private T data;

    public WebResult(){
        this(WebResultCodeType.OK,"",null);
    }
    public WebResult(Integer code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public WebResult(String msg,T data){
        this(WebResultCodeType.OK,msg,data);
    }
    public WebResult(T data){
        this("操作成功",data);
    }
    public static <T> WebResult fromPage(Page<T> pageBean){
        WebResult<List<T>> result = new WebResult<>();
        result.setCount(pageBean.getTotals());
        result.setData(pageBean.getList());
        return result;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
