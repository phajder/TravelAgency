package com.hajder.travelagency.filter;

import com.hajder.travelagency.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hajder.travelagency.filter.AdminFilter.ADMIN_PATH;
import static com.hajder.travelagency.filter.AdminFilter.SUPER_ADMIN_PATH;

/**
 * Filtering /admin/* and /super-admin/* paths for unauthorized entries.
 * Created by Piotr on 19.12.2016.
 * @see javax.servlet.Filter
 * @author Piotr Hajder
 */
@WebFilter(filterName = "AdminFilter", urlPatterns = { ADMIN_PATH + "/*", SUPER_ADMIN_PATH + "/*" })
public class AdminFilter implements Filter {
    static final String ADMIN_PATH = "/admin";
    static final String SUPER_ADMIN_PATH = "/super-admin";
    private static final String ADMIN_LOGIN = "/admin-login.xhtml";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if(user != null) {
            String ctx = request.getRequestURI();
            if(ctx.startsWith(SUPER_ADMIN_PATH) ? user.isSuperAdmin() : user.isAdmin()) {
                filterChain.doFilter(request, response);
            } else {
                response.sendError(403);
            }
        } else {
            response.sendRedirect(request.getContextPath() + ADMIN_LOGIN);
        }
    }

    @Override
    public void destroy() {
    }
}
