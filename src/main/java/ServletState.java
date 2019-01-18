/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.*;

/**
 *
 * @author erwalind
 */

public class ServletState extends HttpServlet {

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
        String state = request.getParameter("state");

        DAO dao = new DAO(DataSourceFactory.getDataSource());

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletState</title>");

          

            
            out.println("</head>");
            out.println("<body>");

            try {
                List<CustomerEntity> l = dao.customersInState(state);
                
                out.println("<table style=\"width:100%\"border = 2>\n"
                            + "  <tr>\n"
                            + "    <th>id</th>\n"
                            + "    <th>name</th> \n"
                            + "    <th>adress</th>\n"
                            + "  </tr>\n");
                for (CustomerEntity c : l) {
                    out.println(
                            "  <tr>\n"
                            + "    <td>" + c.getCustomerId() + "\n"
                            + "    <td>" + c.getName() + "\n"
                            + "    <td>" + c.getAddressLine1() + "\n"
                            + "  </tr>\n"
                            );
                   
                }
                out.println("</table>");
            } catch (DAOException ex) {
                out.println("Erreur : " + ex.getMessage());
                Logger.getLogger(ServletState.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.println("<h1>Servlet ServletState at " + request.getContextPath() + "</h1>");
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
