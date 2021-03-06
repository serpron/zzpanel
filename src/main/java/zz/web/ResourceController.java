package zz.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zz.entity.Resource;
import zz.service.ResourceService;
import zz.service.TreeMapService;
import zz.service.TreeNodeService;
import zz.util.Page;
import zz.util.WebResult;
import zz.util.WebResultCodeType;

@Controller
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private TreeMapService treeNodeService;

    /**
     * 查找所有资源
     * @param page
     * @param rows
     * @return
     */
    @RequiresPermissions("resources:list")
    @ResponseBody
    @RequestMapping(value = "/resources",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public Object find(Resource example, @RequestParam(defaultValue = "-1") int page, @RequestParam(value = "limit",defaultValue = "10") int rows){
        if(page==-1){
            return new WebResult<>(this.resourceService.find(example));
        }else{
            Page<Resource> pageBean = this.resourceService.find(example,page,rows);
            return WebResult.fromPage(pageBean);
        }
    }
    @RequiresPermissions("resources:list")
    @RequestMapping(value="/resources",headers = "data=tree",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object findAllResourcesWithTree(){
        return new WebResult<>(this.resourceService.findResourcesWithTree(null));
    }
    @RequiresPermissions("resources:list")
    @RequestMapping(value="/resources/{id}/resources",headers = "data=tree",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object findAllResourcesWithTree(@PathVariable("id") Integer id){
        return new WebResult<>(this.resourceService.findResourcesWithTree(id));
    }

    /**
     * 添加资源
     * @param entity
     * @return
     */
    @RequiresPermissions("resources:add")
    @ResponseBody
    @RequestMapping(value="/resources",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public WebResult<Resource> add(Resource entity){
        try {
            this.resourceService.add(entity);
            return new WebResult<>(entity);
        }catch(RuntimeException e){
            return new WebResult<>(WebResultCodeType.ERROR,e.getMessage(),entity);
        }
    }

    /**
     * 修改资源
     * @param entity
     * @return
     */
    @RequiresPermissions("resources:update")
    @ResponseBody
    @RequestMapping(value="/resources",method = RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
    public WebResult<Resource> update(Resource entity){
        try {
            this.resourceService.update(entity);
            return new WebResult<>(entity);
        }catch(RuntimeException e){
            return new WebResult<>(WebResultCodeType.ERROR,e.getMessage(),entity);
        }
    }

    @RequiresPermissions("resources:list")
    @RequestMapping(value = "/resources/{id}",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public WebResult<Resource> findById(@PathVariable("id") Integer id){
        Resource result = this.resourceService.findById(id);
        return new WebResult<>(result);
    }

    @RequiresPermissions("resources:delete")
    @RequestMapping(value = "/resources/{id}",method = RequestMethod.DELETE,produces =  {"application/json;charset=UTF-8"})
    @ResponseBody
    public WebResult<String> delete(@PathVariable("id") Integer id){
        this.resourceService.delete(id);
        return new WebResult<>("");
    }

    public ResourceService getResourceService() {
        return resourceService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public TreeMapService getTreeNodeService() {
        return treeNodeService;
    }

    public void setTreeNodeService(TreeMapService treeNodeService) {
        this.treeNodeService = treeNodeService;
    }
}
