package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class ClickjackingFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inizializzazione del filtro
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        
        // Aggiungi gli header anti-clickjacking a tutte le risposte HTTP
        httpServletResponse.setHeader("Content-Security-Policy", "frame-ancestors 'self'");
        httpServletResponse.setHeader("X-Frame-Options", "SAMEORIGIN");
        
        // Prosegui con la catena del filtro
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        // Pulizia delle risorse del filtro
    }
}

