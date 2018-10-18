package zz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.tree.Tree;
import zz.dao.TreeselectNodeMapper;
import zz.entity.TreeselectNode;

import java.util.ArrayList;
import java.util.List;

@Service("treeselectNodeService")
public class TreeselectNodeServiceImpl implements TreeselectNodeService{

    @Autowired
    private TreeselectNodeMapper treeselectNodeMapper;
    @Override
    public List<TreeselectNode> generateDepartmentTree() {
        List<TreeselectNode> list = treeselectNodeMapper.findAllDepartments();
        // 根节点
        List<TreeselectNode> root = new ArrayList<>();
        // 寻找所有的根节点，并进行进一步迭代
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                TreeselectNode node = list.get(i);
                if(node.getParent_id()==null || node.getParent_id()==0){
                    root.add(node);
                    iterate(node,list);
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
    private void iterate(TreeselectNode parentNode,List<TreeselectNode> list){
        if(list.size()>0) {
            for (int i=0;i<list.size();i++) {
                TreeselectNode node = list.get(i);
                if (node.getParent_id() == parentNode.getId()) {
                    parentNode.getChildren().add(node);
                    iterate(node,list);
                }
            }
        }
    }

    public TreeselectNodeMapper getTreeselectNodeMapper() {
        return treeselectNodeMapper;
    }

    public void setTreeselectNodeMapper(TreeselectNodeMapper treeselectNodeMapper) {
        this.treeselectNodeMapper = treeselectNodeMapper;
    }
}
