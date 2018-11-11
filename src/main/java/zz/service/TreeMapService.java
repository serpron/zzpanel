package zz.service;

import zz.entity.FrontRoute;
import java.util.List;
import java.util.Map;

public interface TreeMapService {
    /**
     * 将一个普通的节点列表转换为树形结构
     * @return
     */
    List<Map<String,Object>> generateDepartmentTree(List<Integer> selectedIdList);

    List<Map<String,Object>> generateResourcesTree(List<Integer> selectedIdList);

    List<Map<String,Object>> findUserResources(String account);

    List<FrontRoute> findAllFrontRoutes();
}
