<%@page import="fr.dauphine.sharemarket.model.Contrat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mes contrats</title>
</head>
<body>
<h1>Mes contrats</h1>
<% 
@SuppressWarnings("unchecked")
List<Contrat> contrats = (List<Contrat>) request.getAttribute("contrats");
%>
<table>
<tr><th>Date d'émission</th><th>Etat</th><th>Prix Fixe</th><th>Prix de Départ</th><th>Date de fin d'enchère</th><th>Achat/Vente </th><th>Type contrat</th><th>Actions</th></tr>
<% 
for(Contrat contrat : contrats) {
	out.println("<tr>");
    out.println("<td>"+contrat.getDateEmission()+"</td>");
    out.println("<td>"+contrat.getEtat()+"</td>");
    out.println("<td>"+contrat.getPrixFixe()+"</td>");
    out.println("<td>"+contrat.getPrixDepart()+"</td>");
    out.println("<td>"+contrat.getDateFinEnchere()+"</td>");
    out.println("<td>"+contrat.getAchatVente()+"</td>");
    out.println("<td>"+contrat.getTypeContrat().getTypeContrat()+"</td>");
    out.println("<td></td>");
    out.println("</tr>");
}
%>
</table>
</body>
</html>