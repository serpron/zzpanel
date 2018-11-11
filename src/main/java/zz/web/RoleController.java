package zz.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zz.entity.Role;
import zz.entity.TreeNode;
import zz.entity.User;
import zz.service.RoleService;
import zz.util.Page;
import zz.util.WebResult;
import zz.util.WebResultCodeType;

import java.util.List;
import java.util.Map;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查找所有角色
     * @param page
     * @param rows
     * @return
     */
    @RequiresPermissions("roles:list")
    @ResponseBody
    @RequestMapping(value = "/roles",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public Object find(Role example, @RequestParam(defaultValue = "1") int page, @RequestParam(value = "limit",defaultValue = "10") int rows){
        Page<Role> pageBean = this.roleService.find(example,page,rows);
        return WebResult.fromPage(pageBean);
    }

    /**
     * 查找角色相关用户
     * @param id
     * @return
     */
    @RequiresPermissions("roles:list")
    @ResponseBody
    @RequestMapping(value = "/roles/{id}/users",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public Object findRoleUsers(@PathVariable("id") int id){
        List<Map<String,Object>> list = this.roleService.findRoleUsers(id);
        WebResult<List<Map<String,Object>>> result = new WebResult(list);
        return result;
    }

    /**
     * 查找角色相关资源
     * @param id
     * @return
     */
    @RequiresPermissions("roles:list")
    @ResponseBody
    @RequestMapping(value = "/roles/{id}/resources",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public Object findRoleResources(@PathVariable("id") int id){
        List<Map<String,Object>> list = this.roleService.findRoleResources(id);
        WebResult<List<Map<String,Object>>> result = new WebResult(list);
        return result;
    }
    /**
     * 添加角色
     * @param entity
     * @return
     */
    @RequiresPermissions("roles:add")
    @ResponseBody
    @RequestMapping(value="/roles",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public WebResult<Role> add(Role entity){
        try {
            this.roleService.add(entity);
            return new WebResult<>(entity);
        }catch(RuntimeException e){
            return new WebResult<>(WebResultCodeType.ERROR,e.getMessage(),entity);
        }
    }

    /**
     * 修改角色
     * @param entity
     * @return
     */
    @RequiresPermissions("roles:update")
    @ResponseBody
    @RequestMapping(value="/roles",method = RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
    public WebResult<Role> update(Role entity){
        try {
            this.roleService.update(entity);
            return new WebResult<>(entity);
        }catch(RuntimeException e){
            return new WebResult<>(WebResultCodeType.ERROR,e.getMessage(),entity);
        }
    }

    @RequiresPermissions("roles:list")
    @RequestMapping(value = "/roles/{id}",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public WebResult<Role> findById(@PathVariable("id") Integer id){
        Role result = this.roleService.findById(id);
        return new WebResult<>(result);
    }

    @RequiresPermissions("roles:delete")
    @RequestMapping(value = "/roles/{id}",method = RequestMethod.DELETE,produces =  {"application/json;charset=UTF-8"})
    @ResponseBody
    public WebResult<String> delete(@PathVariable("id") Integer id){
        this.roleService.delete(id);
        return new WebResult<>("");
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
