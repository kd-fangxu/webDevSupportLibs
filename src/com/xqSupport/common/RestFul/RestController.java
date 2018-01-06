package com.xqSupport.common.RestFul;

import com.xqSupport.common.BaseResponse;
import com.xqSupport.common.RestFul.Service.IRestSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by developer on 2017/7/7.
 */
@Controller
@RequestMapping(path = "/rest")
public class RestController {
    @Autowired
    IRestSer restSer;

    @ResponseBody
    @RequestMapping(path = "/{action}/{moduleName}/{targetEntityName}")
    public BaseResponse doRest(HttpServletRequest request, @PathVariable String action, @PathVariable String moduleName, @PathVariable String targetEntityName) {
        return restSer.doRestFulApi(request, action, moduleName, targetEntityName);
    }
}
