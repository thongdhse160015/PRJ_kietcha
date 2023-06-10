/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kietchase.registration;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import kietchase.util.DBHelper;
/**
 *
 * @author ADMIN
 */
public class RegistrationDAO implements Serializable {
     public RegistrationDAO() {
    }
     public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;
        try {
            //1. Connect DB
            con =DBHelper.makeConnection(); 
            if(con != null) {
            //2. Write SQl command
                String sql = "Select lastname, isAdmin " 
                           + "From Registration "
                           + "Where username = ? And password = ?";
                    //3. Create Statement Object
                    stm = con.prepareStatement(sql);
                    stm.setString(1, username);
                    stm.setString(2, password);
                    //4. Execute statement object to get result 
                    rs = stm.executeQuery();
                    //5. Process result
                    if(rs.next()) {     
                        String fullname = rs.getString("lastname");
                        boolean role = rs.getBoolean("isAdmin");
                        result = new RegistrationDTO(username, null, fullname, role);
                    }
            }//end connection has existed
        } finally {
            if(rs != null) {
                rs.close();
            }
            if(stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    private List<RegistrationDTO> accountList;
   
   public List<RegistrationDTO> getAccountList(){
       return accountList;
   }
   public void searchLastName(String keyword)
        throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
       try {
            //1. connect DB
            con = DBHelper.makeConnection(); //tu model xuong DB -->40
            if (con != null) {
            //2. write SQL Statement
                String sql = "Select username, password, lastname, isAdmin "
                        + "from Registration "
                        + "where username Like ? ";
            //3. create statenment obj
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            //4. excute statement obj to get result
            rs = stm.executeQuery();            
            //5. process result 
          while(rs.next()) {
              String username = rs.getString("username");
              String password = rs.getString("password");
              String fullName = rs.getString("lastname");
              boolean role = rs.getBoolean("isAdmin"); 
              RegistrationDTO dto = new RegistrationDTO(
                      username, password, fullName, role);
              if(this.accountList == null) {
                  this.accountList = new ArrayList<>();
              }
              this.accountList.add(dto);
          }
            }//end connection
        } finally {
            if(rs !=null){
                rs.close();
            }
            if(stm !=null){
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
   }
   public boolean deleteAccount(String pk) throws SQLException, NamingException {
       Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnection(); //tu model xuong DB -->40
            if (con != null) {
                String sql = "Delete From Registration "
                        +"Where username = ?";
                stm =  con.prepareStatement(sql);
                stm.setString(1,pk);
                int effectRows = stm.executeUpdate();
                if(effectRows > 0) {
                    result = true;
                }
            }
            
        }
        finally{
            if(stm !=null){
                stm.close();
            }
            if (con != null) {
                con.close();
        }
   }
        return result;
}
    public boolean UpdateAccount(String username, String password, boolean isAdmin) throws SQLException, NamingException {
       Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Registration "
                        +"Set password = ?, isAdmin = ? "
                        +"Where username = ?";
                stm =  con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                int effectRows = stm.executeUpdate();
                if(effectRows > 0) {
                    result = true;
                }
            }       
        }
        finally{
            if(stm !=null){
                stm.close();
            }
            if (con != null) {
                con.close();
        }
   }
        return result;
    }

    public boolean createAccount(RegistrationDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
