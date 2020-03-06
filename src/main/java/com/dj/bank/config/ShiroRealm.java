package com.dj.bank.config;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.BankUser;
import com.dj.bank.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * @ProjectName: pms
 * @Package: com.dj.pms.config
 * @ClassName: ShiroRealm
 * @Author: Administrator
 * @Description:
 * @Date: 2020/2/19 12:02
 * @Version: 1.0
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 得到用户名
        String userName = (String) authenticationToken.getPrincipal();
        // 得到密码
        String password = new String((char[]) authenticationToken.getCredentials());

        try {

            QueryWrapper<BankUser> wrapper = new QueryWrapper<BankUser>();
            wrapper.or(i -> i.eq("user_name", userName)
                    .or().eq("email", userName)
                    .or().eq("phone", userName));
            wrapper.eq("password", password);
            BankUser user = userService.getOne(wrapper);
            if (null == user || user.getIsDel() == 2) {
                throw new AccountException(SystemConstant.IS_DEL_NOT);
            }

            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user", user);

        } catch (Exception e) {
            throw new AccountException(e.getMessage());
        }
        return new SimpleAuthenticationInfo(userName, password, getName());
    }
}
