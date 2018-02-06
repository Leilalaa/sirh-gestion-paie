<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Liste des bulletins</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.2.13/dist/semantic.min.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.2.13/dist/semantic.min.js"></script>
    <script src="index.js"></script>
</head>

<header>
  <ul class="nav nav-tabs">
  <li class="nav-item">
    <a class="nav-link" href="http://localhost:8080/paie/mvc/employes/lister">Employes</a>
  </li>
  <li class="nav-item">
   <a class="nav-link active" href="http://localhost:8080/paie/mvc/bulletins/lister">Bulletins</a>
  </li>
</ul>
</header>

<body class="ui container-fluid">

  <h1 class="ui header" align = "center">Liste des bulletins</h1>
  <br />
  <a href="http://localhost:8080/paie/mvc/bulletins/creer" role = "button" class="btn btn-primary" style='float:right;'>Créer un nouveau bulletin</a>
  <br />
  <br />
  <br />
  <table class="table table-striped table-bordered l-10 r-10">
  <thead>
    <tr>
      <th scope="col">Date/heure creation</th>
      <th scope="col">Periode</th>
      <th scope="col">Matricule</th>
      <th scope="col">Salaire brut</th>
      <th scope="col">Net imposable</th>
      <th scope="col">Net à payer</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${bulletinSalaire}" var="bulletinSalaire">
    							<tr>
									<td>${bulletinSalaire.id}</td>
									<td>${bulletinSalaire.periode}</td>
									<td>test</td>
									<td>test</td>
									<td>test</td>
									<td>test</td>
									<td>Visualiser</td>
								</tr>
	</c:forEach>
  </tbody>
</table>
</body>
</html>
