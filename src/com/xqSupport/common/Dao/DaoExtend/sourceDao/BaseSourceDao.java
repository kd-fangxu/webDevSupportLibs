package com.xqSupport.common.Dao.DaoExtend.sourceDao;

import com.xqSupport.Entity.BaseSourceEntity;
import com.xqSupport.common.Dao.BaseDao;
import com.xqSupport.common.Utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by mac on 2018/1/20.
 */
public class BaseSourceDao<T extends BaseSourceEntity> extends BaseDao<T> implements IBaseSourceDao<T> {


    public void saveSource(HttpServletRequest request, T sourceEntity, MultipartFile file, String releatedPath) {

        String footerPath = FileUtils.saveFile(request, file, releatedPath);
        if (footerPath != null) {
            sourceEntity.setSouceUrl(footerPath);
            saveEntity(sourceEntity);
        }


    }

    public void deleteSource(HttpServletRequest request, T sourceEntity, boolean isDeleteFile) {
        if (isDeleteFile) {
            File file = new File(request.getSession().getServletContext().getRealPath("/") + sourceEntity.getSouceUrl());
            file.deleteOnExit();
        }
        delete(sourceEntity);
    }
}
