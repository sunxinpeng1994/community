package top.simplelife42.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.simplelife42.community.dto.FileDTO;
import top.simplelife42.community.provider.TcloudProvider;

import javax.servlet.http.HttpServletRequest;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/28/2020 11:09 PM
 **/
@Controller
public class FileController {
    @Autowired
    private TcloudProvider tcloudProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        /*
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setMessage("upload work");
        fileDTO.setUrl("/img/myLogo.jpg");
        return fileDTO;
        */


        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
//        TcloudProvider util = new TcloudProvider();
        TcloudProvider util = tcloudProvider;
        String name = null;
        try {
            name = util.uploadFile2Cos(file);
            String url = util.getImgUrl(name);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl(url);
            return fileDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/img/avatar.png");
        return fileDTO;


    }
}
