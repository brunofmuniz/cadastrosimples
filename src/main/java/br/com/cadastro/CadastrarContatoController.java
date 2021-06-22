package br.com.cadastro;

import br.com.cadastro.domain.Contato;
import br.com.cadastro.infra.ContatoRepository;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;


@WebServlet(name = "CadastrarContatoController", urlPatterns = {"/CadastrarContato"})
public class CadastrarContatoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //1 - obter os dados do formulário
        Contato contato = new Contato();
        contato.setNome(request.getParameter("nome"));
        contato.setEmail(request.getParameter("email"));
        contato.setFone(request.getParameter("fone"));

        //2 - validar os dados
        ArrayList<String> erros = new ArrayList<>();
        if (StringUtils.isBlank(contato.getNome())) {

            erros.add("O campo Nome é obrigatório");
        }
        if (StringUtils.isBlank(contato.getEmail())) {

            erros.add("O campo Email é obrigatório");
        }
        if (StringUtils.isBlank(contato.getFone())) {

            erros.add("O campo Telefone é obrigatório");
        } else if (!StringUtils.isNumeric(contato.getFone())) {

            erros.add("O campo Telefone é inválido");
        }
        if(erros.isEmpty()) {
            
            //------------------------------------------------------------------
            //3 - executar o processamento
            ContatoRepository cr = new ContatoRepository();
            cr.inserir(contato);
            //------------------------------------------------------------------
            
            //4 - colocar os objetos Java na requisição
            request.setAttribute("sucesso", "Contato salvo com sucesso");
            request.setAttribute("contato", null);
        } else {
                        
            request.setAttribute("erros", erros);
            request.setAttribute("contato", contato);
        }

        //5 - redirecionar
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
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
