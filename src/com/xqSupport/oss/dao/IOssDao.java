package com.xqSupport.oss.dao;

import com.xqSupport.common.BaseResponse;
import com.xqSupport.common.Dao.IBaseDao;
import com.xqSupport.oss.entity.OssSourceEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by mac on 2018/1/21.
 */
public interface IOssDao extends IBaseDao<OssSourceEntity> {
    /**
     * 文件存储
     *
     * @param request
     * @param file
     * @param releatedPath
     * @return 成功返回文件对象  包含文件key
     */
    OssSourceEntity saveSource(HttpServletRequest request, MultipartFile file, String releatedPath);

    BaseResponse deleteSource(HttpServletRequest request, Serializable ossEntityKey, boolean isDeleteFile);

}
