package application.GestioneRecensioni;

import model.recensione.Recensione;
import model.recensione.RecensioneServiceImpl;
import model.struttura.Struttura;
import model.struttura.StrutturaServiceImpl;
import model.utente.Utente;
import model.utente.UtenteServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RecensioneController", value = "/gr/*")
public class RecensioneController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String address=getServletContext().getContextPath();
        RecensioneServiceImpl rs=new RecensioneServiceImpl();
        StrutturaServiceImpl ss=new StrutturaServiceImpl();
        UtenteServiceImpl us=new UtenteServiceImpl();
        String path=(request.getPathInfo() != null) ? request.getPathInfo(): "/";
        switch (path) {
            case "/addRecensione":
                Utente profilo=(Utente) session.getAttribute("profilo");
                if(profilo==null)
                    response.sendRedirect(address+"/ac/signin");
                else {
                    int idStr = Integer.parseInt(request.getParameter("idStruttura"));
                    Recensione nuova = new Recensione();
                    nuova.setUtente(profilo);
                    nuova.setStruttura(ss.trovaStruttura(idStr));
                    String titolo = request.getParameter("titolo");
                    String testo = request.getParameter("testo");
                    int stelle = Integer.parseInt(request.getParameter("stelle"));
                    nuova.setTesto(testo);
                    nuova.setTitolo(titolo);
                    nuova.setNumeroStelle(stelle);
                    nuova.setIdRecensione(rs.visualizzaRecensioni().size()+1);
                    rs.inserisciRecensione(nuova);
                    Struttura s=ss.trovaStruttura(idStr);
                    ArrayList<Recensione> listaRecensioni=rs.visualizzaRecensioniByIdStruttura(s);
                    for (Recensione r:listaRecensioni){
                        Utente x=r.getUtente();
                        r.setUtente(us.trovaUtente(x.getIdUtente()));
                    }
                    session.setAttribute("struttura", s);
                    session.setAttribute("listaRecensioni",listaRecensioni);
                    request.getRequestDispatcher("/WEB-INF/interface/site/single_structure.jsp").forward(request, response);
                }
                break;
        }

    }
}
