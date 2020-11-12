package cuit.hotel.common;


public enum StatusEnum {
    SUCCESS(200, "成功"),
    CREATED(201,"创建/修改成功"),
    NO_CONTENT(204,"删除成功"),
    ERROR(400, "请求失败"),
    NO_PERMISSION(403, "没有权限"),
    NOT_FOUND(404,"url不存在"),
    SERVER_ERROR(500,"服务器出错");

    private Integer status;
    private String msg;

    StatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
