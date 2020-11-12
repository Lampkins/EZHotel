package cuit.hotel.common;

/**
 * 返回结果工具类，用于ajax、REST等类型的web服务
 */
public class ResultUtils {

    /**
     * 成功：StatusEnum(200, "成功")
     */
    public static Result success(){
        return success(StatusEnum.SUCCESS);
    }

    /**
     * 成功：StatusEnum
     */
    public static Result success(StatusEnum statusEnum){
        return success(statusEnum, null);
    }
    /**
     * 成功：StatusEnum 数据
     */
    public static Result success(StatusEnum statusEnum, Object data){
        Result result = new Result();
        result.setStatus(statusEnum.getStatus());
        result.setMessage(statusEnum.getMsg());
        result.setData(data);
        return result;
    }
    /**
     * 成功：200 自定义消息
     */
    public static Result success(String msg){
        return success(msg, null);
    }
    /**
     * 成功：200 "成功" 数据
     */
    public static Result success(Object data){
        return success(StatusEnum.SUCCESS.getMsg(), data);
    }
    /**
     * 成功：200 自定义消息 数据
     */
    public static Result success(String msg, Object data){
        Result result = new Result();
        result.setStatus(StatusEnum.SUCCESS.getStatus());
        result.setMessage(msg);
        result.setData(data);
        return result;
    }


    /**
     * 失败 默认 (400, "失败")
     */
    public static Result failure(){
        return failure(StatusEnum.ERROR);
    }
    /**
     * 失败 StatusEnum
     */
    public static Result failure(StatusEnum statusEnum){
        return failure(statusEnum, statusEnum.getMsg());
    }

    /**
     * 失败 默认状态码400 自定义消息
     * @param msg 消息
     */
    public static Result failure(String msg){
        return failure(StatusEnum.ERROR, msg);
    }

    /**
     * 失败 状态码 自定义消息
     * @param statusEnum 失败类型
     * @param msg 消息
     */
    public static Result failure(StatusEnum statusEnum, String msg){
        Result result = new Result();
        result.setStatus(statusEnum.getStatus());
        result.setMessage(msg);
        result.setData(null);
        return result;
    }

    public static Result custom(Integer status, String msg, Object data) {
        Result result = new Result();
        result.setStatus(status);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }
}
