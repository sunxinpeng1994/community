package top.simplelife42.community.dto;

import lombok.Data;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/26/2020 11:35 AM
 **/
@Data
public class NotificationDTO {
    private Long id;
    private Long notifier;
    private String outerTitle;
    private String notifierName;
    private Integer type;
    private String typeName;
    private Long gmtCreate;
    private Integer status;
    private Long outerid;
}
