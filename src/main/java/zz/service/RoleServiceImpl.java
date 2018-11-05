package zz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zz.dao.*;
import zz.entity.*;
import zz.util.Page;

import java.util.*;

@Transactional(readOnly = true)
@Service("roleService")
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private TreeNodeService treeNodeService;

    @Override
    public Page<Role> find(Role example, int page, int rows) {
        int count = this.roleMapper.count(example);
        Page<Role> pageBean = new Page<>(count,page,rows);
        if(count>0){
            List<Role> list = this.roleMapper.findByExampleWithPage(example,pageBean.getStart(),pageBean.getRows());
            pageBean.setList(list);
        }
        return pageBean;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public Role add(Role entity) {
        entity.setCreate_time(new Date());
        this.roleMapper.add(entity);
        // 添加角色用户关联
        if(entity.getUsers()!=null && entity.getUsers().length>0){
            for(int i=0;i<entity.getUsers().length;i++) {
                Integer userId = entity.getUsers()[i];
                UserRole ur = new UserRole();
                ur.setUser_id(userId);
                ur.setRole_id(entity.getId());
                this.userRoleMapper.add(ur);
            }
        }
        // 添加角色资源关联
        if(entity.getResources()!=null && entity.getResources().length>0){
            for(int i=0;i<entity.getResources().length;i++){
                Integer resourceId = entity.getResources()[i];
                RoleResource rr = new RoleResource();
                rr.setResource_id(resourceId);
                rr.setRole_id(entity.getId());
                this.roleResourceMapper.add(rr);
            }
        }
        return entity;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public Role update(Role role) {
        this.roleMapper.update(role);
        if(role.getUsers()!=null && role.getUsers().length>0){
            // 数据库存储的角色用户
            List<Integer> dbUsers = this.userRoleMapper.findByRoleId(role.getId());
            List<Integer> users = Arrays.asList(role.getUsers());
            for(Integer userId : role.getUsers()){
                // 数据库无、web有，则添加
                if(!dbUsers.contains(userId)){
                    UserRole r = new UserRole();
                    r.setRole_id(role.getId());
                    r.setUser_id(userId);
                    this.userRoleMapper.add(r);
                }
            }
            for(Integer dbUserId : dbUsers){
                // 数据库有、web无，则删除
                if(!users.contains(dbUserId)){
                    UserRole r = new UserRole();
                    r.setUser_id(dbUserId);
                    r.setRole_id(role.getId());
                    this.userRoleMapper.deleteByEntity(r);
                }
            }
        }
        if(role.getResources()!=null && role.getResources().length>0){
            List<Integer> dbResources = this.roleResourceMapper.findByRoleId(role.getId());
            List<Integer> resources = Arrays.asList(role.getResources());
            for(Integer resourceId : resources){
                // 数据库无，web有，则添加
                if(!dbResources.contains(resourceId)){
                    RoleResource roleResource = new RoleResource();
                    roleResource.setRole_id(role.getId());
                    roleResource.setResource_id(resourceId);
                    this.roleResourceMapper.add(roleResource);
                }
            }
            for(Integer dbResourceId : dbResources){
                if(!resources.contains(dbResourceId)){
                    RoleResource roleResource = new RoleResource();
                    roleResource.setResource_id(dbResourceId);
                    roleResource.setRole_id(role.getId());
                    this.roleResourceMapper.deleteByEntity(roleResource);
                }
            }
        }
        return role;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void delete(Integer id) {
        UserRole userRole = new UserRole();
        userRole.setRole_id(id);
        // 删除中间表信息
        this.userRoleMapper.deleteByEntity(userRole);
        RoleResource roleResource = new RoleResource();
        roleResource.setRole_id(id);
        this.roleResourceMapper.deleteByEntity(roleResource);
        // 删除角色
        this.roleMapper.delete(id);
    }

    @Override
    public Role findById(Integer id) {
        return roleMapper.findById(id);
    }

    @Override
    public List<Map<String,Object>> findRoleUsers(int roleid) {
        List<Integer> selectUserList = userRoleMapper.findByRoleId(roleid);
        List<User> allUserList = userMapper.findByExample(null);
        List<Map<String,Object>> result = new ArrayList<>();
        for(User user : allUserList){
            Map<String,Object> item = new HashMap<>();
            item.put("name",user.getName());
            item.put("value",user.getId());
            if(selectUserList!=null && selectUserList.contains(user.getId())){
                item.put("selected","selected");
            }
            result.add(item);
        }
        return result;
    }

    @Override
    public List<TreeNode> findRoleResources(int roleid) {
        List<Integer> selectResourcesList = roleResourceMapper.findByRoleId(roleid);
        return this.treeNodeService.generateResourcesTree(selectResourcesList);
    }

    public RoleMapper getRoleMapper() {
        return roleMapper;
    }

    public void setRoleMapper(RoleMapper RoleMapper) {
        this.roleMapper = RoleMapper;
    }

    public UserRoleMapper getUserRoleMapper() {
        return userRoleMapper;
    }

    public void setUserRoleMapper(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public RoleResourceMapper getRoleResourceMapper() {
        return roleResourceMapper;
    }

    public void setRoleResourceMapper(RoleResourceMapper roleResourceMapper) {
        this.roleResourceMapper = roleResourceMapper;
    }

    public ResourceMapper getResourceMapper() {
        return resourceMapper;
    }

    public void setResourceMapper(ResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }

    public TreeNodeService getTreeNodeService() {
        return treeNodeService;
    }

    public void setTreeNodeService(TreeNodeService treeNodeService) {
        this.treeNodeService = treeNodeService;
    }
}
