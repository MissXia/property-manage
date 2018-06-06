package com.property.manage.base.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 公共常量类
 *
 * @author huzhengnan
 *         2015年03月20日19:33
 */
public class WebConstants {

    public final static int HTTP_TIMEOUT = 5000;

    /**
     * 加密算法：MD5
     */
    public final static String ALGORITHM_MD5 = "MD5";

    /**
     * 字符集UTF-8
     */
    public final static String CHARSET_UTF8 = "UTF-8";

    /**
     * 请求回执状态
     */
    public static final String SERVICE_RESPONSE_STATUS = "status";

    /**
     * 请求回执信息
     */
    public static final String SERVICE_RESPONSE_MESSAGE = "message";

    /**
     * 淘宝旺旺前缀
     */
    public static final String WANGWANG_PREFIX = "cntaobao";
    /**
     * 阿里旺旺前缀
     */
    public static final String ALI_PREFIX = "cnali";

    /**
     * 订单续费类型
     */
    public static final Long ORDER_BIZ_TYPE_NEW_ORDER_CODE = 1L;
    public static final String ORDER_BIZ_TYPE_NEW_ORDER_STRING = "新订";

    /**
     * 订单续费类型
     */
    public static final Long ORDER_BIZ_TYPE_RENEW_CODE = 2L;
    public static final String ORDER_BIZ_TYPE_RENEW_STRING = "续费";

    /**
     * 升级订单类型
     */
    public static final Long ORDER_BIZ_TYPE_UPGRADE_CODE = 3L;
    public static final String ORDER_BIZ_TYPE_UPGRADE_STRING = "升级";

    /**
     * 后台自动续订
     */
    public static final Long ORDER_BIZ_TYPE_AUTO_RENEW_CODE = 5L;
    public static final String ORDER_BIZ_TYPE_AUTO_RENEW_STRING = "后台自动续订";

    /**
     * 自动续费
     */
    public static final String NATURAL_RENEW = "自然成交";

    /**
     * 人机续费
     */
    public static final String MANUAL_RENEW = "人机托管";

    /**
     * 广撒网低价引流_99/季
     */
    public static final String NORMAL_WIDE_ENGINE_SUBSCRIBE_CODE = "FW_GOODS-1834824-1";

    public static final String NORMAL_WIDE_ENGINE_VERSION = "normalWideengineVersion";

    public static final String WIDE_ENGINE_VERSION = "wideengine";

    public static final String WIDE_ENGINE_VERSION_STR = "广撒网低价引流版";


    /**
     * 豪华双引擎版_活动季
     */
    public static final String ACTIVITY_DUAL_ENGINE_SUBSCRIBE_CODE = "FW_GOODS-1834824-v2";

    public static final String ACTIVITY_DUAL_ENGINE_VERSION = "activityDualengineVersion";

    public static final String ACTIVITY_DUAL_ENGINE_VERSION_STR = "豪华双引擎活动版";
    /**
     * 豪华双引擎版_188/季
     */
    public static final String NORMAL_DUAL_ENGINE_SUBSCRIBE_CODE = "FW_GOODS-1834824-v3";

    public static final String NORMAL_DUAL_ENGINE_VERSION = "normalDualengineVersion";

    public static final String NORMAL_DUAL_ENGINE_VERSION_STR = "双引擎体验版";

    // 双引擎豪华版
    public static final String DUAL_ENGINE_VERSION = "dualengine";

    public static final String DUAL_ENGINE_VERSION_STR = "双引擎豪华版";

    /**
     * 四引擎版本
     */
    public static final String FOUR_ENGINE_SUBSCRIBE_CODE = "FW_GOODS-1834824-v5";

    public static final String NORMAL_FOUR_ENGINE_VERSION = "fourengineVersion";

    public static final String FOUR_ENGINE_VERSION = "fourengine";

    public static final String FOUR_ENGINE_VERSION_STR = "四引擎旗舰版";

    /**
     * 体验版本
     */
    public static final String EXPERIENCE_SUBSCRIBE_CODE = "FW_GOODS-1834824-v6";

