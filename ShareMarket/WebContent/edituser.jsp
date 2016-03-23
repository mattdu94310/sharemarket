<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.dauphine.sharemarket.model.Utilisateur"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifier Utilisateur</title>
</head>
<body>
<h1>Modifier Utilisateur</h1>
<%  
Utilisateur utilisateur = (Utilisateur)request.getAttribute("utilisateur");
%>
<form action="AdminFunction" method ="post">
	<label>Login</label> <input type="text" name="login" value="<%out.println(utilisateur.getLogin());%>"/> <br />
	<label>Nom</label> <input type="text" name="nom" value="<%out.println(utilisateur.getNom());%>" /> <br /> 
	<label>Prenom</label> <input type="text" name="prenom" value="<%out.println(utilisateur.getPrenom());%>" /> <br /> 
	<label>Mot de passe</label> <input type="password" name="password" value="<%out.println(utilisateur.getPassword());%>" /> <br /> 
	<label>Rôles</label><br />
	<INPUT type="checkbox" name="admin" value="admin" <%if(utilisateur.getAdministrateur()==1)out.println("checked");%>> Administrateur
	<INPUT type="checkbox" name="membresociety" value="membresociety" <%if(utilisateur.getMembreSociete()==1)out.println("checked");%>> Membre société
	<INPUT type="checkbox" name="investor" value="investor" <%if(utilisateur.getInvestisseur()==1)out.println("checked");%>> Investisseur <br />
	<INPUT type="checkbox" name="valide" value="valide" <%if(utilisateur.getValide()==1)out.println("checked");%>> Utilisateur validé <br />
	<input type="submit" value="Modifier Utilisateur" name="Demande" />
</form>
</body>
</html>