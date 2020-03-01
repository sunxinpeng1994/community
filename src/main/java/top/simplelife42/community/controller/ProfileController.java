package top.simplelife42.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import top.simplelife42.community.dto.PaginationDTO;
import top.simplelife42.community.model.User;
import top.simplelife42.community.service.NotificationService;
import top.simplelife42.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name="action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name="page",defaultValue = "1") Integer page,
                          @RequestParam(name="size",defaultValue = "5") Integer size){

        User user = (User)request.getSession().getAttribute("user");
        if(user == null) {
            return "redirect:/";
        }
        if(action.equals("questions")) {
//            Long unreadCount = notificationService.unreadCount(user.getId());
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName","我的提问");
            PaginationDTO pagination = questionService.list(user.getId(), page, size);
            model.addAttribute("pagination", pagination);
//            model.addAttribute("unreadCount", unreadCount);
        } else if (action.equals("replies")){
            PaginationDTO paginationDTO = notificationService.list(user.getId(), page, size);
//            Long unreadCount = notificationService.unreadCount(user.getId());
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName","最新回复");
            model.addAttribute("pagination", paginationDTO);
//            model.addAttribute("unreadCount", unreadCount);
        }

        return "profile";
    }
}
