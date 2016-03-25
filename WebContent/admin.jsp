<%@page import="fr.dauphine.sharemarket.model.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	if(session.getAttribute("connected_user")!=null){
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("connected_user");
		if(utilisateur.getAdministrateur()!=1){
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
<title>Administration home page</title>
</head>
<body>
<h1>Administration home page</h1>
<% if(request.getAttribute("message_info")!=null){
	out.println("<p>"+request.getAttribute("message_info")+"</p>");
}%>
<a href="userform.html">Find User</a>
<a href="MInscription">Member society inscription</a>
</body>
</html>