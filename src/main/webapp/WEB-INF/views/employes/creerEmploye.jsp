<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ajouter un nouvel employé</title>
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
		<li class="nav-item"><a class="nav-link active" href="index.html">Employés</a>
		</li>
		<li class="nav-item"><a class="nav-link" href="#">Bulletins</a></li>
	</ul>
</header>

<body class="ui container-fluid">

	<h1 class="ui header" align="center">Ajouter un employé</h1>

	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<form method="POST">
						<div class="form-group">
							<label for="matricule">Matricule</label> <input type="text"
								class="form-control" placeholder="" id="matricule"
								name="matricule">
						</div>
						<div class="form-group">
							<label for="entreprise">Entreprise</label> <select
								class="form-control" id="entreprise">
								<c:forEach items="${entreprise}" var="entreprise">
									<option>${entreprise.denomination}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="profil">Profil</label><select class="form-control"
								id="profil" name="profil">
								<c:forEach items="${profil}" var="profil">
									<option>${profil.code}</option>
								</c:forEach>
							</select>
							<div class="form-group">
								<label for="grade">Grade</label> <select class="form-control"
									id="grade" name="grade">
									<c:forEach items="${grade}" var="grade">
										<option>${grade.code}</option>
									</c:forEach>
								</select>
							</div>
							<button type="submit"
								class="btn btn-primary align-self-end align-items-end">Ajouter</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	</body>
</html>