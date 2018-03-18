import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class FormPost2 extends HttpServlet{
    private String myUrl = "index.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        printHeader(out);
        printForm(out);
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
            rs = stmt.executeQuery("SELECT * FROM students");
        }
        catch (SQLException e) {
            out.println("<h1>No Bueno</h1>");
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
            out.println("<h1>No Dice</h1>");
        }
        printFooter(out);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        Connection con = null;
        Statement stmt = null;

        ResultSet rs = null;

        printHeader(out);
        printForm(out);

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
            String Fname = request.getParameter("fName").toString();
            String Lname = request.getParameter("lName").toString();
            String Nname = request.getParameter("nName").toString();

            PreparedStatement pst =(PreparedStatement)con.prepareStatement("INSERT INTO students(First, Last, Nick)VALUES(?, ?, ?)");

            pst.setString(1, Fname);
            pst.setString(2, Lname);
            pst.setString(3, Nname);

            pst.executeUpdate();
            pst.close();

        }
        catch (Exception e) {
            out.println("Data Not Inserted");
        }
        try {
            rs = stmt.executeQuery("SELECT * FROM students");
        }
        catch (SQLException e) {
            out.println("<h1>No Bueno</h1>");
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
            out.println("  Nickname ");
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
            out.println("<h1>No Dice</h1>");
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

    public void printForm (PrintWriter out) {

        out.println("<form method='post' action ='http://localhost:7070/formpost2'>");
        out.println("<div class='form-group'>");
        out.println("<label class='label'>First Name:</label>");
        out.println("<input type='text' name='fName' class='form-control'>");
        out.println("</div>");
        out.println("<div class='form-group'>");
        out.println("<label class='label'>Last Name:</label>");
        out.println("<input type='text' name='lName' class='form-control'>");
        out.println("</div>");
        out.println("<div class='form-group'>");
        out.println("<label class='label'>Nickname:</label>");
        out.println("<input type='text' name='nName' class='form-control'>");
        out.println("</div>");
        out.println("<div class='text-center'>");
        out.println("<button type='submit' class='btn btn-default'>Submit</button>");
        out.println("</div>");
        out.println("</form>");

    }
    public void printFooter(PrintWriter out){
        out.println("</div>");
        out.println("<div class='col-xs-4'></div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
