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
  <body onload="" class="u-body u-overlap u-overlap-contrast u-stick-footer">
          
          
          <main>
          <c:import url="/inc/header-admin.jsp"/>
            <div>
                <div class="text-center my-5">
                    <span class="fs-2">Résultat analytique totale : <span class="text-primary fw-bold">${resultatTotal} DH</span></span>
                </div>
                <div class="d-flex flex-row justify-content-center">
                    <div id="chartMois" style="height: 300px; width: 75%;"></div>
                </div>
                <div class="d-flex flex-row justify-content-center mt-5">
                    <div id="chartJour" style="height: 300px; width: 75%;"></div>
                </div>
            </div>
        </main>
        <script src="/bootstrap.min.js"></script>
        <script src="/bootstrap.bundle.min.js"></script>
        <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
        <script>
            window.onload = function () {

            var chart = new CanvasJS.Chart("chartMois", {
                animationEnabled: true,
                exportEnabled: true,
                theme: "dark2", // "light1", "light2", "dark1", "dark2"
                title:{
                    text: "Résultat analytique des mois précédents"
                },
                axisY: {
                    suffix: " DH",
                    includeZero: true
                },
                data: [{
                    type: "column", //change type to bar, line, area, pie, etc
                    indexLabelFontColor: "#5A5757",
                    indexLabelFontSize: 16,
                    indexLabelPlacement: "outside",
                    dataPoints: [
                        <c:forEach items="${resultatMois}" var="resultat">
                        { label: '${resultat.key}', y: ${resultat.value} },
                        </c:forEach>
                    ]
                }]
            });

            var chart2 = new CanvasJS.Chart("chartJour", {
                animationEnabled: true,
                exportEnabled: true,
                theme: "dark2", // "light1", "light2", "dark1", "dark2"
                title:{
                    text: "Résultat analytique des jours précédents"
                },
                axisY: {
                    suffix: " DH",
                    includeZero: true
                },
                data: [{
                    type: "spline", //change type to bar, line, area, pie, etc
                    indexLabelFontColor: "#5A5757",
                    indexLabelFontSize: 16,
                    indexLabelPlacement: "outside",
                    dataPoints: [
                        <c:forEach items="${resultatMois}" var="resultat">
                        { label: '${resultat.key}', y: ${resultat.value} },
                        </c:forEach>
                    ]
                }]
            });

            chart.render();
            chart2.render();

            }
        </script>
     





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
  </body>
</html>