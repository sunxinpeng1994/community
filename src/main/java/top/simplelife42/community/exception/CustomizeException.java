package top.simplelife42.community.exception;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/11/2020 12:18 PM
 **/
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;
    public CustomizeException(String message) {
        this.message = message;
    }
    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
    public String getMessage(){
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
