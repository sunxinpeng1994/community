package top.simplelife42.community.dto;

import lombok.Data;
import top.simplelife42.community.model.User;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/24/2020 4:38 PM
 **/
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
    private Integer commentCount;
}
