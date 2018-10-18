package zz.service;

import zz.entity.User;
import zz.util.Page;

import java.util.List;

public interface UserService {
    Page<User> find(User example,int page,int rows);
    User login(String account,String pass);
    void changePass(String account,String pass);
    User add(User user);
    User findById(Integer id);
    String generateHex(String msg,String salt);
    String generateSalt(String msg);
    User update(User user);
    void delete(Integer id);
}
