package fr.m2i.webdistributeur.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {  
//        // on cast en HttpServletRequest pour ensuite recuperer la session
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpSession session = req.getSession(false);
//        
//        // on verifie qu'un user est connecte
//        boolean isLoggedIn = session != null && session.getAttribute("user") != null;
//        
//        //si pas co on affiche la page login
//        if (!isLoggedIn) {
//            this.context.log("User not connected");
//            request.getRequestDispatcher("/META-INF/login.jsp").forward(request,response);
//            return;
//        }
        
        // tout va bien on passe au filter suivant
        chain.doFilter(request, response);
    }

    public void destroy() {
        //we can close resources here
    }
}
