package life.majiang.community.controller;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        //第一次访问主页时，查询请求域的cookie中是否已有token，来验证是否已有登录信息
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie :
                    cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();  //如果有，取得token的值
                    User user = userMapper.findByToken(token);  //已token为条件从数据库中查找user
                    if (user != null) {
                        //查找结果不为null，就在服务器session域中加入该user对象
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        //如果cookie中没有token，或者数据库中没有该token对应的user对象，就返回初始页面
        return "index";
    }
}
