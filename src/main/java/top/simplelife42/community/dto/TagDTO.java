package top.simplelife42.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/25/2020 2:51 PM
 **/
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
