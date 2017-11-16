package com.tianqian.self.config.filter;

import com.alibaba.fastjson.JSON;
import com.tianqian.self.common.base.BaseCodeEnum;
import com.tianqian.self.common.base.BaseResult;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter extends FormAuthenticationFilter {

	private static final Logger log = LoggerFactory.getLogger(LoginFilter.class);

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				if (log.isTraceEnabled()) {
					log.trace("Login submission detected.  Attempting to execute login.");
				}
				return executeLogin(request, response);
			} else {
				if (log.isTraceEnabled()) {
					log.trace("Login page view.");
				}
				return true;
			}
		} else {
			if (log.isTraceEnabled()) {
				log.trace("Attempting to access a path which requires authentication.  Forwarding to the "
						+ "Authentication url [" + getLoginUrl() + "]");
			}
			HttpServletResponse res = WebUtils.toHttp(response);
			res.setCharacterEncoding("UTF-8");
			res.setContentType("application/json; charset=utf-8");
			res.setStatus(HttpServletResponse.SC_OK);
			res.setHeader("sessionstatus", "timeout");
			res.getWriter().write(JSON.toJSONString(new BaseResult<String>(false, BaseCodeEnum.FAILURE.getIndex(), "timeout")));
			return false;
		}
	}

}
