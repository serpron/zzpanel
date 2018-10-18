package zz.dao;

import zz.entity.TreeselectNode;
import zz.util.BaseDao;

import java.util.List;

public interface TreeselectNodeMapper extends BaseDao<TreeselectNode,Integer> {
    List<TreeselectNode> findAllDepartments();
}
