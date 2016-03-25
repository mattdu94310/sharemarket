<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rechercher votre société</title>
<style type="text/css">
    .container {
        width: 500px;
        clear: both;
    }
    .container input {
        width: 100%;
        clear: both;
    }

    </style>
</head>
<body>
<div class="container">
<h1>Recherche Societe</h1>

	<form action="Rechsociete" method="POST">
		<label> ID societé</label> <input type="text" name="id_societe" value="" /> <br />
		<label> Nom de la société</label> <input type="text" name="nom" value="" /> <br /> 
		<label> Membre société</label> <input type="text" name="membre_societe_login" value="" /> <br />
		<label> Un secteur d'activité</label> <input type="text" name="secteur_activite" value="" /> <br />
	
		<input type="submit" value="Rechercher societe" name="submit" />
	</form>
</div>
</body>
</html>