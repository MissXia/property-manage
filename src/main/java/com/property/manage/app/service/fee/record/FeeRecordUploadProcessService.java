package com.property.manage.app.service.fee.record;

import com.property.manage.base.model.constants.Constants;
import com.property.manage.common.pojo.FeeRecordUpload;
import com.property.manage.common.query.FeeRecordUploadQuery;
import com.property.manage.common.service.FeeRecordUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class FeeRecordUploadProcessService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FeeRecordUploadService feeRecordUploadService;

    /**
     * 查找上传记录(排除Json)
     *
     * @param userId
     * @return
     */
    public FeeRecordUpload findFeeRecordUploadExceptJson(Long userId) {
        // 查询条件
        FeeRecordUploadQuery query = new FeeRecordUploadQuery();
        // 用户ID
        query.setUserId(userId);
        // 设定显示字段
        query.setFields("id,user_id,total_count,error_count,status,created,modified");
        // 按时间排序
        query.orderbyModified(false);
        // 取得列表
        List<FeeRecordUpload> uploads = feeRecordUploadService.getFeeRecordUploadList(query);
        // 异常处理
        if (null == uploads || uploads.isEmpty()) {
            // 中断流程
            return null;
        }
        // 取得最新数据
        return uploads.get(0);
    }

    /**
     * 查找上传记录
     *
     * @param userId
     * @return
     */
    public FeeRecordUpload findFeeRecordUpload(Long userId) {
        // 查询条件
        FeeRecordUploadQuery query = new FeeRecordUploadQuery();
        // 用户ID
        query.setUserId(userId);
        // 按时间排序
        query.orderbyModified(false);
        // 取得列表
        List<FeeRecordUpload> uploads = feeRecordUploadService.getFeeRecordUploadList(query);
        // 异常处理
        if (null == uploads || uploads.isEmpty()) {
            // 中断流程
            return null;
        }
        // 取得最新数据
        return uploads.get(0);
    }

    /**
     * 生成导入记录
     *
     * @param upload
     * @param userId
     * @param totalCount
     * @param errorCount
     * @param errorJson
     * @param status
     * @return
     */
    public FeeRecordUpload makeFeeRecordUpload(FeeRecordUpload upload, Long userId, Integer totalCount, Integer errorCount, String errorJson, Integer status) {
        // 当前时间
        Date now = new Date();
        // 是否更新
        boolean updated = true;
        // 如果没有记录
        if (null == upload) {
            // 实例化对象
            upload = new FeeRecordUpload();
            // 用户ID
            upload.setUserId(userId);
            // 创建时间
            upload.setCreated(now);
            // 新增
            updated = false;
        }
        // 总记录数
        upload.setTotalCount(totalCount);
        // 错误记录数
        upload.setErrorCount(errorCount);
        // 错误JSON
        upload.setErrorJson(errorJson);
        // 如果未设定状态
        if (null == status) {
            // 如果全部失败
            if (totalCount.equals(errorCount)) {
                // 设定为失败
                status = Constants.UPLOAD_STATUS_FAILD;
            }
            // 如果错误条数大于0
            else if (errorCount > 0) {
                // 设定为部分失败
                status = Constants.UPLOAD_STATUS_PART;
            } else {
                // 设定导入成功
                status = Constants.UPLOAD_STATUS_SUCCESS;
            }
        }
        // 上传状态
        upload.setStatus(status);
        // 更新时间
        upload.setModified(now);
        // 更新的情况下
        if (updated) {
            // 更新数据
            feeRecordUploadService.updateFeeRecordUploadByKey(upload);
        } else {
            // 新增数据
            feeRecordUploadService.addFeeRecordUpload(upload);
        }
        // 返回对象
        return upload;
    }
}