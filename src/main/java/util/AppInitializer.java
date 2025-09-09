package util;

import dao.MatchDao;
import dao.PlayerDao;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.FinishedMatchesPersistenceService;
import service.MatchListingService;
import service.MatchScoreCalculationService;
import service.OngoingMatchesService;

@WebListener
public class AppInitializer implements ServletContextListener {
    private SessionFactory sessionFactory;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        sce.getServletContext().setAttribute("sessionFactory", sessionFactory);

        MatchDao matchDao = new MatchDao(sessionFactory);

        sce.getServletContext().setAttribute("ongoingMatchesService", new OngoingMatchesService());
        sce.getServletContext().setAttribute("matchScoreService", new MatchScoreCalculationService());
        sce.getServletContext().setAttribute("finishedMatchesService", new FinishedMatchesPersistenceService(matchDao));
        sce.getServletContext().setAttribute("matchListingService", new MatchListingService(matchDao));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sessionFactory.close();
    }
}
