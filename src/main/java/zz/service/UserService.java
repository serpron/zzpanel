package zz.service;

import zz.entity.TreeNode;
import zz.entity.User;
import zz.util.Page;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> find(User example);
    Page<User> find(User example,int page,int rows);
    User login(String account,String pass);
    void changePass(String account,String pass);
    User add(User user);
    User findById(Integer id);
    String generateHex(String msg,String salt);
    String generateSalt(String msg);
    User update(User user);
    void delete(Integer id);
    List<Map<String,Object>> findSelectInfo(User example);
    List<TreeNode> findDepartmentsWithTree(Integer id);
}
