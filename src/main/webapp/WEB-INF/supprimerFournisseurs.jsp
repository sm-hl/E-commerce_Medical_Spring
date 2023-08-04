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
<link rel="stylesheet" href="/bootstrap.min.css" media="screen">
<link rel="stylesheet" href="/headers.css" media="screen">
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
          <c:import url="/inc/header-admin.jsp"/>
          <main>
            
            <c:import url="/inc/panel_fournisseurs.jsp"/>
            
            <c:if test="${errors != null || error != null}">
                <div class="d-flex flex-row justify-content-center my-2">
                  <div class="alert alert-danger" role="alert">
                    <ul>
                      <c:forEach items="${errors}" var="error">
                        <li><c:out value="${error.defaultMessage}" /></li>
                      </c:forEach>
                      <c:if test="${error != null && not empty error}">
                        <li><c:out value="${error}"/></li>
                      </c:if>
                    </ul>
                  </div>
                </div>
            </c:if>
            <c:if test="${success != null && not empty success}">
              <div class="alert alert-success d-flex align-items-center justify-content-center px-5 mb-0" role="alert">
                <svg id="check-circle-fill" fill="currentColor" class="me-4" width="24" height="24" viewBox="0 0 16 16">
                    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"></path>
                </svg>
                <span>${success}</span>
              </div>
            </c:if>

            <div class="text-center my-5">
                <span class="fs-3 text-gray">Suppression des fournisseurs</span>
            </div>
            <div class="table-responsive my-4">
                <table class="table table-primary table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Nom</th>
                            <th scope="col"> </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ fournisseurs }" var="fournisseur">
                            <tr>
                                <form method="POST" action="/Admin/supprimerFournisseurs">
                                    <input type="hidden" name="id" value="${fournisseur.getId()}">
                                    <td class="fs-3">${fournisseur.getNomFournisseur()}</td>
                                    <td><input type="submit" class="btn btn-outline-primary" value="Supprimer"></td>
                                </form>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
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