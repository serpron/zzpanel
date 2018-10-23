package zz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import zz.entity.User;
import zz.service.UserService;
import zz.util.RouteModel;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SessionController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/sessions",method = RequestMethod.GET)
    public String login(Model model){
        return "forward:/login.jsp";
    }

    @RequestMapping(value="/sessions",method = RequestMethod.POST)
    public String login(HttpSession session, Model model, String account, String pass){
        if(account== null || pass == null){
            return "forward:/login.jsp";
        }
        User user = this.userService.login(account,pass);
        // 用户名密码正确
        if(user!=null){
            session.setAttribute("current_user",user);
            return "forward:/index.jsp";
        }else{
            return "forward:/login.jsp";
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
