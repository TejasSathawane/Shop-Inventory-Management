package shop_inventory;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    private String user = "Tejas";
    private String password = "t@123";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String un=request.getParameter("un");
        String pwd=request.getParameter("pwd");
        
        boolean match = un.equals(user) && pwd.equals(password);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            if(match)
            {
                out.println("<title>Welcome to SAI ELECTRONICS!!</title>");   
            }
            else
            {
                out.println("<title>Please TRY again -- username or password not matched</title>");   
            }
            out.println("</head>");
            out.println("<body>");
            
           // RequestDispatcher rd = request.getRequestDispatcher("/DisplayStudentList");
            if(match)
            {
                out.println("<h1>Login is Successful. Welcome " +un+ "!</h1>");
                out.print("<form action=\"Shop.jsp\" method=\"GET\">\n" +
                    " <input type =\"hidden\" name=\"hiddenUN\" value =\""+un+"\" /> " +
                        " <input type =\"hidden\" name=\"hiddenPWD\" value =\""+pwd+"\" /> "
                        + "<input type=\"submit\" value=\"SHOP\"/>\n" +
                    "      </form><br>");
                
                out.print("<form action=\"Godown.jsp\" method=\"GET\">\n" +
                    "            <input type=\"submit\" value=\"GODOWN\"/>\n" +
                    "      </form><br>");
                 out.print("<form action=\"ChangePrice\" method=\"GET\">\n" +
                    "            <input type=\"submit\" value=\"CHANGE PRICE\"/>\n" +
                    "      </form><br>");
                 out.print("<form action=\"Login.html\" method=\"GET\">\n" +
                    "            <input type=\"submit\" value=\"LOGOUT\"/>\n" +
                    "      </form><br>");

               
            }
            else
            {
                out.println("<h1> Sorry! TRY AGAIN. </h1>");
                out.print("<form action=\"Login.html\" method=\"GET\">\n" +
                    "            <input type=\"submit\" value=\"BACK\"/>\n" +
                    "      </form>");
                //out.println("<a href='Login.html'>Go back to  login</a>");
            }
            
            out.println("thank you!");
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
