package top.simplelife42.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import top.simplelife42.community.dto.NotificationDTO;
import top.simplelife42.community.dto.PaginationDTO;
import top.simplelife42.community.enums.NotificationTypeEnum;
import top.simplelife42.community.mapper.NotificationMapper;
import top.simplelife42.community.model.User;
import top.simplelife42.community.service.NotificationService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/27/2020 11:28 PM
 **/
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name="id") Long id,
                          Model model,
                          HttpServletRequest request){

        User user = (User)request.getSession().getAttribute("user");
        if(user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id, user);
        if (NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getOuterid();
        } else if(NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getOuterid();
        }
        return "redirect:/";
    }
}
