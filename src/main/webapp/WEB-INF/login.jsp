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
    <title>Se connecter</title>
    <link rel="stylesheet" href="nicepage.css" media="screen">
    <link rel="stylesheet" href="se-connecter.css" media="screen">
    <meta name="generator" content="Nicepage 4.1.0, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">
    <!-- fonts awesome -->
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <script type="application/ld+json">{
		"@context": "http://schema.org",
		"@type": "Organization",
		"name": "",
		"logo": "images/logo.png"
}</script>
    <meta name="theme-color" content="#478ac9">
    <meta property="og:title" content="se connecter">
    <meta property="og:type" content="website">
  </head>
  <body class="u-body u-image u-overlap u-overlap-transparent u-stick-footer " style="background-position: 50% 50%; background-image: url(&quot;images/visuel_actu_journee_mondiale_sante.jpg&quot;);">
    <section class="u-align-center u-clearfix u-section-1" src="" id="carousel_08f7">
      <div class="u-clearfix u-sheet u-sheet-1">
        <div class="u-clearfix u-layout-wrap u-layout-wrap-1">
          <div class="u-gutter-0 u-layout">
            <div class="u-layout-row">
              <div class="u-align-center u-container-style u-custom-color-5 u-layout-cell u-radius-50 u-shape-round u-size-60 u-layout-cell-1">
                
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
                
                <div class="u-container-layout u-valign-middle u-container-layout-1">
                  <div class="u-form u-form-1">
                    <form action="/login" method="POST" class="u-clearfix u-form-spacing-50 u-form-vertical u-inner-form" style="padding: 0px;" source="custom" name="form">
                      <div class="u-form-group u-form-name u-form-group-1">
                        <label for="name-30a4" class="u-form-control-hidden u-label" wfd-invisible="true"></label>
                        <input type="text" placeholder="Entrez votre username..." id="name-30a4" name="username" class="u-border-2 u-border-white u-input u-input-rectangle u-radius-50 u-white u-input-1" required="">
                      </div>
                      <div class="u-form-password u-form-group u-form-group-2">
                        <label for="password-cd2c" class="u-form-control-hidden u-label" wfd-invisible="true"></label>
                        <input type="password" id="password-cd2c" name="password" class="u-border-2 u-border-white u-input u-input-rectangle u-radius-50 u-white u-input-2" required="" placeholder="Entrez votre mot de passe...">
                      </div>
                      <div class="u-align-left u-form-group u-form-submit u-form-group-3">
                        <input type="submit" value="Se connecter" class="u-active-white u-border-none u-btn u-btn-round u-btn-submit u-button-style u-custom-color-8 u-hover-white u-radius-50 u-text-active-black u-text-hover-black u-btn-1">
                      </div><h3 style="margin-left:120px; margin-top:50px; "><a style=" text-decoration-line: underline; text-decoration-color: white; font-size: 25px; " href="/register">Créer un nouveau compte</a> </h3>
                    <h3 style=" margin-left:120px; margin-top:0; "><a style=" text-decoration-line: underline; text-decoration-color: white; font-size: 25px;" href="/forgot_password">Mot de passe oublié?</a> </h3>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    
    <div style="height: 32vh;"></div>
    <footer  class="u-align-center u-clearfix u-custom-color-5 u-footer u-footer" id="sec-f04e"><div  class="u-align-left u-clearfix u-sheet u-sheet-1"></div></footer>
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