package zz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zz.dao.ResourceMapper;
import zz.entity.Resource;
import zz.entity.TreeNode;
import zz.util.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService{

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private TreeMapService treeNodeService;

    @Override
    public Page<Resource> find(Resource example, int page, int rows) {
        int count = this.resourceMapper.count(example);
        Page<Resource> pageBean = new Page<>(count,page,rows);
        if(count>0){
            List<Resource> list = this.resourceMapper.findByExampleWithPage(example,pageBean.getStart(),pageBean.getRows());
            pageBean.setList(list);
        }
        return pageBean;
    }

    @Override
    public List<Resource> find(Resource example) {
        return this.resourceMapper.findByExample(example);
    }

    @Override
    public List<Map<String, Object>> findSelectInfo(Resource example) {
        return this.resourceMapper.findSelctInfoByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public Resource add(Resource resource) {
        // 查询父类的路径
        Integer parent_id = resource.getParent_id();
        Resource parent = null;
        if(parent_id!=null){
            parent = resourceMapper.findById(parent_id);
        }
        this.resourceMapper.add(resource);
        if(parent!=null) {
            String parent_ids = parent.getParent_ids() + "/" + resource.getId();
            resource.setParent_ids(parent_ids);
            this.resourceMapper.update(resource);
        }
        return resource;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public Resource update(Resource resource) {
        this.resourceMapper.update(resource);
        return resource;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void delete(Integer id) {
        this.resourceMapper.delete(id);
    }

    @Override
    public Resource findById(Integer id) {
        return resourceMapper.findById(id);
    }

    @Override
    public List<Map<String,Object>> findResourcesWithTree(Integer id) {
        List<Integer> selectedIdList = new ArrayList<>();
        if(id!=null){
            Resource resource = this.resourceMapper.findById(id);
            selectedIdList.add(resource.getId());
        }
        return treeNodeService.generateResourcesTree(selectedIdList);
    }

    public ResourceMapper getResourceMapper() {
        return resourceMapper;
    }

    public void setResourceMapper(ResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }

    public TreeMapService getTreeNodeService() {
        return treeNodeService;
    }

    public void setTreeNodeService(TreeMapService treeNodeService) {
        this.treeNodeService = treeNodeService;
    }
}