    public static final String NORMAL_EXPERIENCE_VERSION = "experienceVersion";

    public static final String EXPERIENCE_VERSION = "experience";

    public static final String EXPERIENCE_VERSION_STR = "低价引流体验版";

    /**
     * 人机云托管版本
     */
    public static final String MANUAL_SUBSCRIBE_CODE = "FW_GOODS-1834824-v7";

    public static final String NORMAL_MANUAL_VERSION = "manualVersion";

    public static final String MANUAL_VERSION = "manual";

    public static final String MANUAL_VERSION_STR = "人机云托管";


    /* 第一次登录|托管 提醒天数 */
    public static Integer FIRST_LOGIN_OPT_REMIND_DAY_3 = 3;

    /* 第二次登录|托管 提醒天数 */
    public static Integer SECOND_LOGIN_OPT_REMIND_DAY_6 = 6;

    /* 订单类型 */
    public static Long ORDER_TYPE_NEW_1 = 1L;

    /* 已联系,无续费意向 */
    public static Integer CONTACT_STATUS_CONTACTED_UNFEE_CODE = -1;
    public static String CONTACT_STATUS_CONTACTED_UNFEE_STRING = "无续费意向";

    /* 今日联系 */
    public static Integer CONTACT_STATUS_TODAY_CONTACT_CODE = 1;
    public static String CONTACT_STATUS_TODAY_CONTACT_STRING = "今日提醒";

    /* 已联系 */
    public static Integer CONTACT_STATUS_CONTACTED_CODE = 2;
    public static String CONTACT_STATUS_CONTACTED_STRING = "已联系";

    /* 未联系 */
    public static Integer CONTACT_STATUS_NOT_CONTACT_CODE = 3;
    public static String CONTACT_STATUS_NOT_CONTACT_STRING = "未联系";

    /* 继续跟进 */
    public static Integer CONTACT_CONTINUE_CONTACT_CODE = 4;
    public static String CONTACT_CONTINUE_CONTACT_STRING = "重点跟进";

    /* 联系不上 */
    public static Integer CONTACT_CAN_NOT_CONTACT_CODE = 5;
    public static String CONTACT_CAN_NOT_CONTACT_STRING = "联系不上";

    /////////////////////////////////////////////////////////////

    /* 手动用户 */
    public static Integer CONTACT_MANUAL_USER_CODE = 6;
    public static String CONTACT_MANUAL_USER_STRING = "手动用户";

    /* 退款用户 */
    public static Integer CONTACT_REFUND_USER_CODE = 7;
    public static String CONTACT_REFUND_USER_STRING = "退款用户";

    /* 评分用户 */
    public static Integer CONTACT_GOOD_COMMENT_CODE = 8;
    public static String CONTACT_GOOD_COMMENT_STRING = "评分用户";

    /////////////////////////////////////////////////////////////

    /* 电话联系不上 */
    public static Integer CONTACT_STATUS_PHONE_NOT_CONTACT_CODE = 10;
    public static String CONTACT_STATUS_PHONE_NOT_CONTACT_STRING = "电话联系不上";

    /* 电话已联系 */
    public static Integer CONTACT_STATUS_PHONE_CONTACTED_CODE = 11;
    public static String CONTACT_STATUS_PHONE_CONTACTED_STRING = "电话已联系";

    /* 电话已联系需继续跟进 */
    public static Integer CONTACT_STATUS_PHONE_CONTINUE_CODE = 12;
    public static String CONTACT_STATUS_PHONE_CONTINUE_STRING = "电话已联系需继续跟进";

    /* 电话已联系需旺旺跟进 */
    public static Integer CONTACT_STATUS_PHONE_WW_CONTINUE_CODE = 13;
    public static String CONTACT_STATUS_PHONE_WW_CONTINUE_STRING = "电话已联系需旺旺跟进";

    //////////////////////////////////////////////////////////////


    /* 登录提醒 */
    public static Integer CONTACT_TYPE_LOGIN_CODE = 1;
    public static String CONTACT_TYPE_LOGIN_STRING = "登录提醒";


