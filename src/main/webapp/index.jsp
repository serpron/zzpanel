<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>zzpanel</title>
    <link rel="stylesheet" href="/static/css/layui.css" id="layui">
    <link rel="stylesheet" href="/static/css/theme/default.css" id="theme">
    <link rel="stylesheet" href="/static/css/kitadmin.css" id="kitadmin">

</head>

<body class="layui-layout-body kit-theme-default">
<div class="layui-layout layui-layout-admin">
    <!-- header -->
    <div class="layui-header">
        <div class="layui-logo">
            <div class="layui-logo-toggle" kit-toggle="side" data-toggle="on">
                <i class="layui-icon">&#xe65a;</i>
            </div>
            <div class="layui-logo-brand">
                <a href="#/">zzpanel</a>
            </div>
        </div>
        <div class="layui-layout-left">
            <div class="kit-search">
                <form action="/">
                    <input type="text" name="keyword" class="kit-search-input" placeholder="关键字..." />
                    <button class="kit-search-btn" title="搜索" type="submit">
                        <i class="layui-icon">&#xe615;</i>
                    </button>
                </form>
            </div>
        </div>
        <div class="layui-layout-right">
            <ul class="kit-nav" lay-filter="header_right">
                <li class="kit-item" kit-target="help">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe607;</i>
                        <span>帮助</span>
                    </a>
                </li>
                <li class="kit-item" id="ccleft">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe60e;</i>
                    </a>
                </li>
                <li class="kit-item" id="cc">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe64c;</i>
                    </a>
                </li>
                <li class="kit-item">
                    <a href="javascript:;">
              <span>
                <i class="layui-icon layui-icon-set-sm">&nbsp;</i>设置
              </span>
                    </a>
                    <ul class="kit-nav-child kit-nav-right">
                        <li class="kit-item">
                            <a href="#/user/my">
                                <i class="layui-icon">&#xe612;</i>
                                <span>个人中心</span>
                            </a>
                        </li>
                        <li class="kit-item" kit-target="setting">
                            <a href="javascript:;">
                                <i class="layui-icon">&#xe614;</i>
                                <span>设置</span>
                            </a>
                        </li>
                        <li class="kit-nav-line"></li>
                        <li class="kit-item">
                            <a href="/logout">
                                <i class="layui-icon">&#x1006;</i>
                                <span>注销</span>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!-- silds -->
    <div class="layui-side" kit-side="true">
        <div class="layui-side-scroll">
            <div id="menu-box">
                <ul class="kit-menu" lay-filter="kit-menu" id="kit-menu">
                </ul>
            </div>
        </div>
    </div>
    <!-- main -->
    <div class="layui-body" kit-body="true">
        <router-view></router-view>
    </div>
    <!-- footer -->
    <div class="layui-footer" kit-footer="true">
        2018 © zzpanel MIT license  <span class="layui-layout-right">Support by springmvc+spring+mybatis+jquery+layui+kitadmin&nbsp;&nbsp;</span>
        <div style="width:400px; height:400px;" class="load-container load1">
            <div class="loader">Loading...</div>
        </div>
    </div>
</div>
<script src="/static/polyfill.min.js"></script>
<script src="/static/layui.js"></script>
<script src="/static/kitadmin.js"></script>
<script src="/static/echarts.min.js"></script>
<script>
    layui.use(["admin","menu","route","axios","lodash","tabs","sidebar"],function(){
        var admin=layui.admin,menu=layui.menu,route=layui.route,sidebar=layui.sidebar,$=layui.jquery;

        // 使用axios加载页面路由数据
        axios({
            url:'/frontroutes',
            method:'get'
        }).then(function(rest){

            if(rest.data.code==200){
                route.setRoutes({
                    routes:rest.data.data
                });

                // 加载菜单
                menu.set({
                    elem:'#menu-box',
                    dynamicRender:true,
                    remote:{
                        url:'/users/<shiro:principal/>/resources',
                        method:'get',
                        transformResponse:[function (rest) {
                            rest = $.parseJSON(rest);
                            return rest.data;
                        }]
                    }
                });

                // 动态生成菜单
                menu.render();

                // 渲染当前视图
                route.render("#/");

            }else{
                layer.alert('服务器错误:'+rest.msg);
            }
        }).catch(function(reason){
            layer.alert('加载路由失败:'+reason);
        })


    });
</script>

</body>

</html>