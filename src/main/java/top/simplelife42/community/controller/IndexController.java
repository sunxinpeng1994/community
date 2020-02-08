package top.simplelife42.community.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.simplelife42.community.dto.QuestionDTO;
import top.simplelife42.community.mapper.QuestionMapper;
import top.simplelife42.community.mapper.UserMapper;
import top.simplelife42.community.model.Question;
import top.simplelife42.community.model.User;
import top.simplelife42.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    //访问首页，循环cookie，看cookie中的token是否在数据库中存在，若存在，说明用户仍然可以登陆，直接跳转
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){

        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    System.out.println("token is in cookie");
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        List<QuestionDTO> questionList = questionService.list();
        model.addAttribute("questions", questionList);


        return "index";
    }
}
