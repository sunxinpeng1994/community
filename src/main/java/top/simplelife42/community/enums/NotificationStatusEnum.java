package top.simplelife42.community.enums;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/26/2020 11:13 AM
 **/
public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1)
    ;

    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
