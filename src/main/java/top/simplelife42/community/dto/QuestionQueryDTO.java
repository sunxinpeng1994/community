package top.simplelife42.community.dto;

import lombok.Data;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 3/4/2020 6:54 PM
 **/
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
