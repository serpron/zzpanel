package zz.dao;

import zz.entity.FrontRoute;
import zz.util.BaseDao;

import java.util.List;
import java.util.Map;

public interface TreeMapMapper extends BaseDao<Map<String,Object>,Integer> {
    List<Map<String,Object>> findAllDepartments();
    List<Map<String,Object>> findAllResources();
    List<Map<String,Object>> findUserResources(String account);
    List<FrontRoute> findAllFrontRoutes();
}
