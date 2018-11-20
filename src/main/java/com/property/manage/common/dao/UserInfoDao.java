package com.property.manage.common.dao;

import com.property.manage.base.model.model.Result;
import com.property.manage.common.mappers.UserInfoMapper;
import com.property.manage.common.pojo.UserInfo;
import com.property.manage.common.pojo.UserInfoView;
import com.property.manage.common.query.UserInfoQuery;
import com.property.manage.common.query.UserInfoViewQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author 管辉俊
 */
@Repository
public class UserInfoDao implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(UserInfoDao.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 新增数据
     *
     * @return
     * @throws SQLException
     */
    public Long addUserInfo(UserInfo record) throws SQLException {
        int insert = userInfoMapper.insertUserInfo(record);
        if (insert > 0) {
            return record.getId();
        }
        return -1L;
    }

    /**
     * 根据主键查找
     *
     * @throws SQLException
     */
    public UserInfo getUserInfoByKey(Long id) throws SQLException {
        return userInfoMapper.getUserInfoByKey(id);
    }

    /**
     * 根据主键查找
     *
     * @throws SQLException
     */
    public UserInfo getUserInfoByOpenId(String openId) throws SQLException {
        return userInfoMapper.getUserInfoByOpenId(openId);
    }

    /**
     * 根据主键删除
     *
     * @return
     * @throws SQLException
     */
    public Integer deleteByKey(Long id) throws SQLException {
        return userInfoMapper.deleteByKey(id);
    }

    /**
     * 根据主键删除
     *
     * @return
     * @throws SQLException
     */
    public Integer deleteByCompany(Long companyId) throws SQLException {
        return userInfoMapper.deleteByCompany(companyId);
    }

    /**
     * 根据主键更新
     *
     * @return
     * @throws SQLException
     */
    public Integer updateUserInfoByKey(UserInfo record) throws SQLException {
        return userInfoMapper.updateUserInfoByKey(record);
    }

    /**
     * 按条件分页查询
     *
     * @param query
     * @return
     */
    public Result<UserInfo> getUserInfoListWithPage(UserInfoQuery query) throws SQLException {
        Result<UserInfo> rs = new Result<UserInfo>();
        Map<String, Object> params = query.getParams();
        Integer count = userInfoMapper.getUserInfoListCount(params);
        rs.setCount(count);
        if (count <= 0) {
            return rs;
        }
        try {
            rs.setList(userInfoMapper.getUserInfoListWithPage(params));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return rs;
    }

    /**
     * 按条件查询
     *
     * @param query
     * @return
     */
    public List<UserInfo> getUserInfoList(UserInfoQuery query) throws SQLException {
        return userInfoMapper.getUserInfoList(query.getParams());
    }

    /**
     * 按条件分页查询
     *
     * @param query
     * @return
     */
    public Result<UserInfoView> getUserInfoViewListWithPage(UserInfoViewQuery query) throws SQLException {
        Result<UserInfoView> rs = new Result<UserInfoView>();
        Map<String, Object> params = query.getParams();
        Integer count = userInfoMapper.getUserInfoViewListCount(params);
        rs.setCount(count);
        if (count <= 0) {
            return rs;
        }
        try {
            rs.setList(userInfoMapper.getUserInfoViewListWithPage(params));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return rs;
    }

    /**
     * 按条件查询
     *
     * @param query
     * @return
     */
    public List<UserInfoView> getUserInfoViewList(UserInfoViewQuery query) throws SQLException {
        return userInfoMapper.getUserInfoViewList(query.getParams());
    }
}