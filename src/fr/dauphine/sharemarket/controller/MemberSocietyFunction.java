package fr.dauphine.sharemarket.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.dauphine.sharemarket.dao.SocieteDAOInterface;
import fr.dauphine.sharemarket.error.MessagesDErreurs;
import fr.dauphine.sharemarket.error.ShareMarketException;
import fr.dauphine.sharemarket.model.Societe;
import fr.dauphine.sharemarket.model.Utilisateur;

@WebServlet("/MemberSocietyFunction")
public class MemberSocietyFunction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	SocieteDAOInterface societeDAO;
	
	
    public MemberSocietyFunction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Byte isMemberSociete=0;
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("connected_user");
		if(utilisateur!=null){
			isMemberSociete=utilisateur.getMembreSociete();
		}else {
			getServletConfig().getServletContext().getRequestDispatcher("/unauthorized.html").forward(request,response);
			return;
		}
		if(isMemberSociete==0){
			getServletConfig().getServletContext().getRequestDispatcher("/unauthorized.html").forward(request,response);
			return;
		}
		if(request.getParameter("Demande")==null){
			getServletConfig().getServletContext().getRequestDispatcher("/notfound.html").forward(request,response);
			return;
		}
		
		switch(request.getParameter("Demande")){
		case "EditSociety" : 
			editerSociete(request,response);
			break;
			
		case "Modifier Societe" : 
			modifierSociete(request,response);
			break;
		}
	}

	private void modifierSociete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("nom").equals("") || request.getParameter("resume").equals("")){
			request.setAttribute("message_error", MessagesDErreurs.getMessageDerreur("3"));
			editerSociete(request, response);
			return;
		}
		boolean error=false;
		try {
			societeDAO.updateSociete(request.getParameter("id") ,request.getParameter("nom"), request.getParameter("resume"));
		} catch (ShareMarketException e) {
			error=true;
			request.setAttribute("message_error",e.getMessage());
		}
		if(!error){
			request.setAttribute("message_info",MessagesDErreurs.getMessageDerreur("8"));
		}
		getServletConfig().getServletContext().getRequestDispatcher("/MembreSociete").forward(request,response);
		return;
	}

	private void editerSociete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("connected_user");
		Societe societe = null;
		try {
			societe = societeDAO.findByMemberSociete(utilisateur.getLogin());
		} catch (ShareMarketException e) {
			request.setAttribute("message_error", e.getMessage());
			getServletConfig().getServletContext().getRequestDispatcher("/MembreSociete").forward(request,response);
			return;
		}
		request.setAttribute("societe", societe);
		getServletConfig().getServletContext().getRequestDispatcher("/EditSociety").forward(request,response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
