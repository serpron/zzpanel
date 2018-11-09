package zz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zz.entity.TreeNode;
import zz.entity.User;
import zz.service.UserService;
import zz.util.Page;
import zz.util.WebResult;
import zz.util.WebResultCodeType;

import java.sql.SQLException;
import java.util.List;

@Controller
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
    @RequestMapping(value = "/users",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public Object find(User user,@RequestParam(defaultValue = "-1") int page, @RequestParam(value = "limit",defaultValue = "10") int rows){
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
    @RequestMapping(value = "/users",headers = "data=select",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public Object find(User user){
        return new WebResult<>(this.userService.findSelectInfo(null));
    }

    @RequestMapping(value="/users/{id}/departments",headers="data=tree",
            method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object findDepartmentsWithTree(@PathVariable("id") Integer id){
        List<TreeNode> list = this.userService.findDepartmentsWithTree(id);
        return new WebResult<>(list);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/users",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
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
    @RequestMapping(value="/users",method = RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
    public WebResult<User> update(User user){
        try {
            this.userService.update(user);
            return new WebResult<>(user);
        }catch(RuntimeException e){
            return new WebResult<>(WebResultCodeType.ERROR,e.getMessage(),user);
        }
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public WebResult<User> findById(@PathVariable("id") Integer id){
        User user = this.userService.findById(id);
        return new WebResult<>(user);
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE,produces =  {"application/json;charset=UTF-8"})
    @ResponseBody
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
