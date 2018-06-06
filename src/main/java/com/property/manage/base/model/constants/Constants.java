package com.property.manage.base.model.constants;

public class Constants {

    /**
     * 创建套件时的默认套件 key
     */
    public final static String DEFAULT_SUITE_KEY = "suite4xxxxxxxxxxxxxxx";

    public final static String SESSION_CORP_USER_INFO_KEY = "corpUserInfo";

    public final static String SESSION_CORP_USER_ROLE_DATA_LIST = "corpUserRoleDataList";

    public final static Integer DEFAULT_PAGE_NO = 1;

    public final static Integer DEFAULT_PAGE_SIZE = 10;

    public final static String COOKIE_CORP_ACTIVE_STATUS = "corpActiveStatus";

    /**
     * 企业角色
     */
    public final static String CORP_ROLE_INFO = "corpRoleInfo";

    /**
     * 公司所有角色
     */
    public final static String CORP_ROLE_LIST = "corpRoleList";

    /**
     * 公司模块
     */
    public final static String CORP_MODULE_LIST = "corpModuleList";

    /**
     * 角色对应权限
     */
    public final static String ROLE_PERMISSION_LIST = "rolePermissionList";

    /**
     * 用户对应的资源列表
     */
    public final static String USER_HREF_LIST = "userHrefList";

    public final static String CUSTOMERDETAIL = "/customer/detail";

    /**
     * 机动项目组机器人消息回调地址
     **/
    public final static String BOT_URL = "https://oapi.dingtalk.com/robot/send?access_token=2d13af76b483e9c0b9cdea1c31bb64272070e72349bde4e2c90283b7ec9f47ac";

    /**
     * CRM项目组开通通知
     */
    public final static String OPEN_NOTICE_BOT_URL = "https://oapi.dingtalk.com/robot/send?access_token=69835c7bebb3d9b26a81ea52def219ca5c5c32248f5766c07d611ae2dfca1306";

    /**
     * 套件创建成功后，实际的套件key
     */
    public static String SUITE_KEY;

    /**
     * 消息的链接地址
     */
    public static String MSG_URL;

    /**
     * 创建套件时，自己输入的 token
     */
    public static String TOKEN;

    /**
     * 创建套件时，数据加密秘钥
     */
    public static String ENCODING_AES_KEY;

    /**
     * 套件下的应用
     */
    public static Long APP_ID;

}