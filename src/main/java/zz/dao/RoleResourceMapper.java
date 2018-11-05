package zz.dao;

import zz.entity.RoleResource;
import zz.util.BaseDao;

import java.util.List;

public interface RoleResourceMapper extends BaseDao<RoleResource,Integer> {
    List<Integer> findByRoleId(int roleid);
}
