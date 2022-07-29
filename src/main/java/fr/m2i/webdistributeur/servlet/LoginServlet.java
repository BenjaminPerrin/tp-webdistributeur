package fr.m2i.webdistributeur.servlet;

import fr.m2i.webdistributeur.entity.Role;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.m2i.webdistributeur.entity.User;
import fr.m2i.webdistributeur.entity.Role;

import fr.m2i.webdistributeur.utils.UserDb;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/META-INF/login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // on recupere notre userDb depuis les attributs du servlet context (cree dans le lifecycleListener)
        UserDb userDb= (UserDb) this.getServletContext().getAttribute("userDb");
        // On récupère les paramètres du formulaire
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // On vérifie dans notre base de donnée qu'un user existe avec les identifiants envoyés
        User user = userDb.checkUser(email, password);

        // Si le user est null, les identifiants sont invalides
        // On set le message d'erreur et on affiche la page de login
        if (user == null) {
            request.setAttribute("error", "Veuillez vérifier vos identifiants !");
            this.getServletContext().getRequestDispatcher("/META-INF/login.jsp").forward(request, response);
            return; // on arrête la méthode ici
        }

        // Si on arrive ici c'est que le user est différent de null -> on la trouver dans notre base de donnée
        
        // On créer une nouvelle session avec le paramètre true
        HttpSession session = request.getSession(true);
        // On stock le user connecter dans la session
        session.setAttribute("user", user);

        // On affiche la page en fonction du Role
        if (user.getRole() == Role.PROVIDER) {
            response.sendRedirect("/webdistributeur/provider/HandleProductServlet");
        } else if(user.getRole() == Role.USER) {
            response.sendRedirect("ShowProductServlet");
        } else if(user.getRole() == Role.ADMIN) {
            response.sendRedirect("/webdistributeur/admin/HandleUserServlet");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
