package com.cy.pj.sys.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class SpringShiroConfig {

    /**
     * <p>shiro框架的和兴安全管理器对象</p>
     * <p>-@Bean注解描述的的方法,器返回值会交给spring管理,spring存储</p>
     *
     * @return 安全管理器对象
     */
    @Bean
    public SecurityManager securityManager(Realm realm, CacheManager cacheManager,RememberMeManager rememberMeManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setCacheManager(cacheManager);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全
        factoryBean.setSecurityManager(securityManager);
        // 设置认证url(哪些资源可以无需认证(anon),哪些需要认证(authc))
        factoryBean.setLoginUrl("/login");
        //设置过滤规则
        Map<String, String> map = new LinkedHashMap<>();
        // 静态资源允许匿名访问,即不需要认证即可访问:"anon
        map.put("/bower_components/**", "anon");
        map.put("/build/**", "anon");
        map.put("/dist/**", "anon");
        map.put("/plugins/**", "anon");
        map.put("/user/doLogin", "anon");
        map.put("/user/doLogout", "logout");
        // 其他都要认证资源:"authc"
//        map.put("/**", "authc");
        // 使用rememberMe功能时需要使用user
        map.put("/**","user");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor newAuthorizationAttributeSourceAdvisor(SecurityManager securityManager) {

        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);

        return authorizationAttributeSourceAdvisor;

    }

    /**
     * 记住登录状态配置
     * @return remember
     */
    @Bean
    public RememberMeManager shiroRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setMaxAge(10*60*60);
        cookieRememberMeManager.setCookie(cookie);
        return  cookieRememberMeManager;
    }

}
