<%@page import="fr.dauphine.sharemarket.model.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	if(session.getAttribute("connected_user")!=null){
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("connected_user");
		if(utilisateur.getAdministrateur()==1){
			getServletContext().getRequestDispatcher("/Admin").forward(request, response);
		}else if (utilisateur.getMembreSociete()==1){
			getServletContext().getRequestDispatcher("/MembreSociete").forward(request, response);
		}else{
			getServletContext().getRequestDispatcher("/Investissor").forward(request, response);
		}
	}

	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Page d'acceuil</h1>
	<h2>Connectez-vous</h2>
	<%	
	if(session.getAttribute("connection_error")!=null){
		session.removeAttribute("connection_error");
		out.println("<p style=\"color:red;\">Identifiants incorrects</p>"); 
	}%>
	<form action="Connexion" method="POST">
		<label>Email</label> <input type="text" name="email" value="" /> <br />
		<label>Password</label> <input type="password" name="password" value="" /> <br /> <input type="submit" value="Envoyer" name="submit" />
	</form>
	<h2>Par ici pour s'inscrire</h2>
	<a href="">S'inscrire</a>
</body>
</html>