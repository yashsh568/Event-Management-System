import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class AddEvent extends HttpServlet {

   
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html");
       PrintWriter out = response.getWriter();
       
       String a1 = request.getParameter("EventNo");
       String a2 = request.getParameter("EventName");
       String a3 = request.getParameter("coordinatorName");
       String a4 = request.getParameter("CoordinatorNo");
       String a5 = request.getParameter("fee");
       String a6 = request.getParameter("venue");
       String a7 = request.getParameter("date");
       
       if(a1.isBlank() && a2.isBlank() && a3.isBlank() && a4.isBlank() && a5.isBlank() && a6.isBlank() && a7.isBlank()){
            response.setContentType("text/html");  
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('Please Enter Event Details!!!');");  
            out.println("</script>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("CreateE.html");
            requestDispatcher.include(request, response);
       }else{
           try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String conURL = "jdbc:mysql://localhost:3306/EventlyDB";
                String userName = "root";
                String userPassword = "abc";
                Connection con = DriverManager.getConnection(conURL, userName, userPassword);
                con.setAutoCommit(false); 

                Statement statement = con.createStatement();
                String queryStatement = "insert into Event values('"+a1+"','"+a2+"','"+a3+"','"+a4+"','"+a5+"','"+a6+"','"+a7+"')";
                statement.executeUpdate(queryStatement);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("CreateE.html");
                requestDispatcher.include(request, response);


                response.setContentType("text/html");  
                out.println("<script type=\"text/javascript\">");  
                out.println("alert('Success! ... Event Details Added To Database!');");  
                out.println("</script>");
                con.commit();
                con.close();
            }catch(ServletException | IOException | ClassNotFoundException | SQLException e){
                System.out.println("Exception caught: " + e);
            }
           
       }
       
    }

}
