import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class StudentData extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

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
            rs = stmt.executeQuery("SELECT * FROM students ORDER BY last_name");
        }
        catch (SQLException e) {
            out.println("<h1>Unable To Retrieve Students</h1>");
        }
        try {
            out.println("<table class='table table-bordered'>");

            out.println("<tr>");

            out.println("<th>");
            out.println("  First  ");
            out.println("</th>");

            out.println("<th>");
            out.println("  Last  ");
            out.println("</th>");

            out.println("<th>");
            out.println("  Nickname  ");
            out.println("</th>");

            out.println("</tr>");

            while(rs.next()) {
                out.println("<tr>");
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    out.println("<td>");
                    out.print((rs.getString(i)).trim() + " ");
                    out.println("</td>");
                }
                out.print("</tr>");
            }
            out.println("</table>");
        }
        catch (Exception e) {
            out.println("<h1>Unable To Create Table</h1>");
        }
        printFooter(out);
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
        out.println("<h1 class='heading'>Students</h1>");
        out.println("</div>");
        out.println("<div class='row'>");
        out.println("<div class='col-xs-4'></div>");
        out.println("<div class='col-xs-4'>");
    }
    public void printFooter(PrintWriter out){
        out.println("</div>");
        out.println("<div class='col-xs-4'></div>");
        out.println("<div class='row'>");
        out.println("<div class='col-xs-5'></div>");
        out.println("<div class='col-xs-2 text-center'>");
        out.println("<button class='btn btn-default btn-block data-btn'><a href='/formpost2'>Enter Data</a></button>");
        out.println("<button class='btn btn-default btn-block home-btn'><a href='index.jsp'>Home</a></button>");
        out.println("</div>");
        out.println("<div class='col-xs-5'></div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
