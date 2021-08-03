
package shop_inventory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tejas Sathawane
 */
public class Remove_Item_From_Godown extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String pid1 = request.getParameter("pid");  
            int pid=Integer.parseInt(pid1);
            String pn = request.getParameter("pn");
            String count1 = request.getParameter("count");
            int count=Integer.parseInt(count1);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Remove_Item_From_Godown</title>");            
            out.println("</head>");
            out.println("<body>");
           
            
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        
         try {
//        Step 1: Register the driver class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("Driver registered");

//        STEP 2: Establish Connection
          conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Shop_Inventory", "root", "root");
          System.out.println("Connection established");

//        Step 3: Create Statement
            stmt = conn.createStatement();
             String st = request.getParameter("btn");
            if(st.equals("REMOVE"))
                {  
                     String query = "select * from ROOT.GODOWN_DATA";
            
                        rs = stmt.executeQuery(query);//for  select query
                       // out.println("<h1> Item removed! </h1>");
                   while (rs.next())
                            {
                             Integer Prod_ID = rs.getInt(1);
                             String Name = rs.getString(2);
                             Integer Item = rs.getInt(3);
                             if(pn.equals(Name))
                             {
                                 if(pid==Prod_ID && count<=Item)
                                 {
                                     out.println("<h1> Item removed! </h1>");
                                     int rowsaffected = stmt.executeUpdate("update ROOT.GODOWN_DATA set quantity="+(Item-count)+"  where cast(PRODUCT_NAME as varchar(128)) = '"+Name+"'");
                                 }
                                 else if(count>Item){
                                     out.println("<h1> Item not removed! Since count is exceeded than items limit.</h1>");
                                 }
                                 
                             }
                            }
                   
                   
                }
            
            
            
            
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {   
//        Step 6: Close all resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (rs1 != null) {
                    rs1.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Exception occured while releasing resources");
            }
        } 
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
