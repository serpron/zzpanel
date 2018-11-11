package zz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zz.dao.DepartmentMapper;
import zz.entity.Department;
import zz.entity.TreeNode;
import zz.util.Page;
import zz.util.WebResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private TreeMapService treeNodeService;

    @Override
    public List<Map<String,Object>> findDepartmentsWithTree(Integer id) {
        List<Integer> selectIds = new ArrayList<>();
        if(id!=null) {
            Department department = this.departmentMapper.findById(id);
            selectIds.add(department.getParent_id());
        }
        return treeNodeService.generateDepartmentTree(selectIds);
    }

    @Override
    public Page<Department> find(Department example, int page, int rows) {
        int count = this.departmentMapper.count(example);
        Page<Department> pageBean = new Page<>(count,page,rows);
        if(count>0){
            List<Department> list = this.departmentMapper.findByExampleWithPage(example,pageBean.getStart(),pageBean.getRows());
            pageBean.setList(list);
        }
        return pageBean;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public Department add(Department department) {
        // 查询父类的路径
        Integer parent_id = department.getParent_id();
        if(parent_id!=null){
            Department parent = departmentMapper.findById(parent_id);
            String parent_ids = parent.getParent_ids()+"/"+parent_id;
            department.setParent_ids(parent_ids);
        }
        this.departmentMapper.add(department);
        return department;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public Department update(Department department) {
        this.departmentMapper.update(department);
        return department;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void delete(Integer id) {
        this.departmentMapper.delete(id);
    }

    @Override
    public Department findById(Integer id) {
        return departmentMapper.findById(id);
    }

    public DepartmentMapper getDepartmentMapper() {
        return departmentMapper;
    }

    public void setDepartmentMapper(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public TreeMapService getTreeNodeService() {
        return treeNodeService;
    }

    public void setTreeNodeService(TreeMapService treeNodeService) {
        this.treeNodeService = treeNodeService;
    }
}
