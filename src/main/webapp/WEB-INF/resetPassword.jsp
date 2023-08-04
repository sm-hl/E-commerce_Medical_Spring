<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html style="font-size: 16px;">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="S&amp;apos;inscrire">
    <meta name="description" content="">
    <meta name="page_type" content="np-template-header-footer-from-plugin">
    <title>Récupération du compte</title>
    <link rel="stylesheet" href="nicepage.css" media="screen">
    <link rel="stylesheet" href="sinscrire.css" media="screen">
    <link rel="stylesheet" href="bootstrap.min.css" media="screen">

    <meta name="generator" content="Nicepage 4.1.0, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">
    <link id="u-page-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i">
    <!-- fonts awesome -->
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <script type="application/ld+json">{
		"@context": "http://schema.org",
		"@type": "Organization",
		"name": "",
		"logo": "images/logo.png"
}</script>
    <meta name="theme-color" content="#478ac9">
    <meta property="og:title" content="sinscrire">
    <meta property="og:type" content="website">
  </head>
  <body class="u-body u-overlap u-overlap-contrast u-stick-footer">
    <section class="u-align-center u-clearfix u-image u-section-1" id="carousel_e654" data-image-width="899" data-image-height="845">
      <div class="u-clearfix u-sheet u-sheet-1">
        <div class="u-custom-color-5 u-shape u-shape-rectangle u-shape-1"></div>
        <div class="u-container-style u-custom-color-8 u-group u-radius-50 u-shape-round u-group-1">
          <div class="u-container-layout u-valign-top u-container-layout-1">
            <h2 class="u-align-center u-custom-font u-font-montserrat u-text u-text-custom-color-5 u-text-1">Réinitialisation du mot de passe</h2>
            
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
            <c:if test="${success}">
            <div class="d-flex flex-row justify-content-center my-2">
              <div class="alert alert-success" role="alert">
                Votre mot de passe a été changé avec succès!
              </div>
            </div>
            </c:if>
            <div class="d-flex flex-row justify-content-center my-2">
              <p class="fs-5">Définir votre nouveau mot de passe</p>
            </div>
            <div class="u-form u-form-1">
              <form action="/reset_password" method="POST" class="u-clearfix u-form-spacing-13 u-inner-form" style="padding: 0;" name="form">
                <input type="hidden" name="token" value="${token}"/>
                <div class="u-form-password u-form-group my-4">
                  <label for="password-f18c" class="u-label u-label-1">Nouveau mot de passe</label>
                  <input type="password" placeholder="" id="password-f18c" name="password" class="u-grey-5 u-input u-input-rectangle u-input-1" required="">
                </div>
                
                <div class="u-align-center u-form-group u-form-submit">
                  <input class="u-btn-submit u-button-style u-custom-color-5 u-btn-1" type="submit" value="Changer">
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
    
    
    <footer class="u-align-center u-clearfix u-custom-color-5 u-footer u-footer" id="sec-f04e"><div class="u-align-left u-clearfix u-sheet u-sheet-1"></div></footer>
    <section class="u-backlink u-clearfix u-grey-80">
      <p class="u-text">
        <span> &copy; All rights reserved by</span>
      </p>
      <a class="u-link" href="/About.html" target="_blank">
        <span>PIMED</span>
      </a>. 
    </section>
  </body>
</html>