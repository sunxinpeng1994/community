package top.simplelife42.community.exception;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/11/2020 12:18 PM
 **/
public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(String message) {
        this.message = message;
    }
    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }
    public String getMessage(){
        return message;
    }
}
