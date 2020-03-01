package top.simplelife42.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.simplelife42.community.dto.FileDTO;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/28/2020 11:09 PM
 **/
@Controller
public class FileController {

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload() {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/img/myLogo.jpg");

        return fileDTO;
    }
}
