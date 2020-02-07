package top.simplelife42.community.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import top.simplelife42.community.mapper.UserMapper;
import top.simplelife42.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;


    //访问首页，循环cookie，看cookie中的token是否在数据库中存在，若存在，说明用户仍然可以登陆，直接跳转
    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie != null && cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }


        return "index";
    }
}
