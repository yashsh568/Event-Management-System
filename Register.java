import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html");
       PrintWriter out = response.getWriter();
        
       String a1 = request.getParameter("ename");
       String a2 = request.getParameter("enum");
       String a3 = request.getParameter("cardno");
       String a4 = request.getParameter("edate");
       String a5 = request.getParameter("cvv");
       String a6 = request.getParameter("cname");
       
       if(a1.isBlank() && a2.isBlank() && a3.isBlank() && a4.isBlank() && a5.isBlank() && a6.isBlank()){
            response.setContentType("text/html");  
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('Please Enter Event Details!!!');");  
            out.println("</script>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Registration.html");
            requestDispatcher.include(request, response);
       }else{
           try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String conURL = "jdbc:mysql://localhost:3306/EventlyDB";
                String dbusername = "root";
                String dbuserpassword = "abc";
                Connection con;
                con = DriverManager.getConnection(conURL , dbusername, dbuserpassword);
                con.setAutoCommit(false);
                Statement statement = con.createStatement();
                String mysqlQuery = "insert into card values('"+a1+"','"+a2+"','"+a3+"','"+a4+"','"+a5+"','"+a6+"') ";
                statement.executeUpdate(mysqlQuery);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Payment.html");
                requestDispatcher.forward(request, response);
                con.commit();
                con.close();
            }catch(ServletException | IOException | ClassNotFoundException | SQLException e){
                System.out.println("Exception Caught: " + e);
            }
       }
       
    }

}
