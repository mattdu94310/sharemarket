<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Recherche Contrat</h1>
	
	<form action="Rechcontrat" method="POST">
		<label> Type contrat</label> <input type="text" name="type_contrat" value="" /> <br />
		<label> ID Société</label> <input type="text" name="id_societe" value="" /> <br /> 
		<label> Prix fixe</label> <input type="text" name="prix_fixe" value="" /> <br />
		<label> Prix depart</label> <input type="text" name="prix_depart" value="" /> <br />
		<label> Date Emission</label> <input type="text" name="date_emission" value="" /> <br />
	
		<input type="submit" value="Rechercher" name="submit" />
	</form>

</body>
</html>