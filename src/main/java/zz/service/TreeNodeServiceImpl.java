package zz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zz.dao.TreeNodeMapper;
import zz.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

@Service("treeNodeService")
public class TreeNodeServiceImpl implements TreeNodeService {

    @Autowired
    private TreeNodeMapper treeNodeMapper;
    @Override
    public List<TreeNode> generateDepartmentTree(List<Integer> selectedIdList) {
        return iterate(treeNodeMapper.findAllDepartments(),selectedIdList);
    }

    @Override
    public List<TreeNode> generateResourcesTree(List<Integer> selectedIdList) {
        return iterate(treeNodeMapper.findAllResources(),selectedIdList);
    }


    /**
     * 查找list中的根节点，然后进行递归
     * @param list
     * @return
     */
    private List<TreeNode> iterate(List<TreeNode> list,List<Integer> selectedIdList){
        // 根节点
        List<TreeNode> root = new ArrayList<>();
        // 寻找所有的根节点，并进行进一步迭代
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                TreeNode node = list.get(i);
                if(node.getParent()==null || node.getParent()==0){
                    root.add(node);
                    // 判断是否选定
                    if(selectedIdList!=null && selectedIdList.size()>0){
                        if(selectedIdList.contains(node.getId())){
                            node.setSelected("selected");
                        }
                    }
                    iterate(node,list,selectedIdList);
                }
            }
        }
        return root;
    }

    /**
     * 递归查找节点的所有根节点
     * @param parentNode
     * @param list
     */
    private void iterate(TreeNode parentNode, List<TreeNode> list,List<Integer> selectedIdList){
        if(list.size()>0) {
            for (int i=0;i<list.size();i++) {
                TreeNode node = list.get(i);
                if (node.getParent() == parentNode.getId()) {
                    parentNode.getChildren().add(node);
                    // 判断是否选定
                    if(selectedIdList!=null && selectedIdList.size()>0){
                        if(selectedIdList.contains(node.getId())){
                            node.setSelected("selected");
                        }
                    }
                    iterate(node,list,selectedIdList);
                }
            }
        }
    }

    public TreeNodeMapper getTreeNodeMapper() {
        return treeNodeMapper;
    }

    public void setTreeNodeMapper(TreeNodeMapper treeNodeMapper) {
        this.treeNodeMapper = treeNodeMapper;
    }
}
