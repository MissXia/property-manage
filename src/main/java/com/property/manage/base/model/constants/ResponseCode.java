package com.property.manage.base.model.constants;

/**
 * @author guozhenbin
 * @since 17/2/5
 */
public class ResponseCode {
    /**
     * 1xx  表示成功   （成功无需解释）
     * 2xx  表示参数异常 （调用后台数据时参数验证失败等）
     * 3xx  程序运行时异常 （没有捕获的异常，如空指针异常等）
     * 4xx  程序内部错误  （预知已经捕获的异常，如获取缓存异常）
     * 5xx  内部服务调用失败  （调用第三方服务出错，如调用dubbo超时等）
     * 6xx  淘宝服务调用失败  （淘宝接口调用失败，如超时，session失效，授权失效等）
     * 7xx  权限错误 （会话失效、无权限）
     * 8xx  程序挂了  （没有想好
     */
    public final static int CODE_SUCCESS = 100;
    public final static int CODE_PARAM_ERROR = 200;
    public final static int CODE_RUNTIME_ERROR = 300;
    public final static int CODE_INTERNAL_ERROR = 400;

    public final static int CODE_INTERNAL_RPC_ERROR = 500;
    public final static int CODE_TAOBAO_API_ERROR = 600;
    public final static int CODE_AUTHORITY = 700;
    //session 获取用户失败
    public final static int CODE_NO_USER_ERROR = 702;

    public final static int CODE_JVM_DOWN = 800;

    /**
     * ps : 业务错误定义在这里
     * 所有的service层的错误code均在此定义，以 CODE_SERVICE_开始,从311开始
     */

    /**
     * 对象转换异常
     */
    public final static int CODE_ENTITY_CONVERT_ERROR = 201;

    /**
     * 程序未知异常
     */
    public final static int CODE_SERVICE_UNKOWN_ERROR = 311;

    /**
     * 编辑器的dubbo服务异常
     */
    public final static int CODE_SERVICE_EDIT_RPC_ERROR = 312;

    /**
     * 前端传入参数异常
     */
    public final static int CODE_SERVICE_WEB_PARAMS_ERROR = 313;

    /**
     *数据库查询异常
     */
    public final static int CODE_SERVICE_DB_QUERY_ERROR = 314;

    /**
     * 商品服务异常
     */
    public final static int CODE_SERVICE_ITEM_SEARCH_ERROR = 315;

    /**
     * 活动不存在
     */
    public final static int CODE_SERVICE_ACTIVE_NOT_FOUND = 316;

    /**
     * 安装的时候提交的宝贝未找到(为0个)
     */
    public final static int CODE_SERVICE_DESC_ITEM_NOT_FOUND = 317;

    /**
     * 调用安装卸载服务异常 -- 安装
     */
    public final static int CODE_SERVICE_DESC_INSTALL_RPC_ERROR = 318;

    /**
     * 调用安装卸载服务异常 -- 卸载
     */
    public final static int CODE_SERVICE_DESC_UNINSTALL_RPC_ERROR = 319;

    /**
     * 调用安装卸载服务的查询异常
     */
    public final static int CODE_SERVICE_DESC_QUERY_RPC_ERROR = 320;

    /**
     * 调用淘宝api查询类目信息异常
     */
    public final static int CODE_SERVICE_TAOBAO_SELLERCAT_ERROR = 321;

    /**
     * XML解析异常
     */
    public final static int CODE_SERVICE_XML_ANALYSIS_ERROR = 322;

    /**
     * 登录guanghua2cc删除系统素材
     */
    public final static int CODE_SERVICE_DELETE_SYSTEM_MATERIAL_ERROR = 323;

    /**
     * 我的海报未找到
     */
    public final static int CODE_SERVICE_MYHAIBAO_NOT_FOUND = 324;

    /**
     * 删除活动异常
     */
    public final static int CODE_SERVICE_DELETE_ACTIVE_ERROR = 325;

    /**
     * 用户权限不够
     */
    public final static int CODE_SERVIEC_LIMITED_AUTHORITY = 326;

    /**
     * 可授权的数量不足
     */
    public final static int AUTHORIZATION_NUMBER_NOT_ENOUGH = 327;

    /**
     * API接口调用已知错误
     */
    public final static int CODE_API_INVOKE_ERROR = 601;

    /**
     * 超限，已知错误
     */
    public final static int CODE_API_OVERRUN_ERROR = 602;

    /**
     * 服务过期
     */
    public final static int CHARGEABLE_SERVICE_EXPIRE_ERROR = 603;

    /**
     * 试用过期
     */
    public final static int PROBATIONAL_SERVICE_EXPIRE_ERROR = 604;

    /**
     * 免费过期
     */
    public final static int FREE_SERVICE_EXPIRE_ERROR = 605;

    /**
     * session 过期
     */
    public final static int CODE_SESSION_KEY_ERROR = 701;

    /**
     * 认证异常
     */
    public final static int AUTH_ERROR = 703;

    /**
     * 短授权异常
     */
    public final static int SHORT_OUATH_ERROR = 704;

    /**
     * 权限受限
     */
    public final static int USER_ROLE_ERROR = 705;
}
