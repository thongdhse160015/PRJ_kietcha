/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kietchase.Order;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.NamingException;
import kietchase.util.DBHelper;
/**
 *
 * @author ADMIN
 */
public class OrderDAO implements Serializable{
    public int createOrder() throws SQLException, NamingException{
        Connection connection = null;
        PreparedStatement statement = null;
        Statement idstatement = null;
        ResultSet rs = null;
        int id = 0;
        try{
            // 1. Make connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create sql String
                String sql = "Insert into [Order](orderdate) "
                        + "values(?)";
                //3. Create Statement Object
                Date date=new Date(System.currentTimeMillis());
                statement = connection.prepareStatement(sql);
                statement.setDate(1, date);
                //4. Excute Query
                int countEffects = statement.executeUpdate();
                //5. Process result
                if (countEffects >0){
                    idstatement = connection.createStatement();
                    statement = connection.prepareStatement("select max(id) as id " +
                                                                "from [Order]");
                    rs = statement.executeQuery();
                    if (rs.next()){
                        id = rs.getInt("id");
                    }    
                }
            } //connection khac null thi moi lam
        } finally{
            if (idstatement != null){
                idstatement.close();
            }
            if (statement!= null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return id;
    }
    public boolean addTotal(int id, int total)throws SQLException, NamingException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean result = false;
        try{
            // 1. Make connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create sql String
                String sql = "Update [Order] "
                        + "Set total = ? "
                        + "Where id = ?";
                //3. Create Statement Object
                statement = connection.prepareStatement(sql);
                statement.setInt(1, total);
                statement.setInt(2,id);
                //4. Excute Query
                int countEffects = statement.executeUpdate();
                //5. Process result
                if (countEffects >0){
                    result = true;
                }
            } //connection khac null thi moi lam
        } finally{
            if (statement!= null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return result;
    }
}
