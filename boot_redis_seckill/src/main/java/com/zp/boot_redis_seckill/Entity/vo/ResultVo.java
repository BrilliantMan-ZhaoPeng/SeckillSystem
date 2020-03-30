package com.zp.boot_redis_seckill.Entity.vo;

/**
 * @author zhaopeng
 * @create 2020-03-28 20:29
 */
public class ResultVo {
    //响应是否成功
    private boolean flag;
    //携带的响应的消息
    private String message;
    //返回响应的数据
    private Object data;

    public ResultVo(boolean flag,String message) {
       this.flag=flag;
       this.message=message;
    }

    public ResultVo(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isFlag() {

        return flag;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
