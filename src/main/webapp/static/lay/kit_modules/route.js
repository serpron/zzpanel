/** kitadmin-v2.1.0 MIT License By http://kit.zhengjinfan.cn Author Van Zheng */ ;
"use strict";
layui.define(["utils", "jquery", "lodash", "nprogress", "layer"], function(e) {
    var i = layui.utils,
        t = i.localStorage,
        o = t.setItem,
        n = t.getItem,
        r = layui.jquery,
        a = layui.lodash,
        s = layui.layer,
        u = r(window),
        l = void 0,
        d = function() {
            this.config = {
                name: "KITADMINROUTE",
                routerViewId: void 0,
                beforeRender: void 0,
                routes: []
            }, this.version = "1.2.1"
        };
    d.prototype.set = function(e) {
        return r.extend(!0, this.config, e), this
    }, d.prototype.setRoutes = function(e) {
        var t = this;
        e.name = e.name || t.config.name, t.config.name = e.name;
        var n = {
            routes: []
        };
        return r.extend(!0, n, e), a.forEach(n.routes, function(e) {
            e.id = (new Date).getTime() + "" + a.random(1e3, 9999)
        }), o(n.name, n.routes), r(window).off("popstate").on("popstate", function() {
            i.isFunction(e.onChanged) ? e.onChanged() : t.render()
        }), t
    }, d.prototype.getRoutes = function() {
        return n(this.config.name)
    }, d.prototype.getRoute = function(e) {
        var t = this.getRoutes(this.config.name);
        if (null !== t && void 0 !== t) {
            e = e || location.hash;
            var o = layui.router(e).href.split("?"),
                n = i.find(t, function(e) {
                    return e.path === o[0]
                });
            return o.length > 1 && (n.component += "?" + o[1], n.path += "?" + o[1]), n
        }
    }, d.prototype.render = function(e, t, o) {
        var n = this,
            a = n.config,
            d = void 0;
        NProgress.start();
        var c = s.load(2),
            v = i.randomCode();
        if (t && t.length > 0) d = t;
        else {
            var f = void 0 === a.routerViewId ? r("router-view") : r("router-view#" + a.routerViewId);
            f.length > 0 && (f.parent().append('<div id="' + v + '"></div>'), f.remove(), d = r("#" + v), l = d)
        }
        void 0 === d && (d = l);
        var h = n.getRoute(e);
        function p() {
            NProgress.done(), c && s.close(c), i.isFunction(o) && o()
        }
        return void 0 !== h ? ("function" == typeof a.beforeRender && (h = a.beforeRender(h)), h.iframe ? (d.html('<iframe src="' + h.component + '" data-id="' + v + '" style="height: 780px;"></iframe>'), u.on("resize", function() {
            var e = r(".layui-body").height();
            r("iframe[data-id=" + v + "]").height(e - 3)
        }).resize(), p()) : i.tplLoader(h.component, function(e) {
            // 这里先修改地址栏路径，然后渲染页面
            i.setUrlState(h.name, "#" + h.path),d.html(e), p()
        }, function(e) {
            var i = ['<div class="layui-fluid">', '<div class="layui-row">', '<div class="layui-col-xs12">', '<div class="kit-not-router">', e, "</div>", "</div>", "</div>", "</div>"].join("");
            d.html(i), p()
        })) : (d.html(['<div class="layui-fluid">', '  <div class="layui-row">', '    <div class="layui-col-xs12">', '      <div class="layui-row">', '        <div class="layui-col-md4">&nbsp;</div>', '        <div class="layui-col-md4">', '          <div class="kit-exception">', '            <i class="layui-icon kit-exception-icon">&#xe61c;</i>', '            <h3 class="kit-exception-title">:>404 抱歉，你访问的页面不存在</h3>', '            <a href="javascript:history.back(-1);" class="layui-btn">返回上一页</a>', "          </div>", "        </div>", '        <div class="layui-col-md4">&nbsp;</div>', "      </div>", "    </div>", "  </div>", "</div>"].join("")), NProgress.done(), c && s.close(c)), n
    }, d.prototype.params = function() {
        var e = layui.router();
        if (void 0 === e.href) return null;
        var i = e.href,
            t = i.substr(i.indexOf("?") + 1);
        if (i === t) return null;
        var o = t.split("&"),
            n = {};
        return a.forEach(o, function(e, i) {
            var t = e.split("="),
                o = t[0],
                r = t[1];
            n[o] = r
        }), n
    }, e("route", new d)
});
//# sourceMappingURL=route.js.map