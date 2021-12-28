package application.Autenticazione;

import model.utente.Utente;
import model.utente.UtenteServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AutenticazioneController", value = "/ac/*")
public class AutenticazioneController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String address=getServletContext().getContextPath();
        UtenteServiceImpl us=new UtenteServiceImpl();
        String email,pass;
        Utente log; //Utente che prova a loggarsi
        String path=(request.getPathInfo() != null) ? request.getPathInfo(): "/";
        switch (path){
            case "/secret":// pagina di login admin
                request.getRequestDispatcher("/WEB-INF/interface/area_riservata/secret.jsp").forward(request,response);
                break;
            case "/dashboard": // admin tentativo di login to dashboard
                email = request.getParameter("email");
                pass = request.getParameter("password");
                log=us.login(email,pass);
                if (log.isAdmin())
                    request.getRequestDispatcher("/WEB-INF/interface/area_riservata/dashboard.jsp").forward(request, response);
                else
                    request.getRequestDispatcher("/WEB-INF/interface/area_riservata/secret.jsp").forward(request,response);
                break;
            case "/signin_signup": //pagina di registrazione e login
                request.getRequestDispatcher("/WEB-INF/interface/site/signin_signup.jsp").forward(request, response);
                break;
            case "/create": //registrazione nuovo utente
                String nome = request.getParameter("nome");
                String cognome = request.getParameter("cognome");
                String user = request.getParameter("username");
                email = request.getParameter("email");
                String tel = request.getParameter("telefono");
                pass = request.getParameter("password");
                int id=us.visualizzaUtenti().size()+1;
                Utente nuovo=new Utente(nome,cognome,email,user,pass,tel,false,2,id);
                us.registrazione(nuovo);
                request.getRequestDispatcher("/WEB-INF/interface/site/registered.jsp").forward(request, response);
                break;
            case "/signin":
                email = request.getParameter("email");
                pass = request.getParameter("password");
                log=us.login(email,pass);
                if (log!= null)
                    request.getRequestDispatcher("/WEB-INF/interface/site/account.jsp").forward(request,response);
                else
                    request.getRequestDispatcher("/WEB-INF/interface/site/signin_signup.jsp").forward(request,response);
                break;
            }
            //response.sendRedirect(address+"/ac/carrello"); per andare in un altra servlet
        }
}
