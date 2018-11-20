package com.property.manage.common.service.impl;

import com.property.manage.base.model.constants.CacheConstants;
import com.property.manage.base.model.model.Result;
import com.property.manage.common.dao.UserInfoDao;
import com.property.manage.common.pojo.UserInfo;
import com.property.manage.common.pojo.UserInfoView;
import com.property.manage.common.query.UserInfoQuery;
import com.property.manage.common.query.UserInfoViewQuery;
import com.property.manage.common.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * @author 管辉俊
 * @since 2018-05-22
 */
@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    private static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public Long addUserInfo(UserInfo record) {
        try {
            return userInfoDao.addUserInfo(record);
        } catch (SQLException e) {
            logger.error("dao addUserInfo error.:" + e.getMessage(), e);
        }
        return -1L;
    }

    @Override
    public UserInfo getUserInfoByKey(Long id) {
        try {
            return userInfoDao.getUserInfoByKey(id);
        } catch (SQLException e) {
            logger.error("dao getUserInfobyKey error.:" + e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Cacheable(value = CacheConstants.CACHE_CORP_USER_KEY + "#" + CacheConstants.DEFAULT_EXPIRE_TIME_TWO_HOUR + "#1", key = "#corpId.concat('_').concat(#userId)")
    public UserInfo getUserInfoByOpenId(String openId) {
        try {
            return userInfoDao.getUserInfoByOpenId(openId);
        } catch (SQLException e) {
            logger.error("dao getUserInfoByOpenId error.:" + e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Cacheable(value = CacheConstants.CACHE_CORP_USER_KEY + "#" + CacheConstants.DEFAULT_EXPIRE_TIME_TWO_HOUR + "#1", key = "#openId")
    public UserInfo getUserInfoByCache(String openId) {
        logger.debug("从缓存中未获得数据,需要重新登录");
        return null;
    }

    @Override
    public Integer deleteByKey(Long id) {
        try {
            return userInfoDao.deleteByKey(id);
        } catch (SQLException e) {
            logger.error("dao deleteByKey error. :" + e.getMessage(), e);
        }
        return -1;
    }

    @Override
    public Integer deleteByCompany(Long companyId) {
        try {
            return userInfoDao.deleteByCompany(companyId);
        } catch (SQLException e) {
            logger.error("dao deleteByCompany error. :" + e.getMessage(), e);
        }
        return -1;
    }

    @Override
    public Integer updateUserInfoByKey(UserInfo record) {
        try {
            return userInfoDao.updateUserInfoByKey(record);
        } catch (SQLException e) {
            logger.error("dao updateUserInfo error.userInfo:" + e.getMessage(), e);
        }
        return -1;
    }

    @Override
    public Result<UserInfo> getUserInfoListWithPage(UserInfoQuery query) {
        Result<UserInfo> rs = null;
        try {
            rs = userInfoDao.getUserInfoListWithPage(query);
            if (!rs.isSuccess()) {
                logger.error("get UserInfo error." + rs.getErrorMsg());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return rs;
    }

    @Override
    public List<UserInfo> getUserInfoList(UserInfoQuery query) {
        try {
            return userInfoDao.getUserInfoList(query);
        } catch (SQLException e) {
            logger.error("get UserInfo list error." + e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public Result<UserInfoView> getUserInfoViewListWithPage(UserInfoViewQuery query) {
        Result<UserInfoView> rs = null;
        try {
            rs = userInfoDao.getUserInfoViewListWithPage(query);
            if (!rs.isSuccess()) {
                logger.error("get UserInfo error." + rs.getErrorMsg());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return rs;
    }

    @Override
    public List<UserInfoView> getUserInfoViewList(UserInfoViewQuery query) {
        try {
            return userInfoDao.getUserInfoViewList(query);
        } catch (SQLException e) {
            logger.error("get UserInfo list error." + e.getMessage(), e);
        }
        return Collections.emptyList();
    }
}
