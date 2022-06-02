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
@WebServlet("/form")
public class FormServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // gather form values from the request object
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Using GET Method to Read Form Data";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n");// +
        out.println(
                "<ul>\n" +
                        "  <li><b>First Name</b>: "
                        + firstName + "\n" +
                        "  <li><b>Last Name</b>: "
                        + lastName + "\n" +
                        "  <li><b>Email</b>: "
                        + email + "\n" +
                        "</ul>\n"
        );
        // link back to the form
        out.println(
                "<a href=\"form_get.html\">Fill out form again</a>" +
                        "</body>" +
                        "</html>"
        );
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // we can get the params from a POST request the same way
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Using POST Method to Read Form Data";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n");// +

        // method adding the same html string as in doGet
        insertFormData(out, firstName, lastName, email);

        out.println(
                "<a href=\"form_post.html\">Fill out form again</a>" +
                "</body>" +
                "</html>"
        );
        out.close();
    }

    private void insertFormData(PrintWriter out, String firstName, String lastName, String email){
        out.println(
                "<ul>\n" +
                "  <li><b>First Name</b>: "
                + firstName + "\n" +
                "  <li><b>Last Name</b>: "
                + lastName + "\n" +
                "  <li><b>Email</b>: "
                + email + "\n" +
                "</ul>\n"
        );
    }
}
