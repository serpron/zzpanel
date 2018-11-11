package zz.service;

import zz.entity.Role;
import zz.entity.TreeNode;
import zz.entity.User;
import zz.util.Page;

import java.util.List;
import java.util.Map;

public interface RoleService {
    Page<Role> find(Role example, int page, int rows);
    Role add(Role Role);
    Role update(Role Role);
    void delete(Integer id);
    Role findById(Integer id);
    List<Map<String,Object>> findRoleUsers(int roleid);
    List<Map<String,Object>> findRoleResources(int roleid);
}
