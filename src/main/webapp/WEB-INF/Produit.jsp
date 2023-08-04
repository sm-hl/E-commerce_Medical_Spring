<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html style="font-size: 16px;">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="page_type" content="np-template-header-footer-from-plugin">
    <title>Panier</title>
    <link rel="stylesheet" href="/nicepage.css" media="screen">
<link rel="stylesheet" href="/panier1.css" media="screen">
<link rel="stylesheet" href="/bootstrap.min.css" media="screen">
    <script class="u-script" type="text/javascript" src="/jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="/nicepage.js" defer=""></script>
    <meta name="generator" content="Nicepage 4.1.0, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">
    <!-- fonts awesome -->
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    
    <script src="/panier1.js"></script>
    <script type="application/ld+json">{
		"@context": "http://schema.org",
		"@type": "Organization",
		"name": "",
		"logo": "images/logo.png"
}</script>
    <meta name="theme-color" content="#478ac9">
    <meta property="og:title" content="">
    <meta property="og:type" content="website">
  </head>
  <body onload="populateTableList()" class="u-body u-overlap u-overlap-contrast u-stick-footer">
  <c:import url="/inc/header.jsp"/>
     

      <main class="d-flex flex-row bd-highlight mb-3 justify-content-evenly" style="margin-top: 10rem;">
        <div class="p-2 bd-highlight me-5" style="width: 50%"><!-- Image -->
          <div class="d-flex flex-row bd-highlight mb-3 justify-content-evenly">
            <img src="<c:url value='${produit.getUrlImageProduit()}'/>" alt="${produit.getNomProduit()}" width="256" height="169" class="rounded">
          </div>

          <hr class="dropdown-divider">
            <p class="text-center my-0 fw-bold">Votre panier</p>
            <div class="d-flex flex-row justify-content-evenly">
                <button type="button" class="btn btn-primary btn-sm btnSM" onclick="Decrementer('${produit.getNomProduit()}')">-</button>
                <span class="text-center my-0 nb-products-in-cart" id="${produit.getNomProduit()}"></span>
                <button type="button" class="btn btn-primary btn-sm btnSM" onclick="Incrementer('${produit.getNomProduit()}')">+</button>
          </div>

          <div class="d-flex flex-row bd-highlight mb-3 justify-content-evenly">
            
          </div>
        </div>

        <div class="p-2 bd-highlight" style="width: 100%">
          <table class="table">
            <thead>
              <tr>
                <th scope="col">Nom</th>
                <th scope="col">Prix</th>
                <th scope="col">En stock</th>
                <th scope="col">Catégorie</th>
                <th scope="col">Fournisseur</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">${produit.getNomProduit()}</th>
                <td>${produit.getPrixProduit()} DH</td>
                <td id="${produit.getNomProduit()}Stock">${produit.getStock().getNbStock()}</td>
                <td>${produit.getCategorieProduit()}</td>
                <td>${produit.getFournisseur().getNomFournisseur()}</td>
              </tr>
            </tbody>
          </table>

          <textarea class="form-control" rows="6" readonly>${produit.getDescriptionProduit()}</textarea>


        </div>
      </main>
     





      <footer class="u-align-center u-clearfix u-custom-color-5 u-footer u-footer" id="sec-f04e">
        <div class="u-align-left u-clearfix u-sheet u-sheet-1"></div></footer>
    <section class="u-backlink u-clearfix u-grey-80">
      <p class="u-text">
        <span> &copy; All rights reserved by</span>
      </p>
      <a class="u-link" href="/About.html" target="_blank">
        <span>PIMED</span>
      </a>. 
    </section>
    <script src="/bootstrap.min.js"></script>
    <script src="/bootstrap.bundle.min.js"></script>
    <script src="/selectionProduits.js"></script>
  </body>
</html>