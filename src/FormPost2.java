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
        printFooter(out);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        Connection con;
        Statement stmt;

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

            PreparedStatement pst =(PreparedStatement)con.prepareStatement("INSERT INTO students(first_name, last_name, nickname)VALUES(?, ?, ?)");

            pst.setString(1, Fname);
            pst.setString(2, Lname);
            pst.setString(3, Nname);

            pst.executeUpdate();
            pst.close();

            out.println("<h3 class='conn2 text-center'>Success! Data Inserted!</h3>");

        }
        catch (Exception e) {
            out.println("<h2 class='conn2 text-center'>Data Not Inserted!</h2>");
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
        out.println("<h1 class='heading'>Enter Student Information</h1>");
        out.println("</div>");
        out.println("<div class='row'>");
        out.println("<div class='col-xs-4'></div>");
        out.println("<div class='col-xs-4'>");
    }

    public void printForm (PrintWriter out) {
        out.println("<form method='post' action ='http://localhost:7070/formpost2'>");
        out.println("<div class='form-group'>");
        out.println("<label class='label'>First Name:</label>");
        out.println("<input type='text' name='fName' class='form-control' placeholder='John'>");
        out.println("</div>");
        out.println("<div class='form-group'>");
        out.println("<label class='label'>Last Name:</label>");
        out.println("<input type='text' name='lName' class='form-control' placeholder='Wayne'>");
        out.println("</div>");
        out.println("<div class='form-group'>");
        out.println("<label class='label'>Nickname:</label>");
        out.println("<input type='text' name='nName' class='form-control' placeholder='Duke'>");
        out.println("</div>");
        out.println("<button type='submit' class='btn btn-default btn-block btn-primary'>Submit</button>");
        out.println("</form>");
    }

    public void printFooter(PrintWriter out){
        out.println("</div>");
        out.println("<div class='col-xs-4'></div>");
        out.println("</div>");
        out.println("<div class='row'>");
        out.println("<div class='col-xs-5'></div>");
        out.println("<div class='col-xs-2'>");
        out.println("<button class='btn btn-default btn-block data-btn'><a href='/studentdata'>View Data</a></button>");
        out.println("<button class='btn btn-default btn-block home-btn'><a href='index.jsp'>Home</a></button>");
        out.println("</div>");
        out.println("<div class='col-xs-5'></div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
