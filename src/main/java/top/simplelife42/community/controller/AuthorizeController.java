package top.simplelife42.community.controller;


import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.simplelife42.community.dto.AccesstokenDTO;
import top.simplelife42.community.dto.GithubUser;
import top.simplelife42.community.provider.GithubProvider;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accesstokenDTO.setState(state);
        accesstokenDTO.setClient_id("c5da5eca0acb859f0405");
        accesstokenDTO.setClient_secret("b60578dfc4c56dba727d48ea456f60b70380f3f9");
        String accessToken = githubProvider.getAccesstoken(accesstokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.print(user.getName());
        return "index";
    }
}
