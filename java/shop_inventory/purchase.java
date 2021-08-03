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
public class purchase extends HttpServlet {
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            double cost=0.0,amount=0.0;
            
            String pn = request.getParameter("pn");
            String count1 = request.getParameter("count");
            int count=Integer.parseInt(count1);
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>purchase</title>");            
            out.println("</head>");
            out.println("<body>");
            
           
        String st = request.getParameter("btn");
        if(st.equals("ADD"))
            {   
                out.println("<a href=\"http://localhost:35075/Shop_Inventory/purchase1?btn1=PURCHASE\">ADD</a>");
                out.println("<a href=\"http://localhost:35075/Shop_Inventory/Payment\">PAY</a>");
            }
        else if(st.equals("PAY"))
            {   response.sendRedirect("Payment");
                //out.println("<h3>Final Bill</h3>");
            }
        
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
            PreparedStatement ps,ps1;
            
            String query = "select * from ROOT.SHOP_DATA";
            
//        Step 4:Execute the query
            rs = stmt.executeQuery(query);//for  select query
            
               out.println("<table style=\"width:100%\">" +
            "  <tr>" +
            "    <th style=\"text-align: left\">Product_id</th>" +
            "    <th style=\"text-align: left\">Product_Name</th>" +
            "    <th style=\"text-align: left\">Quantity</th>" +
            "    <th style=\"text-align: left\">Price</th>" +
            "  </tr>");
              
            
             while (rs.next())
              {
               Integer Prod_ID = rs.getInt(1);
               String Name = rs.getString(2);
               Integer Item = rs.getInt(3);
               Double Price = rs.getDouble(4);
               if(pn.equals(Name))
               {
                    if(count<=Item)
                   {
                    cost=count*Price;
                   
                    out.println("<br>");
                    
                   // out.println("<br>"+"Product_id : " + Prod_ID +"&emsp;"+"&emsp;"+"&emsp;"+"Product Name : " + Name +"&emsp;"+"&emsp;"+"&emsp;"+"Quantity : "+count);
                    out.println("<h3> Total Price is: "+cost+"</h3>");
                    
                   int cn=Item-count;
                   //int rowsaffected = stmt.executeUpdate("update ROOT.SHOP_DATA set quantity="+cn+" where cast(PRODUCT_NAME as varchar(128)) = 'MOTO TV'");
                   int rowsaffected = stmt.executeUpdate("update ROOT.SHOP_DATA set quantity="+cn+" where cast(PRODUCT_NAME as varchar(128)) = '"+Name+"'");
                    
            
                   
            ps = conn.prepareStatement("INSERT INTO ROOT.TEMPORARY_DATA (product_id,product_name,quantity,price) values(?,?,?,?)");
            ps.setInt(1, Prod_ID);
            ps.setString(2, Name);
            ps.setInt(3, count);
            ps.setDouble(4, cost);
            int rowsaffected1 = ps.executeUpdate();
            
             ps1 = conn.prepareStatement("INSERT INTO ROOT.DAILYSALE (product_id,product_name,quantity,price) values(?,?,?,?)");
            ps1.setInt(1, Prod_ID);
            ps1.setString(2, Name);
            ps1.setInt(3, count);
            ps1.setDouble(4, cost);
            int rowsaffected2 = ps1.executeUpdate();
            
                     out.println("<h3>Data is inserted in temopary table successfully!!</h3>");
                     
                     rs1 = stmt.executeQuery("select * from root.temporary_data");
                         while (rs1.next())
                         {
                          Integer ID = rs1.getInt(1);
                          String Names = rs1.getString(2);
                          Integer Items = rs1.getInt(3);
                          Double Prices = rs1.getDouble(4);
                               amount=amount+Prices;
                               
                                 
                                        out.println(" <tr>" +
                                  "    <td>"+ID+"</td>" +
                                  "    <td>"+ Names+"</td>" +
                                  "    <td>"+Items+"</td>" +
                                   "    <td>"+Prices+"</td>" +
                                  "  </tr>");
                                
                              // out.println("Product_id : " + ID +"&emsp;"+"&emsp;"+"&emsp;"+"Product Name : " + Names +"&emsp;"+"&emsp;"+"&emsp;"+"Quantity : "+Items+"&emsp;"+"&emsp;"+"&emsp;"+"Total Price : "+Prices);
                               //out.println("<br>");
                         }
                         
                         out.println("<h3> Total count left in stock : "+cn+"</h3>");
                        
                         break;
                    }
                }
             }
                         
                         out.println("<h3>Final Bill = "+amount+"</h3>");
                                
               
              
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
