package zz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zz.entity.Department;
import zz.entity.TreeNode;
import zz.service.DepartmentService;
import zz.service.TreeNodeService;
import zz.util.Page;
import zz.util.WebResult;
import zz.util.WebResultCodeType;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    /**
     * 查找所有部门
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/departments",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public Object find(Department example, @RequestParam(defaultValue = "1") int page, @RequestParam(value = "limit",defaultValue = "10") int rows){
        Page<Department> pageBean = this.departmentService.find(example,page,rows);
        return WebResult.fromPage(pageBean);
    }

    @RequestMapping(value="/departments",headers = "data=tree",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object findAllDepartmentsWithTree(){
        return new WebResult<>(this.departmentService.findDepartmentsWithTree(null));
    }

    @RequestMapping(value="/departments/{id}/departments",headers="data=tree",
            method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object findAllDepartmentsWithTree(@PathVariable("id") Integer id){
        List<TreeNode> list = this.departmentService.findDepartmentsWithTree(id);
        return new WebResult<>(list);
    }

    /**
     * 添加部门
     * @param entity
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/departments",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public WebResult<Department> add(Department entity){
        try {
            this.departmentService.add(entity);
            return new WebResult<>(entity);
        }catch(RuntimeException e){
            return new WebResult<>(WebResultCodeType.ERROR,e.getMessage(),entity);
        }
    }

    /**
     * 修改部门
     * @param entity
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/departments",method = RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
    public WebResult<Department> update(Department entity){
        try {
            this.departmentService.update(entity);
            return new WebResult<>(entity);
        }catch(RuntimeException e){
            return new WebResult<>(WebResultCodeType.ERROR,e.getMessage(),entity);
        }
    }

    @RequestMapping(value = "/departments/{id}",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public WebResult<Department> findById(@PathVariable("id") Integer id){
        Department result = this.departmentService.findById(id);
        return new WebResult<>(result);
    }

    @RequestMapping(value = "/departments/{id}",method = RequestMethod.DELETE,produces =  {"application/json;charset=UTF-8"})
    @ResponseBody
    public WebResult<String> delete(@PathVariable("id") Integer id){
        this.departmentService.delete(id);
        return new WebResult<>("");
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

}
