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
	<input type="hidden" name="login" value="<%out.print(utilisateur.getLogin());%>"/>
	<label>Login</label> <input type="text" name="loginDisplay" value="<%out.print(utilisateur.getLogin());%>" disabled/> <br />
	<label>Nom</label> <input type="text" name="nom" value="<%out.print(utilisateur.getNom());%>" /> <br /> 
	<label>Prenom</label> <input type="text" name="prenom" value="<%out.print(utilisateur.getPrenom());%>" /> <br /> 
	<label>Mot de passe</label> <input type="password" name="password" value="<%out.print(utilisateur.getPassword());%>" /> <br /> 
	<INPUT type="checkbox" name="valide" value="valide" <%if(utilisateur.getValide()==1)out.print("checked");%>> Utilisateur validé <br />
	<input type="submit" value="Modifier Utilisateur" name="Demande" />
</form>
</body>
</html>