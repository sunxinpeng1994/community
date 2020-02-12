package top.simplelife42.community.exception;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/11/2020 12:34 PM
 **/
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不在了，要不要换个试试？");
    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
