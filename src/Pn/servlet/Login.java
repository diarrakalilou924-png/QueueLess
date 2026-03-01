package Pn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Pn.doa.UtilisateurDoa;
import Pn.metier.Utilisateur;
import Pn.service.UserService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
	
		
		
		UserService service=new UserService();
		Utilisateur util=service.authenticate(email, password);
		if(util!=null) {
			   HttpSession session=request.getSession();
			   session.setAttribute("util",util);
			   response.sendRedirect(request.getContextPath() + "/protected/accueil.jsp");
		}else {
            // Échec : redirection vers login avec message d'erreur
            response.sendRedirect("Loginfrm.jsp?error=Email ou mot de passe incorrect");
        }
		
	}

}
