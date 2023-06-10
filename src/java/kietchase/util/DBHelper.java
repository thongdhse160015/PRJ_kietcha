/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kietchase.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author ADMIN
 */
public class DBHelper implements Serializable {
    public static Connection makeConnection() 
            throws SQLException, NamingException{ /*ClassNotFoundException, SQLException*/ 
                    //1. get current Context
            Context context = new InitialContext();
            //2. get tomcat context
            Context tomcat = (Context)context.lookup("java:comp/env");
            //3. find data Source
            DataSource ds = (DataSource)tomcat.lookup("DataSource");
            //4. get Connection, open
            Connection con = ds.getConnection();
            
            return con;
//        //1. Load Drive
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Tao URL Connection String --> DB address
//        String url ="jdbc:sqlserver://localhost:1433;"
//                +"databaseName=PRJ;instanceName=SQLEXPRESS01";
//        //3. Open Connection
//        Connection con = DriverManager.getConnection(url, "sa","123456");
//        return con;
//        
    }
    public static void getSiteMaps(ServletContext context) throws IOException{
        String siteMapFile = context.getInitParameter("SITEMAPS_PATH");
        
        InputStream is = null;
        Properties properties = new Properties();
        try{
            is = context.getResourceAsStream(siteMapFile);
            properties.load(is);
            context.setAttribute("SITEMAPS", properties);
            
        } 
        finally{
            if (is != null){
                is.close();
            }
        }
    }

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
