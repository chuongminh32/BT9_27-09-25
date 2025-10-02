package vn.iotstar.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("role") == null) {
            response.sendRedirect("/login?error=unauthenticated");
            return false;
        }

        String role = session.getAttribute("role").toString();
        String uri = request.getRequestURI();

        if (uri.startsWith("/admin") && !"ADMIN".equalsIgnoreCase(role)) {
            response.sendRedirect("/login?error=unauthorized");
            return false;
        }

        if (uri.startsWith("/user") && !"USER".equalsIgnoreCase(role) && !"ADMIN".equalsIgnoreCase(role)) {
            response.sendRedirect("/login?error=unauthorized");
            return false;
        }

        return true;
    }
}
