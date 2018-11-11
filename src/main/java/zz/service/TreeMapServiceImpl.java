package zz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zz.dao.TreeMapMapper;
import zz.entity.FrontRoute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("treeNodeService")
public class TreeMapServiceImpl implements TreeMapService {

    @Autowired
    private TreeMapMapper treeMapMapper;
    @Override
    public List<Map<String,Object>> generateDepartmentTree(List<Integer> selectedIdList) {
        return iterate(treeMapMapper.findAllDepartments(),selectedIdList);
    }

    @Override
    public List<Map<String,Object>> generateResourcesTree(List<Integer> selectedIdList) {
        return iterate(treeMapMapper.findAllResources(),selectedIdList);
    }

    @Override
    public List<Map<String, Object>> findUserResources(String account) {
        return iterate(treeMapMapper.findUserResources(account),null);
    }

    @Override
    public List<FrontRoute> findAllFrontRoutes() {
        return treeMapMapper.findAllFrontRoutes();
    }

    /**
     * 查找list中的根节点，然后进行递归
     * @param list
     * @return
     */
    private List<Map<String,Object>> iterate(List<Map<String,Object>> list,List<Integer> selectedIdList){
        // 根节点
        List<Map<String,Object>> root = new ArrayList<>();
        // 寻找所有的根节点，并进行进一步迭代
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                Map<String,Object> node = list.get(i);
                if(node.get("parent")==null || node.get("parent").equals(new Integer(0))){
                    root.add(node);
                    // 判断是否选定
                    if(selectedIdList!=null && selectedIdList.size()>0){
                        if(selectedIdList.contains(node.get("id"))){
                            node.put("selected","selected");
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
    private void iterate(Map<String,Object> parentNode, List<Map<String,Object>> list,List<Integer> selectedIdList){
        if(list.size()>0) {
            for (int i=0;i<list.size();i++) {
                Map<String,Object> node = list.get(i);
                if (node.get("parent")!=null && node.get("parent").equals(parentNode.get("id"))) {
                    List<Map<String,Object>> children = (List<Map<String, Object>>) parentNode.get("children");
                    if(children==null){
                        children = new ArrayList<>();
                        parentNode.put("children",children);
                    }
                    children.add(node);
                    // 判断是否选定
                    if(selectedIdList!=null && selectedIdList.size()>0){
                        if(selectedIdList.contains(node.get("id"))){
                            node.put("selected","selected");
                        }
                    }
                    iterate(node,list,selectedIdList);
                }
            }
        }
    }

    public TreeMapMapper getTreeMapMapper() {
        return treeMapMapper;
    }

    public void setTreeMapMapper(TreeMapMapper treeMapMapper) {
        this.treeMapMapper = treeMapMapper;
    }
}
