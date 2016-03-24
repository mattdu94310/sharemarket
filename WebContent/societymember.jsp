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
<% if(request.getAttribute("message_error")!=null){
	out.println("<p style=\"color:red;\">"+request.getAttribute("message_error")+"</p>");
}
if(request.getAttribute("message_info")!=null){
	out.println("<p style=\"\">"+request.getAttribute("message_info")+"</p>");
}
%>
<a href="MemberSocietyFunction?Demande=MesContrat">Mes Contrats</a>
<a href="MemberSocietyFunction?Demande=MyPublication">Mes Publications</a>
<a href="MemberSocietyFunction?Demande=EditSociety">Ma Société</a>
<a href="MemberSocietyFunction?Demande=EditContract">Nouveau Contrat</a>
<a href="MemberSocietyFunction?Demande=EditPublication">Nouvelle Publication</a>
</body>
</html>