package application.GestioneStrutture;

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

@WebServlet(name = "StrutturaController", value = "/gs/*")
public class StrutturaController extends HttpServlet {
    private StrutturaServiceImpl ss;
    private RecensioneServiceImpl rs;
    private UtenteServiceImpl us;
    public StrutturaController(){
        us=new UtenteServiceImpl();
        ss=new StrutturaServiceImpl();
        rs=new RecensioneServiceImpl();
    }

    public StrutturaController(StrutturaServiceImpl ss){
        this.ss=ss;
        us=new UtenteServiceImpl();
        rs=new RecensioneServiceImpl();
    }

    public StrutturaController(StrutturaServiceImpl ss,UtenteServiceImpl us){
        this.ss=ss;
        this.us=us;
        rs=new RecensioneServiceImpl();
    }

    public StrutturaController(StrutturaServiceImpl ss,UtenteServiceImpl us,RecensioneServiceImpl rs){
        this.ss=ss;
        this.us=us;
        this.rs=rs;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String address=request.getServletContext().getContextPath();
        Struttura s=new Struttura(); // oggetto di appoggio;
        String path=(request.getPathInfo() != null) ? request.getPathInfo(): "/";
        switch (path) {
            case "/viewStructures":
                session.setAttribute("listaStrutture",ss.visualizzaStrutture());
                request.getRequestDispatcher("/WEB-INF/interface/area_riservata/structures.jsp").forward(request, response);
                break;
            case "/deleteStruttura":
                String idDelete=request.getParameter("selezioneDelete");
                s.setIdStruttura(Integer.parseInt(idDelete));
                ss.eliminaStruttura(s);
                response.sendRedirect(address+"/gs/viewStructures");
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
                response.sendRedirect(address+"/gs/viewStructures");
                break;
            case "/addStruttura":
                s.setIdStruttura(ss.visualizzaStrutture().size()+1);
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
                response.sendRedirect(address+"/gs/viewStructures");
                break;
            case "/singleStructure":
                int idStruttura=Integer.parseInt(request.getParameter("idStruttura"));
                s=ss.trovaStruttura(idStruttura);
                ArrayList<Recensione> listaRecensioni=rs.visualizzaRecensioniByIdStruttura(s);
                for (Recensione r:listaRecensioni){
                    Utente x=r.getUtente();
                    r.setUtente(us.trovaUtente(x.getIdUtente()));
                }
                session.setAttribute("struttura", s);
                session.setAttribute("listaRecensioni",listaRecensioni);
                request.getRequestDispatcher("/WEB-INF/interface/site/single_structure.jsp").forward(request, response);
                break;
            case "/viewStructuresUser":
                String categoria=request.getParameter("categoria");
                if (categoria==null) {
                    session.setAttribute("listaStrutture", ss.visualizzaStrutture());
                    request.getRequestDispatcher("/WEB-INF/interface/site/showStructures.jsp").forward(request, response);
                } else{
                    ArrayList<Struttura> listaStrutture=ss.visualizzaStrutture();
                    ArrayList<Struttura> result=new ArrayList<>();
                    for (Struttura x: listaStrutture){
                        if (x.getCategoria().equals(categoria))
                            result.add(x);
                    }
                    session.setAttribute("listaStrutture", result);
                    request.getRequestDispatcher("/WEB-INF/interface/site/showStructures.jsp").forward(request, response);
                }
                break;

        }
    }
}
