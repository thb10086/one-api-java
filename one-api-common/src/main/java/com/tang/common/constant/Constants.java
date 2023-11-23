package com.tang.common.constant;

/**
 * 通用常量信息
 *
 * @author ruoyi
 */
public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * www主域
     */
    public static final String WWW = "www.";

    /**
     * RMI 远程方法调用
     */
    public static final String LOOKUP_RMI = "rmi:";

    public static final String ONE_API_JAVA_ERROR="one_api_java_error";

    /**
     * LDAP 远程方法调用
     */
    public static final String LOOKUP_LDAP = "ldap:";

    /**
     * LDAPS 远程方法调用
     */
    public static final String LOOKUP_LDAPS = "ldaps:";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 200;

    /**
     * 失败标记-参数类错误
     */
    public static final Integer BAD_REQUEST = 400;

    /**
     * 失败标记-权限类错误
     */
    public static final Integer UNAUTHORIZED = 401;

    /**
     * 登录类错误
     */
    public static final Integer LOGIN_ERROR = 401001;

    /**
     * 刷新token类错误
     */
    public static final Integer REFRESH_ERROR = 401002;

    /**
     * token错误或者失效
     */
    public static final Integer TOKEN_ERROR = 401003;

    /**
     * 失败标记
     */
    public static final Integer FAIL = 500;

    /**
     * 登录成功状态
     */
    public static final String LOGIN_SUCCESS_STATUS = "0";

    /**
     * 登录失败状态
     */
    public static final String LOGIN_FAIL_STATUS = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 初始化数据版本号
     */
    public static final int INIT_VERSION = 1;

    public static final String DEFAULT_API_HOST = "https://api.openai.com";

    public static final String DEFAULT_API_URL = "/v1/chat/completions";

    public static final String DEFAULT_ACCEPT = "*/*";

    public static final String DEFAULT_MODEL = "gpt-3.5-turbo";


    public static final String STREAM_ACCEPT = "text/event-stream";

    public static final String URL_PATTERN ="^(http://|https://)?([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}(/.*)?$";



}
