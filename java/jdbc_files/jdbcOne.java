package jdbc_files;
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
        Connection conn1 = null;
        Statement stmt = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        
        try {
//        Step 1: Register the driver class
           Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("DB Driver registered");

//        STEP 2: Establish Connection
            conn1 = DriverManager.getConnection("jdbc:derby://localhost:1527/Shop_Inventory", "root", "root");
            System.out.println("DB Connection established");

//        Step 3: Create Statement
             stmt = conn.createStatement();
             String query = "SELECT * FROM ROOT.SHOP_DATA";
             rs = stmt.executeQuery(query);
             while (rs.next()) {
                Integer PRODUCT_ID = rs.getInt(1);
                String Name = rs.getString(2);
               // float cgpa = rs.getFloat(3);
               // String dept = rs.getString(4);

                //System.out.println("Roll : " + roll + " Name : " + name + "\tCGPA : " + cgpa + "\tDept: " + dept);
                System.out.println("Product : " + PRODUCT_ID + "\nProduct name : " + Name );
               
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
               if (stmt1 != null) {
                    stmt1.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (conn1 != null) {
                    conn1.close();
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