package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.services.UtilisateurService;
import jpa.entities.Utilisateur;

/**
 * Servlet implementation class AdminFunction
 */
@WebServlet("/AdminFunction")
public class AdminFunction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private UtilisateurService utilisateurService;
	
    public AdminFunction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder userSearch = new StringBuilder();
		if(request.getParameter("Demande") != null){
			switch(request.getParameter("Demande")){
			case "searchUser" :
				byte admin=0, investor=0, membersociety=0,valide=0;
				if(request.getParameter("admin")!=null){
					admin=1;
				}
				if(request.getParameter("investor")!=null){
					investor=1;
				}
				if(request.getParameter("membersociety")!=null){
					membersociety=1;
				}
				List<Utilisateur> utilisateurs = utilisateurService.find(request.getParameter("login"), request.getParameter("nom"), request.getParameter("prenom"),admin, membersociety, investor, valide);
				request.setAttribute("utilisateurs", utilisateurs); 
				getServletConfig().getServletContext().getRequestDispatcher("/UserList").forward(request,response);
				break;
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
