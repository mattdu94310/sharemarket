<%@page import="fr.dauphine.sharemarket.error.MessagesDErreurs"%>
<%@page import="fr.dauphine.sharemarket.model.Societe"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<input type="submit" name="Demande" value="Modifier Societe" />
	</form>
	<%} %>
</body>
</html>