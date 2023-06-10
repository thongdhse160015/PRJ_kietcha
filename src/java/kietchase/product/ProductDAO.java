/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kietchase.product;
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
public class ProductDAO {
    private List<ProductDTO> itemsList;

public List<ProductDTO> getItemsList() {
    return itemsList;
}
public void ShowProduct() throws SQLException, NamingException {
    Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select sku, name, price, quantity, Status "
                        +"From Product "
                        +"Where Status = 1 ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()) {
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    Float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Boolean Status = rs.getBoolean("Status");
                    
                    ProductDTO dto = new ProductDTO(sku, name, price, quantity, Status);
                    if(this.itemsList== null) {
                        this.itemsList = new ArrayList<>();
                    }
                    this.itemsList.add(dto);
                }
            }
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
}
