package zz.dao;

import zz.entity.TreeNode;
import zz.util.BaseDao;

import java.util.List;

public interface TreeNodeMapper extends BaseDao<TreeNode,Integer> {
    List<TreeNode> findAllDepartments();
    List<TreeNode> findAllResources();
    List<TreeNode> findAllDepartmentsWithUsers();
}
