<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Inscription Investisseur</h1>
	<h2>Ici commence l'aventure !</h2>
	<form action="Inscription" method="POST">
		<label> Nom</label> <input type="text" name="nom" value="" /> <br />
		<label> Prenom</label> <input type="text" name="prenom" value="" /> <br /> 
		<label> Login</label> <input type="text" name="login" value="" /> <br />
		<label> Password</label> <input type="password" name="password" value="" /> <br />
	
		<input type="submit" value="Enregistrer" name="submit" />
	</form>

</body>
</html>