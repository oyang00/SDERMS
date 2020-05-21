package com.ouyang.base.wrap;


import com.ouyang.base.constant.CustomConstant;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2019
 * @date 2019/11/15 10:18
 * @Description TODO
 * 返回方法封装
 */
public class WrapMapper {
    private WrapMapper() {

    }

    /**
     * 成功封装
     *
     * @param msg
     * @param data
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> ok(int code, String msg, E data) {
        return new Wrapper<E>(code, msg, data);
    }

    /**
     * 成功封装
     *
     * @param msg
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> ok(int code, String msg) {
        return new Wrapper<E>(code, msg, null);
    }

    /**
     * 成功封装
     *
     * @param msg
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> okExec(String msg) {
        return new Wrapper<E>(CustomConstant.Wrap.CODE_EXEC_SUCCESS, msg, null);
    }

    /**
     * 成功封装
     *
     * @param msg
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> okExec(String msg, E data) {
        return new Wrapper<E>(CustomConstant.Wrap.CODE_EXEC_SUCCESS, msg, data);
    }

    /**
     * 成功封装
     *
     * @param msg
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> okObtain(String msg, E data) {
        return new Wrapper<E>(CustomConstant.Wrap.CODE_OBTAIN_SUCCESS, msg, data);
    }


    /**
     * 错误封装
     *
     * @param msg
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> error(int code, String msg) {
        return new Wrapper<E>(code, msg, null);
    }

    /**
     * 错误封装
     *
     * @param msg
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> errorExec(String msg) {
        return new Wrapper<E>(CustomConstant.Wrap.CODE_EXEC_FAILURE, msg, null);
    }

    /**
     * 错误封装
     *
     * @param msg
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> errorObtain(String msg) {
        return new Wrapper<E>(CustomConstant.Wrap.CODE_OBTAIN_FAILURE, msg, null);
    }
}
