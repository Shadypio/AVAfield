package application.GestioneStrutture;

import model.struttura.Struttura;
import model.struttura.StrutturaServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "StrutturaController", value = "/gs/*")
public class StrutturaController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String address=getServletContext().getContextPath();
        StrutturaServiceImpl ss=new StrutturaServiceImpl();
        Struttura s=new Struttura(); // oggetto di appoggio;
        String path=(request.getPathInfo() != null) ? request.getPathInfo(): "/";
        switch (path) {
            case "/viewStructure":
                session.setAttribute("listaStrutture",ss.visualizzaStruttura());
                request.getRequestDispatcher("/WEB-INF/interface/area_riservata/structures.jsp").forward(request, response);
                break;
            case "/deleteStruttura":
                String idDelete=request.getParameter("selezioneDelete");
                s.setIdStruttura(Integer.parseInt(idDelete));
                ss.eliminaStruttura(s);
                response.sendRedirect(address+"/gs/viewStructure");
                break;
            case "/updateStruttura":
                String idUpdate=request.getParameter("selezioneMod");
                s.setIdStruttura(Integer.parseInt(idUpdate));
                s.setNome(request.getParameter("nome"));
                s.setCategoria(request.getParameter("cat"));
                s.setDescrizione(request.getParameter("desc"));
                s.setIndirizzo(request.getParameter("indirizzo"));
                s.setTelefono(request.getParameter("tel"));
                s.setCapienza(Integer.parseInt(request.getParameter("capienza")));
                s.setNumeroSpogliatoi(Integer.parseInt(request.getParameter("numSpo")));
                String park=request.getParameter("park");
                if (park.equals("on"))
                    s.setParcheggio(true);
                else
                    s.setParcheggio(false);
                ss.modificaStruttura(s);
                response.sendRedirect(address+"/gs/viewStructure");
                break;
            case "/addStruttura":
                s.setIdStruttura(ss.visualizzaStruttura().size()+1);
                s.setNome(request.getParameter("nome"));
                s.setCategoria(request.getParameter("cat"));
                s.setDescrizione(request.getParameter("desc"));
                s.setIndirizzo(request.getParameter("indirizzo"));
                s.setTelefono(request.getParameter("tel"));
                s.setCapienza(Integer.parseInt(request.getParameter("capienza")));
                s.setNumeroSpogliatoi(Integer.parseInt(request.getParameter("numSpo")));
                park=request.getParameter("park");
                if (park.equals("on"))
                    s.setParcheggio(true);
                else
                    s.setParcheggio(false);
                ss.inserisciStruttura(s);
                response.sendRedirect(address+"/gs/viewStructure");
                break;
        }

    }
}
