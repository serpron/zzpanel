package zz.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zz.entity.User;
import zz.service.UserService;
import zz.util.Page;
import zz.util.WebResult;
import zz.util.WebResultCodeType;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查找所有用户
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("users:list")
    public Object find(User user,@RequestParam(defaultValue = "-1") int page,
                       @RequestParam(value = "limit",defaultValue = "10") int rows){
        if(page!=-1) {
            Page<User> pageBean = this.userService.find(user, page, rows);
            return WebResult.fromPage(pageBean);
        }else{
            return new WebResult<>(this.userService.find(user));
        }
    }

    /**
     * 查找所有用户以select方式打开
     * @return
     */
    @ResponseBody
    @RequestMapping(headers = "data=select",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("users:list")
    public Object find(User user){
        return new WebResult<>(this.userService.findSelectInfo(null));
    }

    @RequestMapping(value="/{id}/departments",headers="data=tree",
            method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @RequiresPermissions("users:list")
    public Object findDepartmentsWithTree(@PathVariable("id") Integer id){
        List<Map<String,Object>> list = this.userService.findDepartmentsWithTree(id);
        return new WebResult<>(list);
    }

    @RequestMapping(value="/{account}/resources",
            method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @RequiresPermissions("users:list")
    public Object findMenuTree(@PathVariable("account") String account){
        List<Map<String,Object>> list = this.userService.findMenuTree(account);
        return new WebResult<>(list);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("users:add")
    public WebResult<User> add(User user){
        try {
            this.userService.add(user);
            return new WebResult<>(user);
        }catch(RuntimeException e){
            return new WebResult<>(WebResultCodeType.ERROR,e.getMessage(),user);
        }
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("users:update")
    public WebResult<User> update(User user){
        try {
            this.userService.update(user);
            return new WebResult<>(user);
        }catch(RuntimeException e){
            return new WebResult<>(WebResultCodeType.ERROR,e.getMessage(),user);
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @RequiresPermissions("users:list")
    public WebResult<User> findById(@PathVariable("id") Integer id){
        User user = this.userService.findById(id);
        return new WebResult<>(user);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE,produces =  {"application/json;charset=UTF-8"})
    @ResponseBody
    @RequiresPermissions("users:delete")
    public WebResult<String> delete(@PathVariable("id") Integer id){
        this.userService.delete(id);
        return new WebResult<>("");
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
