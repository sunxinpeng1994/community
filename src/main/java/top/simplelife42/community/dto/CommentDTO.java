package top.simplelife42.community.dto;

import lombok.Data;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/12/2020 12:11 PM
 **/
@Data
public class CommentDTO {
    private long parentId;
    private String content;
    private Integer type;
}
