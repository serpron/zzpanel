package zz.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zz.service.TreeMapService;
import zz.util.WebResult;

@Controller
public class FrontRoutes {

    @Autowired
    private TreeMapService treeNodeService;
    @RequestMapping(value="/frontroutes",
            method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @RequiresPermissions("users:list")
    public Object findFrontRoutes(){
        return new WebResult<>(treeNodeService.findAllFrontRoutes());
    }

    public TreeMapService getTreeNodeService() {
        return treeNodeService;
    }

    public void setTreeNodeService(TreeMapService treeNodeService) {
        this.treeNodeService = treeNodeService;
    }
}
