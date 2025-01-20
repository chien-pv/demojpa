import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

public class HelloServelet extends HttpServlet {
@Override
public void doGet(HttpServletRequest req, HttpServletResponse res) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("demoPU");
    EntityManager em = factory.createEntityManager();
    em.getTransaction().begin();
    try {
            String jpaql = "SELECT o FROM User o";
            TypedQuery<User> query = em.createQuery(jpaql, User.class);
            List<User> list =  query.getResultList();
            req.setAttribute("list", list);
        } catch (Exception e) {
        // TODO: handle exception
    }
    RequestDispatcher view = req.getRequestDispatcher("/hello.jsp");      
    try {
       view.forward(req, res);
   } catch (ServletException | IOException e) {
       e.printStackTrace();
   }
}
}
