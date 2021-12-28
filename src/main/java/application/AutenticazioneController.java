package application;

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
        String path=(request.getPathInfo() != null) ? request.getPathInfo(): "/";
        switch (path){
            case "/signin_signup": //pagina di registrazione e login
                request.getRequestDispatcher("/WEB-INF/interface/signin_signup.jsp").forward(request, response);
                break;
            case "/create":
                Utente nuovo=new Utente();
                //prendi dati da form
                us.registrazione(nuovo);
                request.getRequestDispatcher("/WEB-INF/interface/registered.jsp").forward(request, response);
                break;
            case "/signin":
                //prendi email e pass da form
                String email="",pass="";
                Utente log=us.login(email,pass);
                if (log!= null)
                    request.getRequestDispatcher("/WEB-INF/interface/account.jsp").forward(request,response);
                else
                    request.getRequestDispatcher("/WEB-INF/interface/signin.jsp").forward(request,response);

                break;


            }

            //response.sendRedirect(address+"/ac/carrello"); per andare in un altra servlet

        }
}
