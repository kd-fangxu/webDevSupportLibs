package com.xqSupport.supportmodule.oss.dao;

import com.xqSupport.common.BaseResponse;
import com.xqSupport.common.Dao.BaseDao;
import com.xqSupport.common.Utils.FileUtils;
import com.xqSupport.supportmodule.oss.entity.OssSourceEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by mac on 2018/1/21.
 */
@Repository
public class OssDao extends BaseDao<OssSourceEntity> implements IOssDao {


    public OssSourceEntity saveSource(HttpServletRequest request, MultipartFile file, String releatedPath) {
        String footerPath = FileUtils.saveFile(request, file, releatedPath);
        if (footerPath != null) {
            OssSourceEntity sourceEntity = new OssSourceEntity();
            sourceEntity.setSourceUrl(footerPath);
            sourceEntity.setSourceKey(UUID.randomUUID().toString());
            saveEntity(sourceEntity);
            return sourceEntity;
        }
        return null;
    }

    public BaseResponse deleteSource(HttpServletRequest request, Serializable ossEntityKey, boolean isDeleteFile) {
        List<OssSourceEntity> ossSourceEntities = findByProperty("sourceKey", ossEntityKey);
        if (ossSourceEntities != null && ossSourceEntities.size() > 0) {
            for (OssSourceEntity entity : ossSourceEntities) {
                if (isDeleteFile) {
                    File file = new File(request.getSession().getServletContext().getRealPath("/") + entity.getSourceUrl());
                    file.deleteOnExit();
                }
                delete(entity);


                return BaseResponse.create(1, "删除成功", "");
            }
        }
        return BaseResponse.create(0, "删除失败", "");
    }

}
