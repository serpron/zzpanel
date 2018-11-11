package zz.service;

import zz.entity.FrontRoute;
import zz.entity.User;
import zz.util.Page;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
    User findByAccount(String account);
    List<User> find(User example);
    Page<User> find(User example,int page,int rows);
    User login(String account,String pass);
    void changePass(String account,String pass);
    User add(User user);
    User findById(Integer id);
    String generateSaltString(String username,String salt);
    String generateHex(String msg,String salt);
    String generateSalt(String msg);
    User update(User user);
    void delete(Integer id);
    List<Map<String,Object>> findSelectInfo(User example);
    List<Map<String,Object>> findDepartmentsWithTree(Integer id);
    Set<String> findAllRoles(String account);
    Set<String> findAllPermissions(String account);
    boolean checkPass(String pass, String salt, String dbPass);
    List<Map<String,Object>> findMenuTree(String account);
    List<FrontRoute> findAllFrontRoutes();
}
