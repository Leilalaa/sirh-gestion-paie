<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Visualiser bulletin</title>
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

  <h1 class="ui header" align = "center">Bulletin de salaire</h1>
  <br />
  <p align ="right">
    <strong>Periode</strong>
    <br/>
    Du ${bulletinSalaire.periode.dateDebut} au ${bulletinSalaire.periode.dateFin}
  </p>
  <div class="d-flex flex-row flex-wrap">
    <div class="p-2">
    <p>
      <strong>Entreprise</strong>
      <br/>
      ${bulletinSalaire.remunerationEmploye.entreprise.denomination}
      <br/>
      SIRET : ${bulletinSalaire.remunerationEmploye.entreprise.siret}

  </div>
  <div class="p-2" style='float:right;'>
  <p>
    <strong>Matricule</strong> : ${bulletinSalaire.remunerationEmploye.matricule}
</div>
<br/>
<br/>
<br/>
</div>
  <strong>Salaire</strong>
  <table class="table table-striped table-bordered l-10 r-10">
  <thead>
    <tr>
      <th scope="col">Rubriques</th>
      <th scope="col">Base</th>
      <th scope="col">Taux Salarial</th>
      <th scope="col">Montant Salarial</th>
      <th scope="col">Taux Patronal</th>
      <th scope="col">Cot. patronales</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Salaire de base</td>
      <td>${bulletinSalaire.remunerationEmploye.grade.nbHeuresBase}</td>
      <td>${resultat.salaireDeBase}</td>
      <td>${resultat.salaireDeBase}</td>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <td>Prime Except.</td>
      <td></td>
      <td></td>
      <td>${bulletinSalaire.primeExceptionnelle}</td>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <td>Salaire Brut</td>
      <td></td>
      <td></td>
      <td>${resultat.salaireBrut}</td>
      <td></td>
      <td></td>
    </tr>
  </tbody>
</table>
  <br />
<strong>Cotisations</strong>
<table class="table table-striped table-bordered l-10 r-10">
<thead>
  <tr>
    <th scope="col">Rubriques</th>
    <th scope="col">Base</th>
    <th scope="col">Taux Salarial</th>
    <th scope="col">Montant Salarial</th>
    <th scope="col">Taux Patronal</th>
    <th scope="col">Cot. patronales</th>
  </tr>
</thead>
<tbody>

  
  <c:forEach var="cotisation" items="${bulletinSalaire.remunerationEmploye.profilRemuneration.cotisationsNonImposables}">
  <tr> 
    <td>${cotisation.code} ${cotisation.libelle}</td>
    <td>${resultat.salaireDeBase}</td>
    <td>${cotisation.tauxSalarial}</td>
    <td>${resultat.salaireDeBase * cotisation.tauxSalarial}</td>
    <td>${cotisation.tauxPatronal}</td>
    <td>${cotisation.tauxPatronal * resultat.salaireDeBase}</td>    
  </tr>
     </c:forEach>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>Total Retenue</td>
    <td></td>
    <td></td>
    <td>${resultat.totalRetenueSalarial}</td>
    <td></td>
    <td>${resultat.totalCotisationsPatronales}</td>
  </tr>

</tbody>
</table>
<br />
<strong>NET Imposable : </strong>  ${resultat.netImposable}
<table class="table table-striped table-bordered l-10 r-10">
<thead>
  <tr>
    <th scope="col">Rubriques</th>
    <th scope="col">Base</th>
    <th scope="col">Taux Salarial</th>
    <th scope="col">Montant Salarial</th>
    <th scope="col">Taux Patronal</th>
    <th scope="col">Cot. patronales</th>
  </tr>
</thead>
<tbody>

<c:forEach var="cotisation" items="${bulletinSalaire.remunerationEmploye.profilRemuneration.cotisationsImposables}">
  <tr>
    <td>${cotisation.code} ${cotisation.libelle}</td>
    <td>${resultat.salaireBrut}</td>
    <td>${cotisation.tauxSalarial}</td>
    <td>${resultat.salaireDeBase * cotisation.tauxSalarial}</td>
    <td></td>
    <td></td>
  </tr>
      </c:forEach>

</tbody>
</table>
<br />
<p align ="right">
  <strong>NET A PAYER :</strong>  ${resultat.netAPayer}
</p>
</body>
</html>
