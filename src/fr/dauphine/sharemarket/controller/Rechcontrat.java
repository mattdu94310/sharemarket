package fr.dauphine.sharemarket.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.sharemarket.*;	
import fr.dauphine.sharemarket.model.Contrat;
import fr.dauphine.sharemarket.model.Societe;

/**
 * Servlet implementation class Rechcontrat
 */
@WebServlet("/Rechcontrat")
public class Rechcontrat extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	
	public static final String Champ_Type ="type_contrat";
	public static final String Champ_Societe="id_societe";
	public static final String Champ_PF="prix_fixe";
	public static final String Champ_PD="prix_depart";
	public static final String Champ_Date="date_emission";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rechcontrat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/rechcontrat.jsp").forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String resultat;

	        /* R�cup�ration des champs du formulaire. */
	        String type_contrat= request.getParameter( Champ_Type );
	        Type
	        String id_societe = request.getParameter( Champ_Societe );
	        Societe s = new Societe();
	        s.setIdSociete(Integer.parseInt(id_societe));
	        String prix_fixe = request.getParameter( Champ_PF );
	        String prix_depart = request.getParameter( Champ_PD ); 
	        String date_emission = request.getParameter( Champ_Date );

	        Contrat p = new Contrat();
	        p.setTypeContrat(typeContrat);
	        p.setSociete(s);
	        p.setPrixFixe(prix_fixe);
	        p.setPrixDepart(prix_depart);
	        p.setDateEmission(date_emission);
	       

	        /* Transmission de la paire d'objets request/response � notre JSP */
	        
	        response.sendRedirect(request.getContextPath()+"/connexion");
	        //this.getServletContext().getRequestDispatcher( "Inscription" ).forward( request, response );
	    
		
		doGet(request, response);
	}

}
