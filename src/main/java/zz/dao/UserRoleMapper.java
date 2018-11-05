package zz.dao;

import zz.entity.User;
import zz.entity.UserRole;
import zz.util.BaseDao;
import java.util.List;

public interface UserRoleMapper extends BaseDao<UserRole,Integer> {
    List<Integer> findByRoleId(Integer roleid);
}
