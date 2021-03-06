<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ajouter un nouvel employ�</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/semantic-ui@2.2.13/dist/semantic.min.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/semantic-ui@2.2.13/dist/semantic.min.js"></script>
<script src="index.js"></script>
</head>

<header>
	<ul class="nav nav-tabs">
		<li class="nav-item"><a class="nav-link active" href="http://localhost:8080/paie/mvc/employes/lister">Employ�s</a>
		</li>
		<li class="nav-item"><a class="nav-link" href="http://localhost:8080/paie/mvc/bulletins/lister">Bulletins</a></li>
	</ul>
</header>

<body class="ui container-fluid">

	<h1 class="ui header" align="center">Ajouter un employ�</h1>

	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<form:form method="POST" modelAttribute="employe">
						<div class="form-group">
							<form:label path="matricule">Matricule</form:label> <form:input type="text"
								path="matricule"/>
						</div>
						<div class="form-group">
							<form:label path="entreprise.id">Entreprise</form:label> <form:select path="entreprise.id">				
									<form:options items="${entreprise}"  itemValue="id" itemLabel="denomination"></form:options>
							</form:select>
						</div>
						<div class="form-group">
							<form:label path="profilRemuneration.id">Profil</form:label> <form:select path="profilRemuneration.id">				
									<form:options items="${profil}"  itemValue="id" itemLabel="code"></form:options>
							</form:select>
						</div>
						<div class="form-group">
							<form:label path="grade.id">Grade</form:label> <form:select path="grade.id">				
									<form:options items="${grade}"  itemValue="id" itemLabel="code"></form:options>
							</form:select>
						</div>
							<input type="submit"
								class="btn btn-primary align-self-end align-items-end" value="Ajouter" />
					</form:form>
				</div>
			</div>
		</div>
	</div>
	</body>
</html>