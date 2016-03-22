<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Inscription Membre Société</h1>
<form action="AdminFunction" method="post">
	<label>Login</label> <input type="text" name="login" value="" /> <br />
	<label>Mot de passe</label> <input type="password" name="password" value="" /> <br />
	<label>Nom</label> <input type="text" name="nom" value="" /> <br /> 
	<label>Prenom</label> <input type="text" name="prenom" value="" /> <br /> 	
	<label>Nom Société</label> <input type="text" name="nomsociete" value="" /><br />
	<input type="submit" value="addMemberSociety" name="Demande" />
</form>
</body>
</html>