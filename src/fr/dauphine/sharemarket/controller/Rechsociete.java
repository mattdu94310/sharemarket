package fr.dauphine.sharemarket.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.sharemarket.model.Secteur_activite;
import fr.dauphine.sharemarket.model.Societe;

/**
 * Servlet implementation class Rechsociete
 */
@WebServlet("/Rechsociete")
public class Rechsociete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String Champ_Id="id_societe";
	public static final String Champ_Nom="nom";
	public static final String Champ_Membre_Societe_Login="membre_societe_login";
	public static final String Champ_Secteur_Activite="secteur_activite";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rechsociete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/rechsociete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String resultat;

        /* Récupération des champs du formulaire. */
        String id_societe= request.getParameter( Champ_Id );
        String nom = request.getParameter( Champ_Nom );
        String membre_societe = request.getParameter( Champ_Membre_Societe_Login );
        List<Secteur_activite> secteur_activite = null;
        Secteur_activite s = new Secteur_activite();
        s.setSecteur(request.getParameter( Champ_Secteur_Activite ));
        secteur_activite.add(s);
        
        

        Societe p = new Societe();
        p.setIdSociete(Integer.parseInt(id_societe));
        p.setNom(nom);
        p.setMembreSocieteLogin(membre_societe);
        p.setSecteurActivites(secteur_activite);
	}

}
