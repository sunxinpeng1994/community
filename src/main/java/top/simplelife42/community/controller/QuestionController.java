package top.simplelife42.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.simplelife42.community.dto.QuestionDTO;
import top.simplelife42.community.mapper.QuestionMapper;
import top.simplelife42.community.service.QuestionService;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/10/2020 11:26 AM
 **/
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){

        questionService.incView(id);
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
