package fr.m2i.webdistributeur.listener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import fr.m2i.webdistributeur.utils.UserDb;

public class AppContextListener implements ServletContextListener {

    private static Logger logger = Logger.getLogger(AppContextListener.class.getName());
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("---- App started ----");
        logger.info(dtf.format(LocalDateTime.now()));
        logger.info("---- App started ----");

          // on recupere le servlet context via le servlet context event passe en param
        ServletContext ctx = sce.getServletContext();
        
        // on recupere les identifiants de la BDD stockes dans le web.xml
        String dbUser = ctx.getInitParameter("dbUser");
        String dbPass = ctx.getInitParameter("dbPass");
        
        //on recupere l'instance de notre bdd
        UserDb userDb = UserDb.getInstance(dbUser,dbPass);
        
        // si elle est nul c'est que les identifiants sont mauvais
        if (userDb == null) {
            logger.severe("/!\\ impossible de se connecter a la BDD");
            return;// on s'arrete la
        }
        
        //si on arrive ici c'est que la co a la BDD s'est bien passee -> les identifiants sont bons
        
        // on regarde dans les attributs du servlet context l'instance de notre BDD
        ctx.setAttribute("userDb", userDb);
        logger.info("---- Init done ----");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("---- App stopped ----");
        logger.info(dtf.format(LocalDateTime.now()));
        logger.info("---- App stopped ----");
    }
}
