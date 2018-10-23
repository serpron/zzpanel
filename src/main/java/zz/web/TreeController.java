package zz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zz.service.TreeselectNodeService;

@Controller
public class TreeController {

    @Autowired
    private TreeselectNodeService treeselectNodeService;

    /**
     * 响应layui treeseelct树形控件数据的要求，通过请求头进行过滤，
     * @return
     */
    @RequestMapping(value="/departments",headers = "data=tree",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object findAllDepartmentsWithTree(){
        return this.treeselectNodeService.generateDepartmentTree();
    }

    /**
     * 生成layui table json格式数据
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value="/departments",method = RequestMethod.GET)
    public Object findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(value = "limit",defaultValue = "10") int rows){
        return null;
    }

    public TreeselectNodeService getTreeselectNodeService() {
        return treeselectNodeService;
    }

    public void setTreeselectNodeService(TreeselectNodeService treeselectNodeService) {
        this.treeselectNodeService = treeselectNodeService;
    }
}
