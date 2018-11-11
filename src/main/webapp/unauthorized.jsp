<%@ page import="zz.util.WebResult,com.alibaba.fastjson.JSON" contentType="text/json;charset=UTF-8"%><%
    WebResult<String> result = new WebResult<>("未授权的访问！");
    String json = JSON.toJSONString(result);
    out.write(json);
%>

