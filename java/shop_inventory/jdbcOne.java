package shop_inventory;
/**
 *
 * @author Tejas Sathawane
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcOne {
    public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
//        Step 1: Register the driver class
           Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("DB Driver registered");

//        STEP 2: Establish Connection
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Shop_Inventory", "root", "root");
            System.out.println("DB Connection established");

//        Step 3: Create Statement
           stmt = conn.createStatement();
            
             String query = "SELECT * FROM ROOT.SHOP_DATA";
             rs = stmt.executeQuery(query);
              while(rs.next())
              {
               Integer LAST_NAME = rs.getInt(1);
               String FIRST_NAME = rs.getString(2);
               Integer EMAIL = rs.getInt(3);
               Double PHONE_NUMBER = rs.getDouble(4);
               System.out.println("First name: " +FIRST_NAME+ "\n Last name:" +LAST_NAME+ "\n Email: "+EMAIL+ "\n Phone number: "+PHONE_NUMBER);  
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
    }

    private static String getColumnName(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
