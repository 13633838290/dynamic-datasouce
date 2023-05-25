package com.yang.dynamicDatasourceService.commons;

import java.util.HashMap;
import java.util.Map;

/**
 * JSON 返回结果Bean
 *
 * @author yangyang
 * @date 2020年6月18日
 */
public final class JsonResponse<T> {

    /**
     * 请求结果
     */
    private String state;
    /**
     * 响应代码
     */
    private String errCode;
    /**
     * 描述信息
     */
    private String errMsg;
    /**
     * 响应数据
     */
    private T outData;

    public JsonResponse() {
    }

    private JsonResponse(String state, String errCode, String errMsg, T outData) {
        super();
        this.state = state;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.outData = outData;
    }

    public String isState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getOutData() {
        return outData;
    }

    public void setOutData(T outData) {
        this.outData = outData;
    }

    // 常用工厂方法
    public static JsonResponse success() {
        return JsonResponse.create(Constants.SUCCESSFUL_STATUS, "1", "成功", null);
    }

    public static <T> JsonResponse<T> success(T data) {
        return JsonResponse.create(Constants.SUCCESSFUL_STATUS, "1", "成功", data);
    }

    public static <T> JsonResponse<T> success(String msg, T data) {
        return JsonResponse.create(Constants.SUCCESSFUL_STATUS, "1", msg, data);
    }

    public static <T> JsonResponse<Map<T, T>> map(T... elements) {
        if (elements.length % 2 != 0) {
            throw new IllegalArgumentException("Length is illegal.");
        }

        Map<T, T> map = new HashMap<T, T>();
        for (int i = 0; i < elements.length; i++) {
            map.put(elements[i], elements[++i]);
        }

        return JsonResponse.create(Constants.SUCCESSFUL_STATUS, "1", "成功", map);
    }

    public static JsonResponse fail() {
        return JsonResponse.create(Constants.FAULTED_STATUS, "E0001", "fail", null);
    }

    public static JsonResponse fail(String msg) {
        return JsonResponse.create(Constants.FAULTED_STATUS, "E0001", msg, null);
    }

    public static <T> JsonResponse<T> fail(String msg, T data) {
        return JsonResponse.create(Constants.FAULTED_STATUS, "E0001", msg, data);
    }
    
    public static <T> JsonResponse<T> create(String state, String code, String msg, T data) {
        return new JsonResponse<T>(state, code, msg, data);
    }

    public static JsonResponse create(String state, String code, String msg) {
        return create(state, code, msg, null);
    }

    public static JsonResponse create(String state, String code) {
        return create(state, code, null, null);
    }
    
}