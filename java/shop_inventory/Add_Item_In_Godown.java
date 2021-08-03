
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
public class Add_Item_In_Godown extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String pid1 = request.getParameter("pid");  
            int pid=Integer.parseInt(pid1);
            String pn = request.getParameter("pn");
            String count1 = request.getParameter("count");
            int count=Integer.parseInt(count1);
            String price1 = request.getParameter("price");
            double price=Double.parseDouble(price1);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Add_Item_In_Godown</title>");            
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
            PreparedStatement ps;
           
            
            
             String st = request.getParameter("btn");
            if(st.equals("ADD ITEM"))
                {   
                    ps = conn.prepareStatement("INSERT INTO ROOT.GODOWN_DATA (product_id,product_name,quantity,price) values(?,?,?,?)");
                    ps.setInt(1,pid);
                    ps.setString(2,pn);
                    ps.setInt(3, count);
                    ps.setDouble(4,price);
                    int rowsaffected1 = ps.executeUpdate();
            
                     out.println("<h1> Item inserted! </h1>");
                }
            else 
                {  
                     String query = "select * from ROOT.GODOWN_DATA";
            
            //        Step 4:Execute the query
                        rs = stmt.executeQuery(query);//for  select query
                        out.println("<h1> Item updated! </h1>");
                   while (rs.next())
                            {
                             Integer Prod_ID = rs.getInt(1);
                             String Name = rs.getString(2);
                             Integer Item = rs.getInt(3);
                             Double Pricenew = rs.getDouble(4);
                             if(pn.equals(Name))
                             {
                                 if(pid==Prod_ID)
                                 {
                                 int rowsaffected = stmt.executeUpdate("update ROOT.GODOWN_DATA set quantity="+(Item+count)+",price="+price+"  where cast(PRODUCT_NAME as varchar(128)) = '"+Name+"'");
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
