<%@page import="fr.dauphine.sharemarket.model.Utilisateur"%>
<%@page import="java.util.List"%>
<%@page import="fr.dauphine.sharemarket.model.Secteur_activite"%>
<%@page import="fr.dauphine.sharemarket.error.MessagesDErreurs"%>
<%@page import="fr.dauphine.sharemarket.model.Societe"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
	if(session.getAttribute("connected_user")!=null){
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("connected_user");
		if(utilisateur.getMembreSociete()!=1){
			getServletContext().getRequestDispatcher("/unauthorized.html").forward(request, response);
		}
	}else{
		getServletContext().getRequestDispatcher("/unauthorized.html").forward(request, response);
	}

	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifier ma société</title>
</head>
<body>
	<h1>Modifier ma société</h1>
	<% Societe societe = (Societe) request.getAttribute("societe");
	   if(societe==null)out.println("<p style=\"color:red;\">"+MessagesDErreurs.getMessageDerreur("6")+"</p>");
	   else{
	%>
	<form action="MemberSocietyFunction" method="post">
		<input type="hidden" name="id" value="<%out.print(societe.getIdSociete()); %>" />
		<label>Nom</label><input type="text" name="nom" value="<%out.print(societe.getNom()); %>" /><br />
		<label>Resumé</label><textarea name="resume" ><%out.print(societe.getResume()); %></textarea><br />
		<label>Secteurs d'activité</label> 
		<table>
		<tr><th>Secteur d'activité</th><th>Action</th><tr>
		<%
		if(societe.getSecteurActivites()!=null){
			for(Secteur_activite secteur_activite : societe.getSecteurActivites()) {
				out.print("<tr><td>"+secteur_activite.getSecteur()+"</td><td><a href=\""+request.getContextPath()+"/MemberSocietyFunction?Demande=SupprimerSecteur&societe="+societe.getIdSociete()+"&secteur="+secteur_activite.getIdSecteur()+"\">Supprimer</a>");
			}
		}
		List<Secteur_activite> secteurs = (List<Secteur_activite>) request.getAttribute("secteurs");
		if(secteurs!=null)
		for(Secteur_activite secteur_activite : secteurs){
			if(!societe.getSecteurActivites().contains(secteur_activite))
			out.print("<tr><td>"+secteur_activite.getSecteur()+"</td><td><a href=\""+request.getContextPath()+"/MemberSocietyFunction?Demande=AjouterSecteur&societe="+societe.getIdSociete()+"&secteur="+secteur_activite.getIdSecteur()+"\">Ajouter</a>");
		}
		%>
		</table>
		<input type="submit" name="Demande" value="Modifier Societe" />
	</form>
	<%} %>
</body>
</html>