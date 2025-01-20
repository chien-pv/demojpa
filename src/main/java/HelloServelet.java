import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloServelet extends HttpServlet {
@Override
public void doGet(HttpServletRequest req, HttpServletResponse res) {
    RequestDispatcher view = req.getRequestDispatcher("/hello.jsp");      
    try {
       view.forward(req, res);
   } catch (ServletException | IOException e) {
       e.printStackTrace();
   }
}
}
