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
    <title>Nouveau compte</title>
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
            <h2 class="u-align-center u-custom-font u-font-montserrat u-text u-text-custom-color-5 u-text-1">Créer un nouveau compte</h2>
            
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
                Votre compte a été créée avec succès!
              </div>
            </div>
            </c:if>
            
            
            <div class="u-form u-form-1">
              <form action="register" method="POST" class="u-clearfix u-form-spacing-13 u-form-vertical u-inner-form" style="padding: 0;" name="form">
                <div class="u-form-group u-form-name u-form-partition-factor-2">
                  <label for="name-f18c" class="u-label u-label-2">Username</label>
                  <input type="text" placeholder="" id="username-f18c" name="username" class="u-grey-5 u-input u-input-rectangle u-input-2" required="">
                </div>
                <div class="u-form-group u-form-name u-form-partition-factor-2">
                  <label for="name-f18c" class="u-label u-label-2">Mot de passe</label>
                  <input type="password" placeholder="" id="password-f18c" name="password" class="u-grey-5 u-input u-input-rectangle u-input-2" required="">
                </div>
                <div class="u-form-email u-form-group u-form-partition-factor-2">
                  <label for="email-f18c" class="u-label u-label-1">Email</label>
                  <input type="email" placeholder="" id="email-f18c" name="email" class="u-grey-5 u-input u-input-rectangle u-input-1" required="">
                </div>
                <div class="u-form-group u-form-name u-form-partition-factor-2">
                  <label for="name-f18c" class="u-label u-label-2">Nom complet</label>
                  <input type="text" placeholder="" id="nomComplet-f18c" name="nomComplet" class="u-grey-5 u-input u-input-rectangle u-input-2" required="">
                </div>
                <div class="u-form-group u-form-name u-form-partition-factor-2">
                  <label for="name-f18c" class="u-label u-label-2">Adresse</label>
                  <input type="text" placeholder="" id="adresse-f18c" name="adresse" class="u-grey-5 u-input u-input-rectangle u-input-2" required="">
                </div>
                <div class="u-form-group u-form-name u-form-partition-factor-2">
                  <label for="name-f18c" class="u-label u-label-2">Pays</label>
                  <select class="u-grey-5 u-input u-input-rectangle u-input-2" name="pays">
                    <option value="Maroc" selected>Maroc</option>
                    <option value="France">France</option>
                    <option value="USA">USA</option>
                    <option value="Allemand">Allemand</option>
                    <option value="Japon">Japon</option>
                    <option value="Canada">Canada</option>
                  </select>
                </div>
                <div class="u-form-group u-form-name u-form-partition-factor-2">
                  <label for="name-f18c" class="u-label u-label-2">Province</label>
                  <input type="text" placeholder="" id="province-f18c" name="province" class="u-grey-5 u-input u-input-rectangle u-input-2" required="">
                </div>
                <div class="u-form-group u-form-partition-factor-2 u-form-phone u-form-group-3">
                  <label for="phone-cbff" class="u-label u-label-3" wfd-invisible="true">Tel</label>
                  <input type="tel" pattern="\+?\d{0,2}[\s\(\-]?([0-9]{3})[\s\)\-]?([\s\-]?)([0-9]{3})[\s\-]?([0-9]{2})[\s\-]?([0-9]{2})" placeholder="" id="phone-cbff" name="tel" class="u-grey-5 u-input u-input-rectangle u-input-3" required="">
                </div>
                <div class="u-align-center u-form-group u-form-submit">
                  <input class="u-btn-submit u-button-style u-custom-color-5 u-btn-1" type="submit" value="Créer le compte">
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