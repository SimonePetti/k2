package filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class ErrorHandlingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inizializzazione del filtro
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // Registrare l'eccezione nei log
            t.printStackTrace(); // Usa un logger in produzione

            // Reindirizzare alla pagina di errore
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/error500.jsp");
        }
    }

    @Override
    public void destroy() {
        // Pulizia delle risorse del filtro
    }
}
