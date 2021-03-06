package ee.bilal.dev.dataprocessor.configurations;

import ee.bilal.dev.dataprocessor.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Slf4j
public class LoggerInterceptor extends HandlerInterceptorAdapter {
    /**
     * Executed before actual handler is executed
     * Log request info
     **/
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) {
        log.info("[preHandle][{}][{}]{}", request, request.getMethod(),
                request.getRequestURI() + getParameters(request));

        return true;
    }

    /**
     * Executed after handler is executed
     **/
    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response,
                           final Object handler, final ModelAndView modelAndView) {
        log.info("[postHandle][{}]", request);
    }

    /**
     * Executed after complete request is finished
     **/
    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
                                final Object handler, final Exception ex) {
        if (ex != null){
            log.error("Exception: {}", ex);
        }

        log.info("[afterCompletion][{}][exception: {}]", request, ex);
    }

    /**
     * Get parameters from request
     * @param request servlet
     * @return params
     */
    private String getParameters(final HttpServletRequest request) {
        final StringBuilder posted = new StringBuilder();
        final Enumeration<?> e = request.getParameterNames();

        if (e != null) {
            posted.append("?");
        }

        while (e != null && e.hasMoreElements()) {
            if (posted.length() > 1) {
                posted.append("&");
            }

            final String curr = (String) e.nextElement();
            posted.append(curr).append("=");

            if (curr.contains("password") || curr.contains("answer") || curr.contains("pwd")) {
                posted.append("*****");
            } else {
                posted.append(request.getParameter(curr));
            }
        }

        final String ip = request.getHeader("X-FORWARDED-FOR");
        final String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;

        if (!StringUtil.isNullOrEmpty(ipAddr)) {
            posted.append("&_psip=").append(ipAddr);
        }

        return posted.toString();
    }

    /**
     * Get remote address from servlet request
     * @param request servlet
     * @return remote address
     */
    private String getRemoteAddr(final HttpServletRequest request) {
        final String ipFromHeader = request.getHeader("X-FORWARDED-FOR");

        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            log.debug("ip from proxy - X-FORWARDED-FOR : {}", ipFromHeader);
            return ipFromHeader;
        }

        return request.getRemoteAddr();
    }

}
