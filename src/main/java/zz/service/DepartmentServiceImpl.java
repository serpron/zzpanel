package zz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zz.dao.DepartmentMapper;
import zz.entity.Department;
import zz.util.Page;

@Transactional(readOnly = true)
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public Page<Department> find(Department example, int page, int rows) {
        return null;
    }

    @Override
    public Department add(Department department) {
        return null;
    }

    @Override
    public Department update(Department department) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    public DepartmentMapper getDepartmentMapper() {
        return departmentMapper;
    }

    public void setDepartmentMapper(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }
}
