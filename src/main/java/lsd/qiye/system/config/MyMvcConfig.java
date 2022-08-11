package lsd.qiye.system.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main.html").setViewName("homePage");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/").setViewName("index");
        //右侧框架初始界面重定向
        registry.addViewController("/iframeRight.html").setViewName("welcome");
        //右侧个人信息重定向
        registry.addViewController("/info.html").setViewName("info");
        //右侧修改密码重定向
        registry.addViewController("/pwd.html").setViewName("pwd");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/index.html","/login","/css/**","/js/**","/images/**");
    }
}
