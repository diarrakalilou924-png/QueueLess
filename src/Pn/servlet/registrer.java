package Pn.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pn.metier.Utilisateur;
import Pn.service.UserService;

/**
 * Servlet implementation class registrer
 */
@WebServlet("/registrer")
public class registrer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String Vue="/WEB-INF/Inscriptionfrm.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registrer() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Rediriger vers le formulaire d'inscription
        request.getServletContext().getRequestDispatcher(Vue).forward(request, response);
    }

		/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom=request.getParameter("nom");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String Motdepass=request.getParameter("Motdepass");
		String role=request.getParameter("role");
		String statut=request.getParameter("statut");
	    String Organisationid=request.getParameter("organisationid");
		
		
		Utilisateur user= new Utilisateur();
		 UUID organisationid = UUID.fromString(Organisationid);
		 
		    
		user.setNom(nom);
		user.setEmail(email);
		user.setTelephone(phone);
		user.setMotDePasse(Motdepass);
		user.setRole(role);
		user.setStatut(statut);
		user.setOrganisationId(organisationid);
		 UserService userservice = new  UserService();
		 boolean succes=userservice.register(user);
		 if(succes) {
			response.sendRedirect("Login.jsp?succes=Inscription réussie ! Vous pouvez vous connecter.");
		 }else {
			 response.sendRedirect("Inscription.jsp?error=Cet email est déjà utilisé");
		 }
		
		
	}

}