    /* 续费 */
    public static Integer CONTACT_TYPE_RENEW_CODE = 2;
    public static String CONTACT_TYPE_RENEW_STRING = "续费";

    /* 售后主动回访 */
    public static Integer CONTACT_TYPE_VISIT_CODE = 3;
    public static String CONTACT_TYPE_VISIT_STRING = "售后主动回访";

    /* 优化师主动回访 */
    public static Integer CONTACT_TYPE_OPT_VISIT_CODE = 4;
    public static String CONTACT_TYPE_OPT_VISIT_STRING = "优化师主动回访";

    /* 退款用户 */
    public static Integer CONTACT_TYPE_OPT_REFUND_USER_CODE = 5;
    public static String CONTACT_TYPE_OPT_REFUND_USER_STRING = "退款用户";

    /* 评分 */
    public static Integer CONTACT_TYPE_COMMENT_CODE = 6;
    public static String CONTACT_TYPE_COMMENT_STRING = "评分";


    /* 系统 */
    public static Integer SYSTEM_CODE = 0;
    public static String SYSTEM_STRING = "系统";

    /* 售前 */
    public static Integer PRE_SALES_CUS_SERVICE_CODE = 1;
    public static String PRE_SALES_CUS_SERVICE_STRING = "售前";

    /* 售后 */
    public static Integer AFTER_SALES_CUS_SERVICE_CODE = 2;
    public static String AFTER_SALES_CUS_SERVICE_STRING = "售后";

    /* 优化师 */
    public static Integer OPTIMIZATION_DIVISION_CUS_SERVICE_CODE = 3;
    public static String OPTIMIZATION_DIVISION_CUS_SERVICE_STRING = "优化师";

    /*--------------------------------------USER ACTION LOG AREA -------------------------------------------------------*/
    /* 用户行为类型 */
    public static String USER_ACITON_LOG_TYPE_OPT_STRING = "优化";
    public static Integer USER_ACITON_LOG_TYPE_OPT_CODE = 1;

    public static Integer USER_ACITON_LOG_TYPE_NOT_OPT_CODE = 0;
    public static String USER_ACITON_LOG_TYPE_NOT_OPT_STRING = "不优化";

    public static Integer USER_ACTION_LOG_SCOPE_USER_CODE = 2;
    public static String USER_ACTION_LOG_SCOPE_USER_STRING = "用户维度";

    public static Integer USER_ACTION_LOG_SCOPE_CAMPAIGN_CODE = 0;
    public static String USER_ACTION_LOG_SCOPE_CAMPAIGN_STRING = "计划维度";

    public static Integer USER_ACTION_LOG_SCOPE_ADGROUP_CODE = 1;
    public static String USER_ACTION_LOG_SCOPE_ADGROUP_STRING = "广告组维度";

    public static Integer USER_ACTION_LOG_SCOPE_KEYWORD_CODE = 3;
    public static String USER_ACTION_LOG_SCOPE_KEYWORD_STRING = "关键词";

    /*-------------------------------------- SYSTEM REMARK CONTENT AREA ------------------------------------------------*/
    /* 系统自动分配1 */
    public static String SYSTEM_REMARK_AUTO_DISTRIBUTION_BEFORE_SALE = "系统自动分配至客服:%s，提醒用户进行登录|托管。";

    /* 系统自动分配2 */
    public static String SYSTEM_REMARK_AUTO_DISTRIBUTION_AFTER_SALE_FOR_RENEW = "系统自动分配至客服:%s，提醒用户进行续费。";

    /* 未联系 */
    public static String SYSTEM_REMARK_AUTO_NOT_CONTACTED = "当日未联系此用户，系统自动更改状态为未联系。";

    /* 客服标注 */
    public static String PRE_SALE_REMARK_CUS = "客服：%s，将用户标注为%s。";

    /* 客服好评标注 */
    public static String COMMENT_REMARK = "客服：%s，将此评价由：%s，归属于：%s。";

    /* 人工分配优化 */
    public static String OPTIMIZATION_BY_CUS_SERVICE = "由：%s，分配至 %s 进行优化";

