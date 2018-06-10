package com.property.manage.common.dao;

import com.property.manage.base.model.model.Result;
import com.property.manage.common.mappers.FeeItemMapper;
import com.property.manage.common.pojo.FeeItem;
import com.property.manage.common.query.FeeItemQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author 管辉俊
 * @date 2018-06-10
 */
@Repository
public class FeeItemDao {

    private static Logger logger = LoggerFactory.getLogger(FeeItemDao.class);

    @Autowired
    private FeeItemMapper feeItemMapper;

    /**
     * 新增数据
     *
     * @return
     * @throws SQLException
     */
    public Long addFeeItem(FeeItem record) throws SQLException {
        int insert = feeItemMapper.insertFeeItem(record);
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
    public FeeItem getFeeItemByKey(Long id) throws SQLException {
        return feeItemMapper.getFeeItemByKey(id);
    }

    /**
     * 根据主键删除
     *
     * @return
     * @throws SQLException
     */
    public Integer deleteByKey(Long id) throws SQLException {
        return feeItemMapper.deleteByKey(id);
    }

    /**
     * 根据主键更新
     *
     * @return
     * @throws SQLException
     */
    public Integer updateFeeItemByKey(FeeItem record) throws SQLException {
        return feeItemMapper.updateFeeItemByKey(record);
    }

    /**
     * 按条件分页查询
     *
     * @param query
     * @return
     */
    public Result<FeeItem> getFeeItemListWithPage(FeeItemQuery query) throws SQLException {
        Result<FeeItem> rs = new Result<FeeItem>();
        Map<String, Object> params = query.getParams();
        Integer count = feeItemMapper.getFeeItemListCount(params);
        rs.setCount(count);
        if (count <= 0) {
            return rs;
        }
        try {
            rs.setList(feeItemMapper.getFeeItemListWithPage(params));
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
    public List<FeeItem> getFeeItemList(FeeItemQuery query) throws SQLException {
        return feeItemMapper.getFeeItemList(query.getParams());
    }
}