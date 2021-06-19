package ir.maktab.configuration;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author : Bahar Zolfaghari
 **/
public class LastViewInterceptor implements HandlerInterceptor {
    public static final String LAST_VIEW_ATTRIBUTE = LastViewInterceptor.class + ".LastView";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (modelAndView != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute(LAST_VIEW_ATTRIBUTE, modelAndView.getViewName());
        }
    }
}
