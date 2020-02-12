package top.simplelife42.community.dto;

import lombok.Data;
import top.simplelife42.community.exception.CustomizeErrorCode;
import top.simplelife42.community.exception.CustomizeException;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/12/2020 1:31 PM
 **/
@Data
public class ResultDTO {
    private Integer code;
    private String message;
    public static ResultDTO errorOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }
    public static ResultDTO errorOf(CustomizeException e) {
        return errorOf(e.getCode(), e.getMessage());
    }
    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }


}
