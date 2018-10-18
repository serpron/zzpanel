package zz.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zz.util.WebResult;
import zz.util.WebResultCodeType;

@Controller
public class ErrorsController {

    @ResponseBody
    @RequestMapping(value = "/errors",produces = {"application/json;charset=UTF-8"})
    public WebResult<String> errors(@ModelAttribute("exception") Exception exception){
        return new WebResult<>(WebResultCodeType.ERROR,exception.getMessage(),null);
    }

}
