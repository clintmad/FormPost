import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class CreateTable extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        Connection con;
        Statement stmt;

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
            stmt.executeUpdate("DROP TABLE students");
            out.println("<h3 class='conn'>Students Table Dropped</h3>");
        }
        catch (SQLException e) {
            out.println("<h3 class='conn'>Table Does Not Exist</h3>");
        }

        try {
            stmt.executeUpdate("CREATE TABLE students(first_name CHAR(20) NOT NULL, last_name CHAR(20) NOT NULL, nickname CHAR(20) NOT NULL)");
            out.println("<h3 class='conn'>Table Created</h3>");
        }
        catch (SQLException e) {
            out.println("<h3 class='conn'>Unable To Create Table</h3>");
        }

        try {
            stmt.close();
            con.close();
            out.println("<h3 class='conn'>Connection Closed</h3>");
        }
        catch (SQLException e) {
            out.println("<h3 class='conn'>Close Failed</h3>");
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
        out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>");
        out.println("<link rel='stylesheet' href='styles/main.css' type='text/css'>");
        out.println("</head>");
        out.println("<body class='container-fluid'>");
        out.println("<div class='row text-center'>");
        out.println("<h1 class='heading'>Success!</h1>");
        out.println("</div>");
        out.println("<div class='row'>");
        out.println("<div class='col-xs-5'></div>");
        out.println("<div class='col-xs-2 text-center'>");
    }

    public void printFooter(PrintWriter out){
        out.println("<button class='btn btn-default btn-block data-btn'><a href='/formpost2'>Enter Data</a></button>");
        out.println("<button class='btn btn-default btn-block home-btn'><a href='index.jsp'>Home</a></button>");
        out.println("</div>");
        out.println("<div class='col-xs-5'></div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
