/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kietchase.cart;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author ADMIN
 */
public class cartObj implements Serializable {
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
 public boolean addItemToCart(String id, int quantity) {
     boolean result = false;
     //1. check data validation 
     if(id == null) {
         return result;
     }
     if(id.trim().isEmpty()) {
         return result;
     }
     if(quantity <= 0) {
         return result;
     }
     //2. check existed items
     if(this.items == null) {
         this.items = new HashMap<>();
     }
     //3. Check existed items
     if(this.items.containsKey(id)) {
         //4. 
         int currentQuantity = this.items.get(id);
         quantity = quantity +currentQuantity;
         
     }//end item has existed
     this.items.put(id, quantity);
     result = true;
     
     return result;
 }
 
 public boolean removeItemFromCart(String id, int quantity) {
     boolean result = false;
     //1. check data validation
     if(id == null) {
         return result;
     }
     if(id.trim().isEmpty()) {
         return result;
     }
     if(quantity <= 0) {
         return result;
     }
     //2. check existed 
     if(this.items == null) {
         return result;
     }
     //3. check existed item
     if(!this.items.containsKey(id)) {
         return result;
     }
     //4. decrease quantity
     int currentQuantity = this.items.get(id);
     if(currentQuantity >= quantity) {
        quantity = currentQuantity - quantity;
     }
     if(quantity == 0) {
         this.items.remove(id);
         if(this.items.isEmpty()) {
             this.items = null;
         }
     } else {
         this.items.put(id, quantity);
     }
     result = true;
     
     return result;
 }
}