    /* 人工分配主动回访 */
    public static String VISIT_BY_CUS_SERVICE = "由：%s，分配至 %s 进行主动回访";

    /* 人工添加至黑名单 */
    public static String ADD_TO_REFUND_USER = "由：%s，添加用户 %s 至退款黑名单列表";

    /* 系统自动移出黑名单 */
    public static String SYSTEM_REMOVE_REFUND_USER = "到达移除时间，系统自动将用户 %s 移出列表";
    /* 客服手动移出黑名单 */
    public static String MANUAL_REMOVE_REFUND_USER = "由：%s，手动将用户 %s 移出列表";

    /*--------------------------------------REFUND USER AREA------------------------------------------------------------*/
    /* 退款用户黑名单有效 */
    public static Integer REFUND_USER_STATUS_VALID = 1;
    /* 退款用户黑名单系统设置无效 */
    public static Integer REFUND_USER_STATUS_INVALID_BY_SYSTEM = 2;
    /* 退款用户黑名单人工设置无效 */
    public static Integer REFUND_USER_STATUS_INVALID_BY_CUS_SERVICE = 3;

    /**
     * 电话回访权限控制，暂时采用变量控制，后续会根据需求改为从数据库读
     */
    public static String PHONE_FLLOW_UP_CUS_SERVICE_IDS = "42,56,57,63,76";

    /**
     * 客服评分链接状态
     */
    public static Integer CUS_SCORE_URL_STATUS_NOT_GENERATED = -1;

    public static Integer CUS_SCORE_URL_STATUS_GENERATED = 0;

    public static Integer CUS_SCORE_URL_STATUS_USED = 1;

    public static Integer CUS_SCORE_URL_STATUS_EXPIRED = 2;

    /* 超级店长营销活动代码 */
    public static String[] SUPER_SHOP_KEEPER_ACT_CODES = {"ACT_305442977_160728175804"};

    /* 快车用户 */
    public static Integer CONTACT_USER_TYPE_1 = 1;
    /* 店长用户 */
    public static Integer CONTACT_USER_TYPE_2 = 2;

    /**
     * 第一页
     */
    public final static String FIRST_PAGE_STR = "1";

    /**
     * 第一页
     */
    public final static int FIRST_PAGE = 1;

    /**
     * 分页大小
     */
    public final static String PAGE_SIZE_10_STR = "10";

    /**
     * 分页大小
     */
    public final static String PAGE_SIZE_25_STR = "25";

    /**
     * 分页大小
     */
    public final static int PAGE_SIZE_25 = 25;

    /**
     * 分页大小
     */
    public final static int PAGE_SIZE = 20;

    /**
     * 排序销量
     */
    public final static String VOLUME_SORT = "volume";

    /**
     * 封装直通车API错误
     */
    public final static String SIMBA_API_ERROR_CODE = "simba_api_error";

    public final static String SIMBA_API_ERROR_STRING = "直通车服务异常";

    /**
     * 快车人机计划titile-prefix
     */
    public final static String OPTIMIZE_CAMPAIGN_TITILE_PREFIX = "快车人机";

    // 订单类型
    public final static Long VAS_ORDER_BIZ_TYPE_NEW = 1L;
    public final static Long VAS_ORDER_BIZ_TYPE_RENEW = 2L;
    public final static Long VAS_ORDER_BIZ_TYPE_UPGRADE = 3L;
    public final static Long VAS_ORDER_BIZ_TYPE_AUTO_PRESENT = 4L;
    public final static Long VAS_ORDER_BIZ_TYPE_AUTO_RENEW = 5L;
    public final static Long VAS_ORDER_BIZ_TYPE_AUTO_GENERATE = 6L;

    public final static Map<Long, String> VAS_ORDER_BIZ_TYPE_MAP = new HashMap<Long, String>() {{
        put(VAS_ORDER_BIZ_TYPE_NEW, "新订");
        put(VAS_ORDER_BIZ_TYPE_RENEW, "续订");
        put(VAS_ORDER_BIZ_TYPE_UPGRADE, "升级");
        put(VAS_ORDER_BIZ_TYPE_AUTO_PRESENT, "后台赠送");
        put(VAS_ORDER_BIZ_TYPE_AUTO_RENEW, "后台自动续订");
        put(VAS_ORDER_BIZ_TYPE_AUTO_GENERATE, "订单审核后生成订购关系");
    }};

