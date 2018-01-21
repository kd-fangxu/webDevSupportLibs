package com.xqSupport.common.Dao.DaoExtend.sourceDao;

import com.xqSupport.Entity.BaseSourceEntity;
import com.xqSupport.common.Dao.IBaseDao;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by mac on 2018/1/20.
 */
public interface IBaseSourceDao<T extends BaseSourceEntity> extends IBaseDao<T> {

    void saveSource(HttpServletRequest request, T sourceEntity, MultipartFile file, String releatedPath);

    void deleteSource(HttpServletRequest request, T sourceEntity, boolean isDeleteFile);

}
