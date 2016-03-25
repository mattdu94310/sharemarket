<%@page import="fr.dauphine.sharemarket.model.Utilisateur"%>
<%@page import="fr.dauphine.sharemarket.model.Type_Contrat"%>
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
<title>Edition d'un contrat</title>
</head>
<body>
<h1>Edition d'un contrat</h1>
<%
if(request.getAttribute("message_error")!=null){
	out.println("<p style=\"color:red;\">"+request.getAttribute("message_error")+"</p>");
}
int societe_id = (Integer)request.getAttribute("societe_id");
Contrat contrat = (Contrat) request.getAttribute("contrat");
List<Type_Contrat> types = (List<Type_Contrat>) request.getAttribute("types");
if(contrat==null || contrat.getPrixFixe()!=0){
%>
<h2>Contrat Prix Fixe</h2>
<form action="MemberSocietyFunction" method="post">
<input type="hidden" name="societe_id" value="<%out.print(societe_id); %>"/>
<input type="hidden"  name="contrat_id" value="<%if(contrat!=null){out.print(contrat.getIdContrat());}else out.print(-1);%>" />
<label>Prix fixe </label><input type="text"  name="prix_fixe" value="<%if(contrat!=null){out.print(contrat.getPrixFixe());}%>" /> <br /><br />
<label>Achat (coché)/ Vente </label><INPUT type="checkbox" name="achat_vente" value="valide" <%if(contrat==null || contrat.getAchatVente()==0)out.print("checked");%>><br /><br />
<label>Type de contrat </label>
<select name="type_contrat">
	<%
	for(Type_Contrat type : types){
		String selected = "";
		if(contrat!=null && contrat.getTypeContrat().getIdTypeContrat()==type.getIdTypeContrat())selected="selected";
		out.print("<option value=\""+type.getIdTypeContrat()+"\" "+selected+" >"+type.getTypeContrat()+"</option>");
	}
	%>
</select><br /><br />
<% if(contrat==null){%>
<input type="submit" name="Demande" value="Nouveau Contrat Prix Fixe"/>
<%}else{%>
<input type="submit" name="Demande" value="Modifier Contrat Prix Fixe"/>	
<%}%>
</form>
<br />
<%}

if(contrat==null || contrat.getPrixDepart()!=0){ %>
<h2>Contrat Enchère</h2>
<form action="MemberSocietyFunction" method="post">
<input type="hidden" name="societe_id" value="<%out.print(societe_id); %>"/>
<input type="hidden"  name="contrat_id" value="<%if(contrat!=null){out.print(contrat.getIdContrat());}else out.print(-1);%>" /> 
<label>Prix Départ</label><input type="text"  name="prix_depart" value="<%if(contrat!=null){out.print(contrat.getPrixDepart());}%>" />  <br /><br />
<label>Date fin echère </label><input type="date" name="date_fin_echere" value="<%if(contrat!=null){out.print(contrat.getDateFinEnchere().toString().subSequence(0, 10));}%>"> <br /><br />
<label>Achat (coché)/ Vente </label><INPUT type="checkbox" name="achat_vente" value="achat" <%if(contrat==null || contrat.getAchatVente()==1)out.print("checked");%>><br /><br />
<label>Type de contrat </label>
<select name="type_contrat">
	<%
	for(Type_Contrat type : types){
		String selected = "";
		if(contrat!=null && contrat.getTypeContrat().getIdTypeContrat()==type.getIdTypeContrat())selected="selected";
		out.print("<option value=\""+type.getIdTypeContrat()+"\" "+selected+" >"+type.getTypeContrat()+"</option>");
	}
	%>
</select> <br /><br />
<% if(contrat==null){%>
<input type="submit" name="Demande" value="Nouveau Contrat Enchere"/>
<%}else{%>
<input type="submit" name="Demande" value="Modifier Contrat Enchere"/>	
<%}%>
</form>
<%} %>
</body>
</html>