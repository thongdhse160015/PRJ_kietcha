/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kietchase.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kietchase.registration.RegistrationDAO;
import kietchase.registration.RegistrationDTO;
import kietchase.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "StarupServlet", urlPatterns = {"/StarupServlet"})
public class StarupServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
         ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = MyApplicationConstants.StartupFeatures.LOGIN_PAGE;
        try {
            HttpSession session = request.getSession(false);
            String username = "", password = "";
            if (session == null || session.getAttribute("ACCOUNT") == null) {
                Cookie[] cookies = request.getCookies();

                if (cookies != null) {
                    Cookie lastCookie = cookies[cookies.length - 1];
                    //read its information
                    username = lastCookie.getName();
                    password = lastCookie.getValue();
                    //call DAO
                    RegistrationDAO dao = new RegistrationDAO();
                    RegistrationDTO result = dao.checkLogin(username, password);
                    // proccess result
                    if (result != null) {
                        url = MyApplicationConstants.StartupFeatures.SEARCH_PAGE;
                        session = request.getSession();
                        session.setAttribute("ACCOUNT", result);
                    }
                } // end cookies has existed
            }
            else if (session.getAttribute("ACCOUNT") != null) {
                url = MyApplicationConstants.StartupFeatures.SEARCH_PAGE;
            }
        } catch(ClassNotFoundException ex) {
            log("startupServlet _ClassNotFound" + ex.getMessage());
        } catch(NamingException ex) {
            log("startupServlet _Naming" + ex.getMessage());
        } catch(SQLException ex) {
            log("startupServlet _SQL" + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
