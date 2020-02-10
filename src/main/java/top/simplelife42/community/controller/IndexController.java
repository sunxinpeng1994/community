package top.simplelife42.community.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.simplelife42.community.dto.PaginationDTO;
import top.simplelife42.community.service.QuestionService;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    //访问首页，循环cookie，看cookie中的token是否在数据库中存在，若存在，说明用户仍然可以登陆，直接跳转
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name="page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "5") Integer size){
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        System.out.println("index page ");

        return "index";
    }
}
