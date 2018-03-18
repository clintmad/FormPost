import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class CreateTable extends HttpServlet{
    private String myUrl = "localhost:7070";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        Connection con = null;
        Statement stmt = null;

        printHeader(out);

        try {
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student1", "pass");
            stmt = con.createStatement();
        }
        catch (Exception e) {
            out.println("<h1>Unable to connect to database.</h1>");
            printFooter(out);
            return;
        }

        try {
            stmt.executeUpdate("DROP TABLE Students");
            out.println("<b>Students Dropped</b>");
            out.println("<br />");
        }
        catch (SQLException e) {
            out.println("<b>Table does not exist</b>");
        }

        try {
            stmt.executeUpdate("CREATE TABLE students(First CHAR(20) NOT NULL, Last CHAR(20) NOT NULL, Nick CHAR(20) NOT NULL)");
            out.println("<b>Table Created</b>");
            out.println("<br />");
        }
        catch (SQLException e) {
            out.println("<b>Unable to create table</b>");
        }

        try {
            stmt.close();
            con.close();
            out.println("Connection closed</b>");
            out.println("<br />");
        }
        catch (SQLException e) {
            out.println("<b>Close failed</b>");
        }

        printFooter(out);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

    }
    public void printHeader(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<title>");
        out.println("Clint Week 5");
        out.println("</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div>");
    }

    public void printFooter(PrintWriter out){
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
