package cuit.hotel.common;

import java.io.Serializable;

public class Result implements Serializable {
    private Integer status; // 异常状态码码
    private String message; // 提示信息
    private Object data; // 返回数据

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
