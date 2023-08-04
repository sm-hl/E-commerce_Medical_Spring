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
     


     <main>
            <c:if test="${status == 'saved'}">
                <div class="alert alert-success d-flex align-items-center justify-content-center px-5 mb-0" role="alert">
                    <svg id="check-circle-fill" fill="currentColor" class="me-4" width="24" height="24" viewBox="0 0 16 16">
                        <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"></path>
                    </svg>
                    <span>Votre panier a été enregistré avec succès!</span>
                </div>
            </c:if>
            <c:if test="${status == 'updated'}">
                <div class="alert alert-success d-flex align-items-center justify-content-center px-5 mb-0" role="alert">
                    <svg id="check-circle-fill" fill="currentColor" class="me-4" width="24" height="24" viewBox="0 0 16 16">
                        <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"></path>
                    </svg>
                    <span>Votre panier a été mis à jour avec succès!</span>
                </div>
            </c:if>
            <c:if test="${status == 'empty'}">
                <div class="alert alert-danger d-flex align-items-center justify-content-center px-5" role="alert">
                    <svg id="exclamation-triangle-fill" class="me-4" width="24" height="24" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"></path>
                    </svg>
                    <span>Votre compte n'a pas un panier enregistré!</span>
                </div>
            </c:if>
            <c:if test="${status == 'stockInsuffisant'}">
                <div class="alert alert-danger d-flex align-items-center justify-content-center px-5" role="alert">
                    <svg id="exclamation-triangle-fill" class="me-4" width="24" height="24" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"></path>
                    </svg>
                    <span>Stock insuffisant!</span>
                </div>
            </c:if>
            <div class="text-center mt-2">
                <p class="fs-2 mb-0">Votre panier</p>
            </div>
            <c:if test="${stocks.size() == 0}">
                <div class="d-flex flex-row justify-content-center my-5">
                    <div class="alert alert-danger d-flex align-items-center justify-content-center px-5" role="alert">
                        <svg id="exclamation-triangle-fill" class="me-4" width="24" height="24" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"></path>
                        </svg>
                        <div>
                            Votre panier est vide!
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${stocks.size() > 0}">
                <div class="text-center mb-2">
                    <p class="fs-5 mb-1 text-gray">Ce tableau affiche le panier temporaire sauvegardé dans votre navigateur</p>
                </div>
                <table class="table table-dark table-striped">
                    <thead>
                        <tr class="table-dark">
                            <th scope="col" class="table-dark">Nom</th>
                            <th scope="col" class="table-dark">Prix unitaire</th>
                            <th scope="col" class="table-dark">Catégorie</th>
                            <th scope="col" class="table-dark">En stock</th>
                            <th scope="col" class="table-dark">Votre panier</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ stocks }" var="stock">
                        <tr class="table-dark">
                            <th scope="row" class="table-dark">${stock.getProduit().getNomProduit()}</th>
                            <td class="table-dark" id="${stock.getProduit().getNomProduit()}Prix">${stock.getProduit().getPrixProduit()}</td>
                            <td class="table-dark">${stock.getProduit().getCategorieProduit()}</td>
                            <td class="table-dark" id="${stock.getProduit().getNomProduit()}Stock">${stock.getNbStock()}</td>
                            <td class="table-dark">
                                <button type="button" class="btn btn-primary btn-sm btnSM me-5" onclick="Decrementer('${stock.getProduit().getNomProduit()}')">-</button>
                                <span class="text-center my-0 me-5 nb-products-in-cart" id="${stock.getProduit().getNomProduit()}"></span>
                                <button type="button" class="btn btn-primary btn-sm btnSM" onclick="Incrementer('${stock.getProduit().getNomProduit()}')">+</button>
                            </td>
                        </tr>
                      </c:forEach>
                    </tbody>
                </table>
                <p class="text-center fs-3">
                    Cout tôtal : 
                    <span id="coutTotal" class="text-primary fw-bold price-total"></span>
                    <span class="text-primary fw-bold">
                        DH
                    </span>
                </p>
            </c:if>
            
            <div class="d-flex flex-row justify-content-center">
                <c:if test="${user != null}">
                    <a href="/Panier/enregistrer" class="mx-2 my-2">
                        <button type="button" class="btn btn-primary mx-2" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Enregistrer ce panier avec votre compte">
                            Enregistrer
                        </button>
                    </a>
                    <a href="/Panier/achat" class="mx-2 my-2">
                        <button type="button" class="btn btn-primary mx-2" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Passer au procédure d'achat">
                            Acheter
                        </button>
                    </a>
                </c:if>
                <c:if test="${user == null}">
                    <div class="mx-2 my-2" tabindex="0" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Vous n'êtes pas connecté!">
                        <button type="button" class="btn btn-primary" disabled>
                            Enregistrer
                        </button>
                    </div>
                    <div class="mx-2 my-2" tabindex="0" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Vous n'êtes pas connecté!">
                        <button type="button" class="btn btn-primary" disabled>
                            Acheter
                        </button>
                    </div>
                </c:if>
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
    <script src="/panier.js"></script>
  </body>
</html>