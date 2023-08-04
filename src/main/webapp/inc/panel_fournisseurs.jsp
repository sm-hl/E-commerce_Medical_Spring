<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="panel-options d-flex flex-row justify-content-center" style="width: 100%;">
    <c:if test="${active == 'ajouterFournisseurs'}">
    <a class="text-decoration-none mx-1">
        <div class="container py-3 panel-option-active">
    </c:if>
    <c:if test="${active != 'ajouterFournisseurs'}">
    <a href="/Admin/ajouterFournisseurs" class="text-decoration-none">
        <div class="container py-3 panel-option">
    </c:if>
            <span class="fs-5 text-break">Ajouter</span>
        </div>
    </a>
    
    <c:if test="${active == 'supprimerFournisseurs'}">
    <a class="text-decoration-none mx-1">
        <div class="container py-3 panel-option-active">
    </c:if>
    <c:if test="${active != 'supprimerFournisseurs'}">
    <a href="/Admin/supprimerFournisseurs" class="text-decoration-none">
        <div class="container py-3 panel-option">
    </c:if>
            <span class="fs-5 text-break">Supprimer</span>
        </div>
    </a>
</div>