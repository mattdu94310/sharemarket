package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.services.UtilisateurService;
import jpa.entities.Utilisateur;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String Champ_Nom ="nom";
	public static final String Champ_Prenom="prenom";
	public static final String Champ_Login="login";
	public static final String Champ_Password="password";
	
	@EJB
	private UtilisateurService utilisateurService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/inscription.jsp").forward(request, response);
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	      String resultat;

	        /* Récupération des champs du formulaire. */
	        String nom = request.getParameter( Champ_Nom );
	        String prenom = request.getParameter( Champ_Prenom );
	        String login = request.getParameter( Champ_Login );
	        String password = request.getParameter( Champ_Password );

	        Utilisateur p = new Utilisateur();
	        p.setLogin(login);
	        p.setNom(nom);
	        p.setPrenom(prenom);
	        p.setPassword(password);
	        p.setInvestisseur((byte)1);
	        utilisateurService.inscriptionInvestisseur(p);

	        /* Transmission de la paire d'objets request/response à notre JSP */
	        
	        response.sendRedirect(request.getContextPath()+"/connexion");
	        //this.getServletContext().getRequestDispatcher( "Inscription" ).forward( request, response );
	    }
		
		
	}


