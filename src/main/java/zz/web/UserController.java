package zz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zz.entity.User;
import zz.service.UserService;
import zz.util.RouteModel;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login(Model model){
        return "forward:/login.jsp";
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(Model model,String username,String password){
        return "forward:/index.jsp";
    }

    @RequestMapping("/index")
    public String index(Model model){
        List<User> list = this.userService.findAll();
        model.addAttribute("list",list);
        return "index";
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
