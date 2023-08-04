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
    <title>Profil</title>
    <link rel="stylesheet" href="nicepage1.css" media="screen">
    <link rel="stylesheet" href="Profil-de-Client.css" media="screen">
    <meta name="generator" content="Nicepage 4.1.0, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">
    <link rel="stylesheet" href="nicepage.css" media="screen">
    <meta name="generator" content="Nicepage 4.1.0, nicepage.com">
    <script class="u-script" type="text/javascript" src="jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="nicepage.js" defer=""></script>
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  
  <style>
      @import url('https://fonts.googleapis.com/css2?family=Playfair+Display+SC:ital,wght@1,900&display=swap');
      .pimed-logo{
    font-family: 'Playfair Display SC', serif;
    font-size: 25px;
}
    </style>
    <script type="application/ld+json">{
		"@context": "http://schema.org",
		"@type": "Organization",
		"name": "Site1"
}</script>
    <meta name="theme-color" content="#478ac9">
    <meta property="og:title" content="">
    <meta property="og:description" content="">
    <meta property="og:type" content="website">
  </head>
 <body class="u-body u-overlap u-overlap-contrast u-stick-footer">
    <c:import url="/inc/header.jsp"/>
    <section class="u-clearfix u-expanded-width-xl u-white u-section-1" id="carousel_8049">
      <div class="u-container-style u-grey-40 u-group u-opacity u-opacity-50 u-shape-rectangle u-group-1">
        <div class="u-container-layout u-container-layout-1">
          <div class="d-flex flex-row justify-content-center" style="margin: 6rem auto 0;">
              <img src="${ personne.getImageUrl() }" alt="profile photo" class="rounded-circle" width="128" height="128">
          </div>
          <div class="d-flex flex-row justify-content-center my-2">
              <form method="POST" action="/Profil/modifyIcon" class="needs-validation" enctype="multipart/form-data" novalidate>
                  <input type="file" id="InputFile" name="photo" class="form-control form-control-sm" required>
                  <div class="d-flex flex-row justify-content-center mt-4">
                      <input type="submit" class="btn btn-primary" value="Envoyer">
                  </div>
              </form>
          </div>

          <p class="u-text u-text-black u-text-default u-text-1"> Informations Personnel</p>
          <div class="u-border-3 u-border-grey-dark-1 u-expanded-width u-line u-line-horizontal u-line-1"></div>
          <div class="u-border-3 u-border-grey-dark-1 u-expanded-width u-line u-line-horizontal u-line-2"></div>
          <p class="u-text u-text-2">
            <span class="u-text-body-color"> &nbsp;&nbsp;&nbsp;<span class="u-icon u-icon-1"><svg class="u-svg-content" viewBox="0 0 49.94 49.94" x="0px" y="0px" style="width: 1em; height: 1em;"><path d="M48.856,22.73c0.983-0.958,1.33-2.364,0.906-3.671c-0.425-1.307-1.532-2.24-2.892-2.438l-12.092-1.757
	c-0.515-0.075-0.96-0.398-1.19-0.865L28.182,3.043c-0.607-1.231-1.839-1.996-3.212-1.996c-1.372,0-2.604,0.765-3.211,1.996
	L16.352,14c-0.23,0.467-0.676,0.79-1.191,0.865L3.069,16.622c-1.359,0.197-2.467,1.131-2.892,2.438
	c-0.424,1.307-0.077,2.713,0.906,3.671l8.749,8.528c0.373,0.364,0.544,0.888,0.456,1.4L8.224,44.701
	c-0.183,1.06,0.095,2.091,0.781,2.904c1.066,1.267,2.927,1.653,4.415,0.871l10.814-5.686c0.452-0.237,1.021-0.235,1.472,0
	l10.815,5.686c0.526,0.277,1.087,0.417,1.666,0.417c1.057,0,2.059-0.47,2.748-1.288c0.687-0.813,0.964-1.846,0.781-2.904
	l-2.065-12.042c-0.088-0.513,0.083-1.036,0.456-1.4L48.856,22.73z"></path></svg><img></span> &nbsp;&nbsp;
            </span>
            <span style="font-size: 1.125rem; font-weight: 400;" class="u-text-body-color">${personne.getNomComplet()}</span>
          </p>
          
          <p class="u-text u-text-default u-text-4">
            <span class="u-text-body-color"><span class="u-icon u-icon-3"><svg class="u-svg-content" viewBox="0 0 54.757 54.757" x="0px" y="0px" style="width: 1em; height: 1em;"><path d="M40.94,5.617C37.318,1.995,32.502,0,27.38,0c-5.123,0-9.938,1.995-13.56,5.617c-6.703,6.702-7.536,19.312-1.804,26.952
	L27.38,54.757L42.721,32.6C48.476,24.929,47.643,12.319,40.94,5.617z M27.557,26c-3.859,0-7-3.141-7-7s3.141-7,7-7s7,3.141,7,7
	S31.416,26,27.557,26z"></path></svg><img></span>
            </span>
            <span style="font-size: 1.125rem; font-weight: 700;" class="u-text-palette-1-base">
              <span class="u-text-body-color"> &nbsp;&nbsp;</span>
              <span class="u-text-body-color" style="font-weight: 400;">${personne.getPays()}</span>
            </span>
          </p>

          <p class="u-text u-text-default u-text-4">
            <span class="u-text-body-color"><span class="u-icon u-icon-3"><svg class="u-svg-content" viewBox="0 0 54.757 54.757" x="0px" y="0px" style="width: 1em; height: 1em;"><path d="M40.94,5.617C37.318,1.995,32.502,0,27.38,0c-5.123,0-9.938,1.995-13.56,5.617c-6.703,6.702-7.536,19.312-1.804,26.952
	L27.38,54.757L42.721,32.6C48.476,24.929,47.643,12.319,40.94,5.617z M27.557,26c-3.859,0-7-3.141-7-7s3.141-7,7-7s7,3.141,7,7
	S31.416,26,27.557,26z"></path></svg><img></span>
            </span>
            <span style="font-size: 1.125rem; font-weight: 700;" class="u-text-palette-1-base">
              <span class="u-text-body-color"> &nbsp;&nbsp;</span>
              <span class="u-text-body-color" style="font-weight: 400;">${personne.getProvince()}</span>
            </span>
          </p>

          <p class="u-text u-text-default u-text-4">
            <span class="u-icon u-icon-5"><svg class="u-svg-content" viewBox="0 0 405.333 405.333" x="0px" y="0px" style="width: 1em; height: 1em;"><path d="M373.333,266.88c-25.003,0-49.493-3.904-72.704-11.563c-11.328-3.904-24.192-0.896-31.637,6.699l-46.016,34.752    c-52.8-28.181-86.592-61.952-114.389-114.368l33.813-44.928c8.512-8.512,11.563-20.971,7.915-32.64    C142.592,81.472,138.667,56.96,138.667,32c0-17.643-14.357-32-32-32H32C14.357,0,0,14.357,0,32    c0,205.845,167.488,373.333,373.333,373.333c17.643,0,32-14.357,32-32V298.88C405.333,281.237,390.976,266.88,373.333,266.88z"></path></svg><img></span>
            <span style="font-size: 1.125rem; font-weight: 700;" class="u-text-palette-1-base">
              <span class="u-text-body-color"> &nbsp;&nbsp;</span>
              <span class="u-text-body-color" style="font-weight: 400;">${personne.getTel()}</span>
            </span>
          </p>

          <p class="u-text u-text-default u-text-4">
            <span class="u-text-body-color"><span class="u-icon u-icon-3"><svg class="u-svg-content" viewBox="0 0 54.757 54.757" x="0px" y="0px" style="width: 1em; height: 1em;"><path d="M40.94,5.617C37.318,1.995,32.502,0,27.38,0c-5.123,0-9.938,1.995-13.56,5.617c-6.703,6.702-7.536,19.312-1.804,26.952
	L27.38,54.757L42.721,32.6C48.476,24.929,47.643,12.319,40.94,5.617z M27.557,26c-3.859,0-7-3.141-7-7s3.141-7,7-7s7,3.141,7,7
	S31.416,26,27.557,26z"></path></svg><img></span>
            </span>
            <span style="font-size: 1.125rem; font-weight: 700;" class="u-text-palette-1-base">
              <span class="u-text-body-color"> &nbsp;&nbsp;</span>
              <span class="u-text-body-color" style="font-weight: 400;">${personne.getAdresse()}</span>
            </span>
          </p>
        </div>
      </div>
      <div class="u-clearfix u-gutter-22 u-layout-wrap u-layout-wrap-1">
        <div class="u-gutter-0 u-layout">
          <div class="u-layout-row">
            <div class="u-size-NaN">
              <div class="u-layout-col">
                <div class="u-size-60">
                  <div class="u-layout-row">
                    <div class="u-container-style u-layout-cell u-size-60 u-layout-cell-1">
                      <div class="u-container-layout u-container-layout-2">
                        <h2 class="u-align-center u-text u-text-6">chANGER VOs DONNEES</h2>
                        <h6 class="u-align-center u-text u-text-7">Pour faire des mise a jour de votre profil</h6>
                        
                        <c:if test="${errors != null || error != null}">
                        <div class="d-flex flex-row justify-content-center my-2">
                          <div class="alert alert-danger" role="alert">
                            <ul>
                              <c:forEach items="${errors}" var="error">
                                <li><c:out value="${error.defaultMessage}" /></li>
                              </c:forEach>
                            </ul>
                          </div>
                        </div>
                        </c:if>

                        <div class="u-expanded-width u-form u-form-1">
                          <form action="/Profil" method="POST" class="u-clearfix u-form-horizontal u-form-spacing-24 u-inner-form" style="padding: 24px;" source="custom" name="form-1">
                            <div class="u-form-group">
                              <label for="nomComplet-3b9a" class="u-form-control-hidden u-label" wfd-invisible="true"></label>
                              <input placeholder="modifier le nom complet" id="nomComplet-3b9a" name="nomComplet" class="u-border-2 u-border-no-left u-border-no-right u-border-no-top u-border-palette-5-dark-1 u-input u-input-rectangle u-input-1" required="required" type="text">
                            </div>
                            <div class="u-align-left u-form-group u-form-submit">
                              <input type="submit" value="Enregistrer" class="u-btn u-btn-submit u-button-style u-hover-palette-1-base u-palette-1-dark-2 u-btn-2">
                            </div>
                          </form>
                        </div>

                        <div class="u-expanded-width u-form u-form-2">
                          <form action="/Profil" method="POST" class="u-clearfix u-form-horizontal u-form-spacing-24 u-inner-form" style="padding: 24px;" source="custom" name="form-1">
                            <div class="u-form-group">
                              <label for="pays-3b9a" class="u-form-control-hidden u-label" wfd-invisible="true"></label>
                              <input placeholder="modifier le pays" id="pays-3b9a" name="pays" class="u-border-2 u-border-no-left u-border-no-right u-border-no-top u-border-palette-5-dark-1 u-input u-input-rectangle u-input-1" required="required" type="text">
                            </div>
                            <div class="u-align-left u-form-group u-form-submit">
                              <input type="submit" value="Enregistrer" class="u-btn u-btn-submit u-button-style u-hover-palette-1-base u-palette-1-dark-2 u-btn-2">
                            </div>
                          </form>
                        </div>

                        <div class="u-expanded-width u-form u-form-3">
                          <form action="/Profil" method="POST" class="u-clearfix u-form-horizontal u-form-spacing-24 u-inner-form" style="padding: 24px;" source="custom" name="form-1">
                            <div class="u-form-group">
                              <label for="province-3b9a" class="u-form-control-hidden u-label" wfd-invisible="true"></label>
                              <input placeholder="modifier le province" id="province-3b9a" name="province" class="u-border-2 u-border-no-left u-border-no-right u-border-no-top u-border-palette-5-dark-1 u-input u-input-rectangle u-input-1" required="required" type="text">
                            </div>
                            <div class="u-align-left u-form-group u-form-submit">
                              <input type="submit" value="Enregistrer" class="u-btn u-btn-submit u-button-style u-hover-palette-1-base u-palette-1-dark-2 u-btn-2">
                            </div>
                          </form>
                        </div>

                        <div class="u-expanded-width u-form u-form-4">
                          <form action="/Profil" method="POST" class="u-clearfix u-form-horizontal u-form-spacing-24 u-inner-form" style="padding: 24px;" source="custom" name="form-1">
                            <div class="u-form-group">
                              <label for="tel-3b9a" class="u-form-control-hidden u-label" wfd-invisible="true"></label>
                              <input placeholder="modifier le téléphone" id="tel-3b9a" name="tel" class="u-border-2 u-border-no-left u-border-no-right u-border-no-top u-border-palette-5-dark-1 u-input u-input-rectangle u-input-1" required="required" type="tel">
                            </div>
                            <div class="u-align-left u-form-group u-form-submit">
                              <input type="submit" value="Enregistrer" class="u-btn u-btn-submit u-button-style u-hover-palette-1-base u-palette-1-dark-2 u-btn-2">
                            </div>
                          </form>
                        </div>

                        <div class="u-expanded-width u-form u-form-5">
                          <form action="/Profil" method="POST" class="u-clearfix u-form-horizontal u-form-spacing-24 u-inner-form" style="padding: 24px;" source="custom" name="form-1">
                            <div class="u-form-group">
                              <label for="adresse-3b9a" class="u-form-control-hidden u-label" wfd-invisible="true"></label>
                              <input placeholder="modifier l'adresse" id="adresse-3b9a" name="adresse" class="u-border-2 u-border-no-left u-border-no-right u-border-no-top u-border-palette-5-dark-1 u-input u-input-rectangle u-input-1" required="required" type="text">
                            </div>
                            <div class="u-align-left u-form-group u-form-submit">
                              <input type="submit" value="Enregistrer" class="u-btn u-btn-submit u-button-style u-hover-palette-1-base u-palette-1-dark-2 u-btn-2">
                            </div>
                          </form>
                        </div>

                      </div>
                    </div>
                  </div>
                </div>
              </div>
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