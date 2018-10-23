package zz.service;

import zz.entity.TreeselectNode;
import java.util.List;

public interface TreeselectNodeService {
    /**
     * 将一个普通的节点列表转换为树形结构
     * @return
     */
    List<TreeselectNode> generateDepartmentTree();
}
