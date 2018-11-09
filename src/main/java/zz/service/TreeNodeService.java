package zz.service;

import zz.entity.TreeNode;
import java.util.List;

public interface TreeNodeService {
    /**
     * 将一个普通的节点列表转换为树形结构
     * @return
     */
    List<TreeNode> generateDepartmentTree(List<Integer> selectedIdList);

    List<TreeNode> generateResourcesTree(List<Integer> selectedIdList);

}
