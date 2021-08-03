package shop_inventory;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tejas Sathawane
 */
public class ChangePrice extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Change Price</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Item details! </h1>");
            out.print("<form action=\"Change_Price_In_Both\" method=\"GET\">\n");
            out.println("Enter Product ID: "+"<input type=\"text\" name=\"pid\"/>\n");
            out.println("<br><br>");
            out.println("Enter Product name: "+"<input type=\"text\" name=\"pn\"/>\n");
            out.println("<br><br>");
            out.println("Enter price per item: "+"<input type=\"text\" name=\"price\"/>\n");
            
             out.println("<br><br>");
            out.print("<input type=\"submit\" name=\"btn\" value=\"CHANGE PRICE\"/>\n" +
                "      </form>");
            
            out.println("<h1>Also display the database (change price only) simulatneously</h1>");
            
            
            
            
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
