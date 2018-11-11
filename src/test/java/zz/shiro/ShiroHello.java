package zz.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShiroHello {
    Subject subject = null;
    @Before
    public void setup(){
        // 构建SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // 安全管理器
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        // 获取认证主体
        subject = SecurityUtils.getSubject();
        // 登录
        UsernamePasswordToken token = new UsernamePasswordToken("test","123");
        try {
            subject.login(token);
        }catch(Exception e){
            // 认证失败
            e.printStackTrace();
        }
        Assert.assertEquals(true,subject.isAuthenticated());
    }
    @Test
    public void checkPermissions(){
        Assert.assertTrue(subject.isPermitted("user:query"));
    }


}
