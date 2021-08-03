package shop_inventory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TodaysSale extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Today's Sale</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println(new java.util.Date());
            out.println("<h1> Displaying all items purchased with total price</h1>");
            
            out.print("<form action=\"ClearData\" method=\"GET\">\n" +
                "            <input type=\"submit\" name=\"btn1\" value=\"CLEAR TODAY'S DATA\"/>\n" +
                "      </form>");
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
         try {
//        Step 1: Register the driver class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("Driver registered");

//        STEP 2: Establish Connection
          conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Shop_Inventory", "root", "root");
            System.out.println("Connection established");

//        Step 3: Create Statement
            stmt = conn.createStatement();
            
            
            String query = "select * from ROOT.DAILYSALE";

//        Step 4:Execute the query
            rs = stmt.executeQuery(query);//for  select query
            
            /* String st = request.getParameter("btn1");
               if(st.equals("btn1"))
                    {   
                        int r=stmt.executeUpdate("delete from root.dailysale");
                    }*/
            out.println("<br>");
            out.println("<table style=\"width:100%\">" +
            "  <tr>" +
            "    <th style=\"text-align: left\">Product_id</th>" +
            "    <th style=\"text-align: left\">Product_Name</th>" +
            "    <th style=\"text-align: left\">Quantity</th>" +
            "    <th style=\"text-align: left\">Price</th>" +
            "  </tr>");
            
               //out.println("<br>");
            
              while (rs.next()) {
               Integer Product_ID = rs.getInt(1);
               String Name = rs.getString(2);
               Integer Item = rs.getInt(3);
               Double Price = rs.getDouble(4);
                
               out.println(" <tr>" +
                 "    <td>"+Product_ID+"</td>" +
                 "    <td>"+ Name+"</td>" +
                 "    <td>"+Item+"</td>" +
                  "    <td>"+Price+"</td>" +
                 "  </tr>");
              // out.println("<br>");
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
