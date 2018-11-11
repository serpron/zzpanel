package zz.dao;

import zz.entity.Resource;
import zz.entity.User;
import zz.util.BaseDao;

import java.util.List;
import java.util.Set;

public interface UserMapper extends BaseDao<User,Integer> {
    Set<String> findAllRoles(String account);
    Set<String> findAllPermissions(String account);
    User findByAccount(String account);
}
