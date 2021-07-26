import lombok.extern.java.Log;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@Log
@WebServlet("/servlet")
public class servletone extends HttpServlet {
    static volatile int counter= 0;

    @Override
    public void init(ServletConfig config) throws ServletException {
        config = getServletConfig();
        log.info("Servlet initialized");
    }

    @Override
    public void destroy() {
        System.out.println("exit");
        log.warning("servlet got destroy!");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse respond) throws ServletException, IOException {
        Car car = new Car(1, "BMW", "530I");

        counterIncrement();
        PrintWriter out = respond.getWriter();
        respond.setContentType("text/html");
        out.print("<html> <head><title>Servlet one</title></head>");
        out.print("<body> <h1>Hii Welcome to the world of Servlets!</h1>");
        out.print("i was visited: " + counter + " times!");
        out.print("<p>Car ID: <b>"+car.getId()+"</b> Car Make: <b>"+car.getMake()+"</b> Car Model: <b>"+car.getModel()+"</b></p>");
        out.print("</body> </html>");
    }
    protected static void counterIncrement(){
        if(counter > 100)
            System.exit(1);
        counter++;
    }
}
