/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.trabalhobiblioteca.controller.livro;

import br.com.trabalhobiblioteca.dao.LivroDAO;
import br.com.trabalhobiblioteca.model.Livro;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pr472
 */
@WebServlet(name = "LivroSalvar", urlPatterns = {"/LivroSalvar"})
public class LivroSalvar extends HttpServlet {

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
          try {

            String id = request.getParameter("id");

            Livro oLivro = new Livro();

            oLivro.setNomeLivro(request.getParameter("nomeLivro"));
            oLivro.setIsbn(request.getParameter("isbn"));
            oLivro.setAutor(request.getParameter("autor"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            oLivro.setDataPublicacao(
                    sdf.parse(request.getParameter("dataPublicacao"))
            );

            String valor = request.getParameter("valorLivro")
                                  .replace(",", ".");

            oLivro.setValorLivro(Double.parseDouble(valor));

            LivroDAO dao = new LivroDAO();

            // ALTERAR
            if (id != null && !id.equals("") && !id.equals("0")) {

                oLivro.setId(Integer.parseInt(id));

                dao.alterar(oLivro);

            } else {

                // NOVO
                dao.inserir(oLivro);
            }

            response.sendRedirect(
                    request.getContextPath() + "/LivroListar"
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Erro ao salvar: " + e.getMessage()
            );
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