    /**
     * crm 无续费意向contact_status
     */
    public final static Integer CRM_CONTACT_STATUS_NO_RENEW_INTENTION = -1;

    /**
     * 人机版本代码
     */
    public static String ITEM_CODE_V9 = "FW_GOODS-1834824-v9";
    public static String ITEM_CODE_V1 = "FW_GOODS-1834824-1";

    public static String REAL_ITEM_CODE_V1 = "MAN_PC-1834824-v1";
    public static String REAL_ITEM_CODE_V2 = "MAN_PC-1834824-v2";
    public static String REAL_ITEM_CODE_V9 = "MAN_PC-1834824-v9";

    /**
     * 人机版本值
     */
    public static int V9 = 9;
    public static int V1 = 1;

    /**
     * 云托管版本代码
     */
    public static String ITEM_CODE_V2 = "FW_GOODS-1834824-v2";
    public static String ITEM_CODE_V8 = "FW_GOODS-1834824-v8";


    public static final String BASE64_IMAGE_PREFIX = ";base64,";

    /**
     * 新旧订单
     */
    public static final String NEW_ORDER = "新版本订单";
    public static final String MANPC_ORDER = "旧版本订单";

	/**
	 * 内购用户状态
     */
    public static final Integer ORDER_WAIT_USE = 0;
    public static final Integer ORDER_IN_USE = 1;
    public static final Integer ORDER_NOT_USE = -1;

	/**
	 * 内购订单状态终止原因
     */
    public static final Integer ORDER_END_NATURE = 1;
    public static final Integer ORDER_END_OPERATE = 2;

    /**
     * 车手等级
     */
    public static final int RIDER_LEVEL_PRACTICE = 4;
    public static final int RIDER_LEVEL_PROFESSIONAL = 1;
    public static final int RIDER_LEVEL_SENIOR = 2;
    public static final int RIDER_LEVEL_GOLD = 3;

    /**
     * 车手角色
     */
    public static final int RIDER_ROLE_NORMAL = 0;
    public static final int RIDER_ROLE_LEADER = 1;
    public static final int RIDER_ROLE_SUB_LEADER = 2;

    /**
     * 头衔
     */
    public static final int RANK_RIDER_PRACTICE = 0;
    public static final int RANK_RIDER_PROFESSIONAL = 1;
    public static final int RANK_RIDER_SENIOR = 2;
    public static final int RANK_RIDER_GOLD = 3;
    public static final int RANK_GROUP_LEADER = 4;
    public static final int RANK_GROUP_SUB_LEADER = 5;
    public static final int RANK_GROUP_LEADER_EXCELLENT = 6;
    public static final int RANK_GROUP_SUB_LEADER_EXCELLENT = 7;

    /**
     * 小组头衔
     */
    public static final int RANK_BEST_GROUP_MONTH = 0;
    public static final int RANK_SECOND_GROUP_MONTH = 1;
    public static final int RANK_THIRD_GROUP_MONTH = 2;
    public static final int RANK_LAST_GROUP_MONTH = 3;
    public static final int RANK_BEST_GROUP_QUARTER = 4;

    public enum LogType {
        CLOSED(-1,5),REJECT(8,6),FINISHED(9,3);
        private int columnType;
        private int logType;
        LogType(int columnType, int logType) {
            this.columnType = columnType;
            this.logType = logType;
        }

        public int getColumnType() {
            return columnType;
        }

        public void setColumnType(int columnType) {
            this.columnType = columnType;
        }

        public int getLogType() {
            return logType;
        }

        public void setLogType(int logType) {
            this.logType = logType;
        }

        public static int getLogType(int columnType){
            for (LogType logType : LogType.values()) {
                if(logType.getColumnType() == columnType){
                    return logType.getLogType();
                }
            }
            return FINISHED.getLogType();
        }
    }
}
