package com.property.manage.base.model.utils;

import java.util.UUID;

public class DBUtils {

    /**
     * 生成UUID主键
     *
     * @return
     */
    public static String makeUUID() {
        // 生成UUID主键
        return UUID.randomUUID().toString().replace("-", "");
    }
}