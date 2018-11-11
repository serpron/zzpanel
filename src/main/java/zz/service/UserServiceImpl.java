package zz.service;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zz.dao.UserMapper;
import zz.entity.FrontRoute;
import zz.entity.User;
import zz.util.Page;

import java.text.SimpleDateFormat;
import java.util.*;

@Transactional(readOnly = true)
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TreeMapService treeNodeService;

    static final SimpleDateFormat DF = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    @Override
    public User findByAccount(String account) {
        User user = this.userMapper.findByAccount(account);
        return user;
    }

    @Override
    public List<User> find(User example) {
        return this.userMapper.findByExample(example);
    }

    @Override
    public Page<User> find(User example,int page,int rows) {
        int count = this.userMapper.count(example);
        Page<User> pageBean = new Page<>(count, page, rows);
        if(count>0) {
            List<User> list = this.userMapper.findByExampleWithPage(example, pageBean.getStart(), pageBean.getRows());
            pageBean.setList(list);
        }
        return pageBean;
    }

    @Override
    public User login(String account, String pass) {
        // 根据用户账户查找用户
        User example = new User();
        example.setAccount(account);
        User user = this.userMapper.findByExampleWithBean(example);
        if(user==null) return null;
        // 比较密码
        String inPass = generateHex(pass,user.getSalt());
        // 如果传入的密码与数据库存储的密码相同
        if(inPass.equals(user.getPass())){
            return user;
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void changePass(String account, String pass) {
        User user = new User();
        user.setAccount(account);
        String salt = generateSalt(pass);  // 根据明文密码产生公盐
        pass = generateHex(pass,generateSaltString(account,salt)); // 结合明文密码与盐产生密文密码
        user.setPass(pass);
        user.setSalt(salt);
        this.userMapper.update(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public User add(User user) {
        String salt = generateSalt(user.getPass());  // 根据明文密码产生盐
        String pass = generateHex(user.getPass(),generateSaltString(user.getAccount(),salt)); // 结合明文密码与私盐产生密文密码
        user.setPass(pass);
        user.setSalt(salt);
        user.setRegister_time(new Date());
        this.userMapper.add(user);
        return user;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public User update(User user) {
        // 为用户重新生成盐和密码
        String salt = generateSalt(user.getPass());  // 根据明文密码产生公盐
        String pass = generateHex(user.getPass(),
                ByteSource.Util.bytes(generateSaltString(user.getAccount(),salt)).toHex()); // 结合明文密码与私盐产生密文密码
        user.setPass(pass);
        user.setSalt(salt);
        this.userMapper.update(user);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    @Override
    public void delete(Integer id) {
        this.userMapper.delete(id);
    }

    @Override
    public List<Map<String, Object>> findSelectInfo(User example) {
        return this.userMapper.findSelctInfoByExample(example);
    }

    @Override
    public List<Map<String,Object>> findDepartmentsWithTree(Integer id) {
        List<Integer> selectedIdList = new ArrayList<>();
        if(id!=null) {
            User user = this.userMapper.findById(id);
            selectedIdList.add(user.getDepartment_id());
        }
        return this.treeNodeService.generateDepartmentTree(selectedIdList);
    }

    @Override
    public Set<String> findAllRoles(String account) {
        return userMapper.findAllRoles(account);
    }

    @Override
    public Set<String> findAllPermissions(String account) {
        return userMapper.findAllPermissions(account);
    }

    @Override
    public boolean checkPass(String pass, String salt, String dbPass) {
        String securityPass = generateHex(pass,salt);
        return securityPass.equals(dbPass);
    }

    @Override
    public List<Map<String,Object>> findMenuTree(String account) {
        return this.treeNodeService.findUserResources(account);
    }

    @Override
    public List<FrontRoute> findAllFrontRoutes() {
        return this.treeNodeService.findAllFrontRoutes();
    }

    @Override
    public User findById(Integer id) {
        return this.userMapper.findById(id);
    }

    /**
     * 根据字符串和私盐生成加密字符串
     * @param salt
     * @return
     */
    public String generateHex(String pass,String salt){
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource(salt));
        hashService.setHashIterations(2);
        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("SHA-512")
                .setSource(ByteSource.Util.bytes(pass))
                .setSalt(ByteSource.Util.bytes(salt))
                .setIterations(2)
                .build();
        return hashService.computeHash(request).toHex();
    }

    /**
     * 使用密码与时间字符串随机生成盐
     * @return
     */
    public String generateSalt(String msg){
        SecureRandomNumberGenerator randomNumberGenerator =
                new SecureRandomNumberGenerator();
        randomNumberGenerator.setSeed((DF.format(new Date())+msg).getBytes());
        return randomNumberGenerator.nextBytes().toHex();
    }

    // 用户名与盐进行连接组成私盐
    public String generateSaltString(String username,String salt){
        return username + salt;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public TreeMapService getTreeNodeService() {
        return treeNodeService;
    }

    public void setTreeNodeService(TreeMapService treeNodeService) {
        this.treeNodeService = treeNodeService;
    }
}
