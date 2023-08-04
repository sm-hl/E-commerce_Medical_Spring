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
     
      <div align="center">
        <h1>Vous êtes sur le point de payer</h1>
        <form action="/Panier/execute_payment" method="post">
        <table>
            <tr>
                <td colspan="2"><b class="fs-2 "><u>Details de transaction:</u></b></td>
                <td>
                    <input type="hidden" name="paymentId" value="${paymentId}" />
                    <input type="hidden" name="PayerID" value="${PayerID}" />
                </td>
            </tr>
            <tr>
                <td>Description:</td>
                <td>${transaction.description}</td>
            </tr>
            <tr>
                <td>Total:</td>
                <td><span class="fw-bold" style="color: red;">${transaction.amount.total } USD</span> (${transaction.amount.total * 10} DH)</td>
            </tr>
            <tr><td><br/></td></tr>
            <tr>
                <td colspan="2"><b class="fs-2"><u>Vos informations:</u></b></td>
            </tr>
            <tr>
                <td>Votre prénom:</td>
                <td>${payer.firstName}</td>
            </tr>
            <tr>
                <td>Votre nom:</td>
                <td>${payer.lastName}</td>
            </tr>
            <tr>
                <td>Email:</td>
                <td>${payer.email}</td>
            </tr>
            <tr><td><br/></td></tr>
            <tr>
                <td colspan="2" align="center">
                    <input class="btn btn-warning" type="submit" value="Je confirme et j'achete" />
                </td>
            </tr>    
        </table>
        </form>
      </div>

     





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