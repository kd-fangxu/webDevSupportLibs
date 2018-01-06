package com.xqSupport.Filter;

//import com.xq.moduleUser.Bean.ModuleuserEntity;
//import com.xq.moduleUser.Dao.IUserDao;
//import com.xq.moduleUser.SeviceProvider.SignedManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by xu on 2017/6/29.
 */
@WebFilter
public class FilterClientSignedCheck implements Filter {
//    @Autowired
//    IUserDao iUserDao;

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

//        Map<String, String[]> parameterMap = servletRequest.getParameterMap();
//        if (parameterMap.containsKey("userId") && parameterMap.containsKey("signedStr") && parameterMap.containsKey("timeTag")) {
//            if (!SignedManager.isTimeTagEffected(Long.valueOf(servletRequest.getParameter("timeTag")))) {
//                servletResponse.getWriter().write("Test");
//                return;//时间失效
//            }
//            ModuleuserEntity moduleuserEntity = iUserDao.getUserById(Long.valueOf(String.valueOf(parameterMap.get("userId"))));//之后优化为缓存获取
//            String signedStrServer = SignedManager.createSignedStr(moduleuserEntity.getAccessToken(), String.valueOf(parameterMap.get("timeTag")));
//            if (!signedStrServer.equals(servletRequest.getParameter("signedStr"))) {
//                return;//签名不合法
//            }
//        }
//
//        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }
}
