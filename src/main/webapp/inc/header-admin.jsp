<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="px-3 bg-dark bg-gradient text-white">
    <div class="container">
        <div class="d-flex flex-wrap justify-content-around">

            <a href="/" class="text-decoration-none text-white mb-0">
                <p class="text-center fs-2 align-middle mb-0">PIMED</p>
            </a>
            <div class="d-flex flex-row justify-content-start">
                <c:if test="${activePanel == 'stats du site'}">
                <a class="px-3 mx-1 text-decoration-none panel py-4 active" href="/Admin/stats">
                </c:if>
                <c:if test="${activePanel != 'stats du site'}">
                <a class="px-3 mx-1 text-decoration-none panel py-4 nonactive" href="/Admin/stats">
                </c:if>
                    <span class="align-middle">
                        Stats du site
                    </span>
                </a>

                <c:if test="${activePanel == 'gestion des produits'}">
                <a class="px-3 mx-1 text-decoration-none panel py-4 active" href="/Admin/ajouterProduits">
                </c:if>
                <c:if test="${activePanel != 'gestion des produits'}">
                <a class="px-3 mx-1 text-decoration-none panel py-4 nonactive" href="/Admin/ajouterProduits">
                </c:if>
                    <span class="align-middle">
                        Gestion des produits
                    </span>
                </a>

                <c:if test="${activePanel == 'gestion des fournisseurs'}">
                <a class="px-3 mx-1 text-decoration-none panel py-4 active" href="/Admin/ajouterFournisseurs">
                </c:if>
                <c:if test="${activePanel != 'gestion des fournisseurs'}">
                <a class="px-3 mx-1 text-decoration-none panel py-4 nonactive" href="/Admin/ajouterFournisseurs">
                </c:if>
                    <span class="align-middle">
                        Gestion des fournisseurs
                    </span>
                </a>

                <c:if test="${activePanel == 'gestion des Stocks'}">
                <a class="px-3 mx-1 text-decoration-none panel py-4 active" href="/Admin/ajouterStocks">
                </c:if>
                <c:if test="${activePanel != 'gestion des Stocks'}">
                <a class="px-3 mx-1 text-decoration-none panel py-4 nonactive" href="/Admin/ajouterStocks">
                </c:if>
                    <span class="align-middle">
                        Gestion du stock
                    </span>
                </a>
            </div>
            <div>
              <span> <span>
            </div>
        </div>
    </div>
</header>