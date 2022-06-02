import lombok.extern.java.Log;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

@Log
@WebServlet("/form")
public class FormServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // gather form values from the request object
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");
        String[] languages = request.getParameterValues("language");
        String langStr = Arrays.toString(languages);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Using GET Method to Read Form Data";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n");
        // Get all the names of request parameters
        Enumeration names = request.getParameterNames();
        out.println("<p>Request Parameter Names are: ");
        if (names.hasMoreElements()) {
            out.print(names.nextElement().toString());
        }
        do {
            out.print(", " + names.nextElement().toString());
        } while (names.hasMoreElements());
        out.println(".</p>");
        out.println(
                "<ul>\n" +
                "  <li><b>First Name</b>: "
                + firstName + "\n" +
                "  <li><b>Last Name</b>: "
                + lastName + "\n" +
                "  <li><b>Email</b>: "
                + email + "\n" +
                        "  <li><b>Languages</b>: "
                        + langStr + "\n" +
                "</ul>\n" +
                "</body>" +
                "<a href=\"form-get.html\">Fill out form again</a>" +
                "</html>"
        );

        // just to save time from repeating static html line by line,
        // we can insert it from a file instead
//        request.getRequestDispatcher("/form-get.html")
//                .include(request, response);
        // Then we can add things
//        out.println("<h3>And some more HTML added by the method called by a GET request to /form</h3>");
        out.close();
    }


}
