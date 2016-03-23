<%@page import="fr.dauphine.sharemarket.model.Utilisateur"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste d'utilisateurs</title>
</head>
<body>
<h1>Liste des utlisateurs</h1>
<%  
List<Utilisateur> list = (List<Utilisateur>)request.getAttribute("utilisateurs");
%>
<table>
<tr><th>Login</th><th>Nom</th><th>Prenom</th><th>Adminstrateur</th><th>Investisseur</th><th>Membre société</th><th>Est Validé</th><th>Actions</th></tr>
<% 
for(Utilisateur utilisateur : list) {
	out.println("<tr>");
    out.println("<td>"+utilisateur.getLogin()+"</td>");
    out.println("<td>"+utilisateur.getNom()+"</td>");
    out.println("<td>"+utilisateur.getPrenom()+"</td>");
    out.println("<td>"+utilisateur.getAdministrateur()+"</td>");
    out.println("<td>"+utilisateur.getInvestisseur()+"</td>");
    out.println("<td>"+utilisateur.getMembreSociete()+"</td>");
    out.println("<td>"+utilisateur.getValide()+"</td>");
    out.println("<td><a href=\""+request.getContextPath()+"/AdminFunction?Demande=SupprimerUtilisateur&id="+utilisateur.getLogin()+"\">Supprimer</a><a href=\""+request.getContextPath()+"/AdminFunction?Demande=FormulaireUtilisateur&id="+utilisateur.getLogin()+"\">Modifier</a></td>");
    out.println("</tr>");
}
%>
</table>
</body>
</html>