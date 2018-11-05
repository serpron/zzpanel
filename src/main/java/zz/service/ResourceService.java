package zz.service;

import zz.entity.Resource;
import zz.entity.TreeNode;
import zz.util.Page;

import java.util.List;
import java.util.Map;

public interface ResourceService {
    Page<Resource> find(Resource example, int page, int rows);
    List<Resource> find(Resource example);
    List<Map<String,Object>> findSelectInfo(Resource example);
    Resource add(Resource resource);
    Resource update(Resource resource);
    void delete(Integer id);
    Resource findById(Integer id);
    List<TreeNode> findResourcesWithTree(Integer id);
}