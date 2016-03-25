<%@page import="fr.dauphine.sharemarket.model.Utilisateur"%>
<%@page import="fr.dauphine.sharemarket.model.Contrat"%>
<%@page import="java.util.List"%>
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
    out.println("<td>"+contrat.getDateEmission().toString().subSequence(0, 10)+"</td>");
    if(contrat.getEtat()==0) out.println("<td>En cours</td>");
    else out.println("<td>Fini</td>");
    if(contrat.getPrixFixe()!=0)out.println("<td>"+contrat.getPrixFixe()+"</td>");
    else out.println("<td></td>");
    if(contrat.getPrixDepart()!=0){
    	out.println("<td>"+contrat.getPrixDepart()+"</td><td>"+contrat.getDateFinEnchere()+"</td>"); 
    }
    else out.println("<td></td><td></td>");
    if(contrat.getAchatVente()==0) out.println("<td>Vente</td>");
    else out.println("<td>Achat</td>");
    out.println("<td>"+contrat.getTypeContrat().getTypeContrat()+"</td>");
    out.println("<td><a href=\"MemberSocietyFunction?Demande=EditExistingContract&id="+contrat.getIdContrat()+"\">Modifier</a></td>");
    out.println("</tr>");
}
%>
</table>
</body>
</html>