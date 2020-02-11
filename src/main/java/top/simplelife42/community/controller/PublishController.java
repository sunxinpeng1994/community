package top.simplelife42.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.simplelife42.community.dto.QuestionDTO;
import top.simplelife42.community.mapper.QuestionMapper;
import top.simplelife42.community.model.Question;
import top.simplelife42.community.model.User;
import top.simplelife42.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")String id,
                       Model model){
        QuestionDTO question = questionService.getById(Integer.parseInt(id));
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("questionId",question.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public  String publish(Model model){

        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam("questionId") Integer questionId,
            HttpServletRequest request,
            Model model){
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if(title == null || title.length() == 0){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if(description == null || description.length() == 0){
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if(tag == null || tag.length() == 0){
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = (User)request.getSession().getAttribute("user");

        if(user == null) {
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(questionId);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}

