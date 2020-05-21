package com.ouyang.base.constant;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2019
 * @date 2019/11/15 10:25
 * @Description TODO
 * 自定义常量类
 */
public final class CustomConstant {
    private CustomConstant() {

    }

    /**
     * @author 16计算机 ouyang
     * @version 1.0
     * @copyright ：ouyang 版权所有 © 2019
     * @date 2019/11/15 10:27
     * @Description TODO
     * wrap 常量
     */
    public static final class Wrap {
        private Wrap() {

        }

        /**
         * 执行成功
         */
        public static final int CODE_EXEC_SUCCESS = 200;

        /**
         * 获取成功
         */
        public static final int CODE_OBTAIN_SUCCESS = 201;

        /**
         * 获取数据为空
         */
        public static final int CODE_OBTAIN_NULL = 202;

        /**
         * 执行成功
         */
        public static final int CODE_EXEC_FAILURE = 400;

        /**
         * 获取失败
         */
        public static final int CODE_OBTAIN_FAILURE = 401;

        /**
         * 参数校验未通过
         */
        public static final int CODE_PARAM_FAILURE = 402;

        /**
         * Token 校验未通过
         */
        public static final int CODE_TOKEN_FAILURE = 403;

        /**
         * 权限校验未通过
         */
        public static final int CODE_LEVEL_FAILURE = 404;

        /**
         * 请求方法错误
         */
        public static final int CODE_METHOD_FAILURE = 405;

        /**
         * 服务器异常
         */
        public static final int CODE_SERVER_EXCEPTION = 500;


        /**
         * 返回错误信息
         */
        public static final String MSG_ERROR = "服务器错误";

        /**
         * 返回成功信息
         */
        public static final String MSG_SUCCESS = "执行成功";

        /**
         * 返回验证未过信息
         */
        public static final String MSG_TOKEN_FAILED = "Token 过期";

        /**
         * 返回权限未过信息
         */
        public static final String MSG_LEVEL_FAILED = "权限不足";

        /**
         * 返回邮件成功信息
         */
        public static final String MSG_MAIL_SUCCESS = "邮件发送成功";

        /**
         * 返回邮件错误信息
         */
        public static final String MSG_MAIL_ERROR = "邮件发送错误";

        /**
         * 返回无参数信息
         */
        public static final String MSG_PARAM_EMPTY = "缺少参数";

        /**
         * 返回参数为空信息
         */
        public static final String MSG_PARAM_NULL = " 参数不能为空";

        /**
         * 返回参数邮件信息
         */
        public static final String MSG_PARAM_EMAIL = " 不是有效邮件格式";

        /**
         * 返回参数数字信息
         */
        public static final String MSG_PARAM_NUMBER = " 不是有效纯数字格式";

        /**
         * 返回参数手机信息
         */
        public static final String MSG_PARAM_PHONE = " 不是有效手机格式";

    }

    /**
     * @author 16计算机 ouyang
     * @version 1.0
     * @copyright ：ouyang 版权所有 © 2019
     * @date 2019/11/15 11:04
     * @Description TODO
     * Date 常量
     */
    public static final class Date {
        private Date() {

        }

        /**
         * 日期格式：yyyy-MM-dd HH:mm:ss
         */
        public static final String DATE_TYPE_01 = "yyyy-MM-dd HH:mm:ss";

        /**
         * 日期格式：yyyy-MM-dd
         */
        public static final String DATE_TYPE_02 = "yyyy-MM-dd";

        /**
         * 日期格式：HH:mm:ss
         */
        public static final String DATE_TYPE_03 = "HH:mm:ss";

        /**
         * 日期格式：yyyy-MM-dd HH:mm:ss:SSS
         */
        public static final String DATE_TYPE_04 = "yyyy-MM-dd HH:mm:ss:SSS";

        /**
         * 日期格式：HH:mm:ss:SSS
         */
        public static final String DATE_TYPE_05 = "HH:mm:ss:SSS";
    }

    /**
     * @author 16计算机 ouyang
     * @version 1.0
     * @copyright ：ouyang 版权所有 © 2019
     * @date 2019/11/15 11:06
     * @Description TODO
     * regular 常量
     */
    public static final class Regular {
        private Regular() {

        }

        /**
         * 邮箱正则
         */
        public static final String MAIL = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";

        /**
         * 纯数字正则
         */
        public static final String NUMBER = "^(0|[1-9][0-9]*|-[1-9][0-9]*)$";

        /**
         * 整数正则
         */
        public static final String INTEGER = "^[-\\+]?[\\d]*$";

        /**
         * 浮点数正则
         */
        public static final String FLOAT = "^[-\\+]?[.\\d]*$";

        /**
         * 手机正则
         */
        public static final String PHONE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
    }
}
