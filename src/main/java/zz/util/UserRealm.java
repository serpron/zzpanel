package zz.util;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import zz.entity.User;
import zz.service.UserService;

public class UserRealm extends AuthorizingRealm {
    private UserService userService;
    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 获取用户账户
        String account = (String) authenticationToken.getPrincipal();
        // 根据账户在数据库中查找用户对象
        User user = this.userService.findByAccount(account);
        if(user==null){
            throw new UnknownAccountException("用户不存在");
        }
        if(user.getState()==2){
            throw new LockedAccountException("账户已锁定");
        }
        // 返回包含用户名、加密密码、盐的AuthenticationInfo对象，交给CredentialsMatcher进行密码匹配
        SimpleByteSource salt = SimpleByteSource.bytes(userService.generateSaltString(account,user.getSalt()));
        return new SimpleAuthenticationInfo(user.getAccount(),user.getPass(),salt,getName());
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String account = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(userService.findAllRoles(account));
        simpleAuthorizationInfo.setStringPermissions(userService.findAllPermissions(account));
        return simpleAuthorizationInfo;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
