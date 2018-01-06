package com.xqSupport.common.RestFul.Service;

import com.xqSupport.common.BaseResponse;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by developer on 2017/7/7.
 */
public interface IRestSer {


    BaseResponse doRestFulApi(HttpServletRequest request, @PathVariable String action, @PathVariable String moduleName, @PathVariable String targetEntityName);

    Object saveEntity(Object ob);

    void updateEntity(Object ob);

    void delete(Object object);

    List<Object> find(String queryStr);


}
