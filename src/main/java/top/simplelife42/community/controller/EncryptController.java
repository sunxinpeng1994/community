package top.simplelife42.community.controller;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 3/3/2020 5:33 PM
 **/

@Controller
public class EncryptController {

    @Autowired
    private StringEncryptor encryptor;

    /**
     * 测试jasypt加密解密
     */
    @GetMapping("/jay")
    public void testJasypt() {
        String key = "AKIDShiUcVcZicGl19sv2vb0BIyPaXEgnykH";
        String encryptPwd = encryptor.encrypt(key);
        System.out.println("id 加密:：" + encryptPwd);

        String key1 = "QddON8fxxjmeGemhNq66sSovoRpyrV0G";
        String encryptPwd1 = encryptor.encrypt(key1);
        System.out.println("key 加密:：" + encryptPwd1);



    }

}