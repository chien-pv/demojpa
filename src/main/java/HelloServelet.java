import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(urlPatterns = {"/home", "/users"})
public class HelloServelet extends HttpServlet {
@Override
public void doGet(HttpServletRequest req, HttpServletResponse res) {
    String path = req.getServletPath();
    switch (path) {
        case "/home":
            RequestDispatcher viewHome = req.getRequestDispatcher("/home.jsp");      
            try {
                viewHome.forward(req, res);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            break;
        case "/users":
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("demoPU");
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            try {
                    String jpaql = "SELECT o FROM User o";
                    TypedQuery<User> query = em.createQuery(jpaql, User.class);
                    List<User> list =  query.getResultList();
                    req.setAttribute("list", list);
            } catch (Exception e) {}
            RequestDispatcher view = req.getRequestDispatcher("/hello.jsp");      
            try {
                view.forward(req, res);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            break;
        default:
            RequestDispatcher viewDefault = req.getRequestDispatcher("/index.jsp");      
            try {
                viewDefault.forward(req, res);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            break;
    }
}
}
