package africa.semicolon.BookFinder.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class FilterConfiguration implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Headers","Content-Type, Authorization, Content-Length, X-Requested-With");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, PATCH,Option");
        response.setHeader("Access-Control-Allow-Credentials","true");


        if(request.getMethod().equalsIgnoreCase("Option")) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
        else{
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {
    }
}
