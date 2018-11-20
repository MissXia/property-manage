package com.property.manage.common.mappers;

import com.property.manage.common.pojo.UserInfo;
import com.property.manage.common.pojo.UserInfoView;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author 管辉俊
 * @date 2018-05-22
 */
@Repository
public interface UserInfoMapper {

    /**
     * 新增数据
     *
     * @return
     * @throws SQLException
     */
    Integer insertUserInfo(UserInfo record) throws SQLException;

    /**
     * 根据主键查找
     *
     * @throws SQLException
     */
    UserInfo getUserInfoByKey(@Param("id") Long id) throws SQLException;

    /**
     * 根据主键查找
     *
     * @throws SQLException
     */
    UserInfo getUserInfoByOpenId(@Param("openId") String openId) throws SQLException;

    /**
     * 根据主键删除
     *
     * @return
     * @throws SQLException
     */
    Integer deleteByKey(@Param("id") Long id) throws SQLException;

    /**
     * 根据主键删除
     *
     * @return
     * @throws SQLException
     */
    Integer deleteByCompany(@Param("companyId") Long companyId) throws SQLException;

    /**
     * 根据主键更新
     *
     * @return
     * @throws SQLException
     */
    Integer updateUserInfoByKey(UserInfo record) throws SQLException;

    /**
     * 根据参数查找记录数
     *
     * @return
     * @throws SQLException
     */
    Integer getUserInfoListCount(Map<String, Object> params) throws SQLException;

    /**
     * 根据参数分页查询
     *
     * @return
     * @throws SQLException
     */
    List<UserInfo> getUserInfoListWithPage(Map<String, Object> params) throws SQLException;

    /**
     * 按条件查询
     *
     * @return
     */
    List<UserInfo> getUserInfoList(Map<String, Object> params) throws SQLException;

    /**
     * 根据参数查找记录数
     *
     * @return
     * @throws SQLException
     */
    Integer getUserInfoViewListCount(Map<String, Object> params) throws SQLException;

    /**
     * 根据参数分页查询
     *
     * @return
     * @throws SQLException
     */
    List<UserInfoView> getUserInfoViewListWithPage(Map<String, Object> params) throws SQLException;

    /**
     * 按条件查询
     *
     * @return
     */
    List<UserInfoView> getUserInfoViewList(Map<String, Object> params) throws SQLException;
}