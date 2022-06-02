import lombok.extern.java.Log;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        // Get all the names of request parameters
        Enumeration names = request.getParameterNames();
        out.println("<p>Request Parameter Names are: ");
        if (names.hasMoreElements()) {
            out.print(htmlFilter(names.nextElement().toString()));
        }
        do {
            out.print(", " + htmlFilter(names.nextElement().toString()));
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
                        "</ul>\n"
        );
        // link back to the form
        out.println(
                "<a href=\"form-get.html\">Fill out form again</a>" +
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
        // Get all the names of request parameters
        Enumeration names = request.getParameterNames();
        out.println("<p>Request Parameter Names are: ");
        if (names.hasMoreElements()) {
            out.print(htmlFilter(names.nextElement().toString()));
        }
        do {
            out.print(", " + htmlFilter(names.nextElement().toString()));
        } while (names.hasMoreElements());
        out.println(".</p>");
        // method adding the same html string as in doGet
        insertFormData(out, firstName, lastName, email);

        out.println(
                "<a href=\"form-post.html\">Fill out form again</a>" +
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

    // Filter the string for special HTML characters to prevent
    // command injection attack
    private static String htmlFilter(String message) {
        if (message == null) return null;
        int len = message.length();
        StringBuffer result = new StringBuffer(len + 20);
        char aChar;

        for (int i = 0; i < len; ++i) {
            aChar = message.charAt(i);
            switch (aChar) {
                case '<': result.append("&lt;"); break;
                case '>': result.append("&gt;"); break;
                case '&': result.append("&amp;"); break;
                case '"': result.append("&quot;"); break;
                default: result.append(aChar);
            }
        }
        return (result.toString());
    }
}
