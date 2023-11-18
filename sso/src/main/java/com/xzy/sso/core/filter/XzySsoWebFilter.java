package com.xzy.sso.core.filter;

import com.xzy.sso.core.conf.Conf;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date: 2023/8/1
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class XzySsoWebFilter extends HttpServlet implements Filter {


    private String ssoServer;
    private String logoutPath;
    private String excludedPaths;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ssoServer = filterConfig.getInitParameter(Conf.SSO_SERVER);
        logoutPath = filterConfig.getInitParameter(Conf.SSO_LOGOUT_PATH);
        excludedPaths = filterConfig.getInitParameter(Conf.SSO_EXCLUDE_PATHS);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        final String servletPath = req.getServletPath();
        if (excludedPaths != null && excludedPaths.trim().length() > 0) {
            for (String excludePath : excludedPaths.split(",")) {
                final String uriPattern = excludePath.trim();
                //支持ant表达式

            }
        }


    }
}