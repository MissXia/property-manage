package com.property.manage.base.model.constants;

/**
 * @author guozhenbin
 * @since 17/2/21.
 */
public class CacheConstants {

    public final static String CACHE_SUITE_KEY = "suite";

    public final static String CACHE_SUITE_TOKEN_KEY = "suiteToken";

    public final static String CACHE_SUITE_TICKET_KEY = "suiteTicket";

    public final static String CACHE_CORP_SUITE_JS_API_TICKET_KEY = "corpSuiteJsApiTicket";

    public final static String CACHE_CORP_TOKEN_KEY = "corpToken";

    public final static String CACHE_CORP_KEY = "isvCorp";

    public final static String CACHE_CORP_USER_KEY = "corpUser";

    public final static int DEFAULT_EXPIRE_TIME_MIN = 60;

    public final static int DEFAULT_EXPIRE_TIME_HOUR = DEFAULT_EXPIRE_TIME_MIN * 60;

    public final static int DEFAULT_EXPIRE_TIME_TWO_HOUR = DEFAULT_EXPIRE_TIME_HOUR * 2;

    public final static int DEFAULT_EXPIRE_TIME_DAY = 24 * DEFAULT_EXPIRE_TIME_HOUR;

    public final static int DEFAULT_EXPIRE_TIME_MOUTH = 30 * DEFAULT_EXPIRE_TIME_DAY;

    public final static String JS_TICKET_UPDATING = "jsTicketUpdate";

    public final static String CORP_TOKEN_UPDATING = "corpTokenUpdate";

    /** 默认的分隔符,用于分割参数,获取过期时间等 */
    public final static String SEPARATOR = "#";

}
