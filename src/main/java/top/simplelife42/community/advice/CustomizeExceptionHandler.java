package top.simplelife42.community.advice;

import org.springframework.http.HttpStatus;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.ModelAndView;
import top.simplelife42.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/11/2020 12:05 PM
 **/
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model) {
        if(e instanceof CustomizeException) {
            model.addAttribute("message", e.getMessage());
        } else {
            model.addAttribute("message", "服务器冒烟了，忙不过来啦！");
        }

        return new ModelAndView("error");

    }

}
