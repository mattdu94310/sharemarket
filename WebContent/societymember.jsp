<%@page import="fr.dauphine.sharemarket.model.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>Society Member home page</title>
</head>
<body>
<h1>Society Member home page</h1>
<a href="MyContracts">Mes Contrats</a>
<a href="MyPublication">Mes Publications</a>
<a href="EditSociety">Ma Société</a>
<a href="EditContract">Nouveau Contrat</a>
<a href="EditPublication">Nouvelle Publication</a>
</body>
</html>