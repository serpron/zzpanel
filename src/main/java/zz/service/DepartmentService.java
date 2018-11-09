package zz.service;

import zz.entity.Department;
import zz.entity.TreeNode;
import zz.util.Page;

import java.util.List;

public interface DepartmentService {
    Page<Department> find(Department example, int page, int rows);
    Department add(Department department);
    Department update(Department department);
    void delete(Integer id);
    Department findById(Integer id);
    List<TreeNode> findDepartmentsWithTree(Integer id);
}
