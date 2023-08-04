<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="u-black u-clearfix u-header" id="sec-8099"><div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
    
    <a href="/" class="pimed-logo u-image u-logo u-image-1 u-logo-image u-logo-image-1" >
      <span style="color: brown;">PI</span><span style="color: darkgray;">MED</span> 
      </a>
<c:if test="${user != null}">
        <nav class="u-align-left u-dropdown-icon u-menu u-menu-dropdown u-nav-spacing-25 u-offcanvas u-menu-1" data-responsive-from="XL">
          <div class="menu-collapse">
            <a class="u-button-style u-nav-link" href="#" style="font-size: calc(1em + 8px); color: rgb(17, 17, 17) !important;">
              <img src="${ personne.getImageUrl() }" alt="mdo" width="32" height="32" class="rounded-circle"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#svg-c6da"></use></img>
            </a>
          </div>
          <div class="u-custom-menu u-nav-container">
            <ul class="u-nav u-unstyled u-nav-1"><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-black u-text-hover-black" href="/Profil" style="padding: 10px 20px;">Profil</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-black u-text-hover-black" href="/logout" style="padding: 10px 20px;">Deconnexion</a>

</li></ul>
          </div>
          <div class="u-custom-menu u-nav-container-collapse">
            <div class="u-align-center u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
              <div class="u-inner-container-layout u-sidenav-overflow">
                <div class="u-menu-close"></div>
                <ul class="u-align-left u-nav u-popupmenu-items u-text-hover-custom-color-5 u-unstyled u-nav-2"><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/Profil" style="padding: 10px 20px;">Profil</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/logout" style="padding: 10px 20px;">Deconnexion</a>
</li></ul>
              </div>
            </div>
            <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
          </div>
        </nav>
</c:if>
        <nav class="u-menu u-menu-dropdown u-offcanvas u-menu-2">
          <form action="Produits" method="get">
          <div class="u-nav-container">
            
            <span style=" margin-right:30px;"> <input style="background-color: rgb(61 77 90); width:200px;" type="text" name="q" value="" placeholder="Chercher....">
               <input style="background-color:  rgb(61 77 90) ; color:white; padding: 4px;" type="submit" value="Chercher" required>
            </span>
            
            <ul class="u-nav u-spacing-20 u-unstyled u-nav-3"><li class="u-nav-item"><a class="u-border-active-grey-90 u-border-hover-grey-50 u-border-no-left u-border-no-right u-border-no-top u-button-style u-nav-link u-text-active-white u-text-hover-white" href="/" style="padding: 10px; color: rgb(146, 207, 236);">Accueil</a>
</li><li class="u-nav-item"><a class="u-border-active-grey-90 u-border-hover-grey-50 u-border-no-left u-border-no-right u-border-no-top u-button-style u-nav-link u-text-active-white u-text-hover-white" href="/Produits" style="padding: 10px; color: rgb(146, 207, 236);">Produits</a>
<c:if test="${user.getIsAdmin()}">
</li><li class="u-nav-item"><a class="u-border-active-grey-90 u-border-hover-grey-50 u-border-no-left u-border-no-right u-border-no-top u-button-style u-nav-link u-text-active-white u-text-hover-white" href="/Admin" style="padding: 10px; color: rgb(146, 207, 236);">Admin</a>
</c:if>
</li><li class="u-nav-item"><a href="/Panier"> <i  class="fas fa-shopping-cart cart"></i>  </a>
</li>
<c:if test="${user == null}">
<li class="u-nav-item"><a href="/register" class="me-2"><button type="button" class="btn btn-warning">Créer compte</button></a> 
</li><li class="u-nav-item"><a href="/login" class="me-2"><button type="button" class="btn btn-outline-light">Connexion</button></a>
</li>
</c:if>
</ul>



          
          <div class="u-nav-container-collapse">
            <div class="u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
              <div class="u-inner-container-layout u-sidenav-overflow">
                <div class="u-menu-close"></div>
                <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-4"><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/">Accueil</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/About">About</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/Produits">Produits</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="sinscrire.html">Créer compte</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="se-connecter.html">Se connecter</a>
</li></ul>
              </div>
            </div>
            <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
          </div>
          </form>
        </nav>
      </div></header>