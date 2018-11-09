<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                            <a href="temp/login.html">
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
        var admin=layui.admin,menu=layui.menu,route=layui.route,sidebar=layui.sidebar;
        route.setRoutes({
            routes:[
                {
                    path: '/',                       //路由地址
                    component: '/views/panel.html', //模板组件地址
                    name: '首页'                              //显示名称
                },
                {
                    path: '/views/panel',                       //路由地址
                    component: '/views/panel.html', //模板组件地址
                    name: '面板'                              //显示名称
                },
                {
                    path: '/views/table',                       //路由地址
                    component: '/views/table.html', //模板组件地址
                    name: '表格'                              //显示名称
                },
                {
                    path: '/views/form',                       //路由地址
                    component: '/views/form.html', //模板组件地址
                    name: '表单'                              //显示名称
                },
                {
                    path: '/views/button',                       //路由地址
                    component: '/views/button.html', //模板组件地址
                    name: '按钮'                              //显示名称
                },
                {
                    path: '/views/carousel',                       //路由地址
                    component: '/views/carousel.html', //模板组件地址
                    name: '轮播'                              //显示名称
                },
                {
                    path: '/users',
                    component: '/views/users/list.html',
                    name: '用户列表'
                },
                {
                    path: '/users/add',
                    component: '/views/users/add.html',
                    name: '添加用户'
                },
                {
                    path: '/users/search',
                    component: '/views/users/find.html',
                    name: '查找用户'
                },
                {
                    path: '/users/id',
                    component: '/views/users/edit.html',
                    name: '编辑用户'
                },
                {
                    path: '/departments',
                    component: '/views/departments/list.html',
                    name: '部门列表'
                },
                {
                    path: '/departments/add',
                    component: '/views/departments/add.html',
                    name: '添加部门'
                },
                {
                    path: '/departments/id',
                    component: '/views/departments/edit.html',
                    name: '编辑部门'
                },
                {
                    path: '/departments/search',
                    component: '/views/departments/find.html',
                    name: '查找部门'
                },
                {
                    path: '/roles',
                    component: '/views/roles/list.html',
                    name: '角色列表'
                },
                {
                    path: '/roles/add',
                    component: '/views/roles/add.html',
                    name: '添加角色'
                },
                {
                    path: '/roles/id',
                    component: '/views/roles/edit.html',
                    name: '编辑角色'
                },
                {
                    path: '/roles/search',
                    component: '/views/roles/find.html',
                    name: '查找角色'
                },
                {
                    path: '/resources',
                    component: '/views/resources/list.html',
                    name: '资源列表'
                },
                {
                    path: '/resources/add',
                    component: '/views/resources/add.html',
                    name: '添加资源'
                },
                {
                    path: '/resources/id',
                    component: '/views/resources/edit.html',
                    name: '编辑资源'
                },
                {
                    path: '/resources/search',
                    component: '/views/resources/find.html',
                    name: '查找资源'
                }
            ]
        });
        menu.set({
            elem:'#menu-box',
            dynamicRender:true,
            data:[
                {id:1,title:'首页',path:'#/',pid:0,open:false,children:[],icon:'&#xe68e;'},
                {id:2,title:'layui测试',pid:0,open:true,icon:'&#xe656;',children:[
                        {id:21,title:'面板',path:'#/views/panel',pid:2,open:false,children:[],icon:'&#xe62d;'},
                        {id:22,title:'表格',path:'#/views/table',pid:2,open:false,children:[],icon:'&#xe62d;'},
                        {id:23,title:'表单',path:'#/views/form',pid:2,open:false,children:[],icon:'&#xe63c;'},
                        {id:24,title:'按钮',path:'#/views/button',pid:2,open:false,children:[],icon:'&#xe609;'},
                        {id:25,title:'轮播',path:'#/views/carousel',pid:2,open:false,children:[],icon:'&#xe64a;'}
                    ]
                },
                {id:3,title:'系统设置',pid:0,open:true,icon:'&#xe656;',children:[
                        {id:31,title:'用户管理',path:'#/users',pid:3,open:false,children:[],icon:'&#xe62d;'},
                        {id:32,title:'部门管理',path:'#/departments',pid:3,open:false,children:[],icon:'&#xe62d;'},
                        {id:33,title:'角色管理',path:'#/roles',pid:3,open:false,children:[],icon:'&#xe63c;'},
                        {id:34,title:'资源管理',path:'#/resources',pid:3,open:false,children:[],icon:'&#xe63c;'}
                    ]
                }
            ]
        });

        // 动态生成菜单
        menu.render();

        // 渲染当前视图
        route.render("#/");

    });
</script>

</body>

</html>