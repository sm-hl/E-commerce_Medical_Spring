package com.miniprojet.miniprojet.Service;

import java.util.*;

import com.miniprojet.miniprojet.Configuration.CustomProperties;
import com.miniprojet.miniprojet.Model.OrderDetail;
import com.miniprojet.miniprojet.Model.Panier;
import com.paypal.api.payments.*;
import com.paypal.base.rest.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServices {
    @Autowired CustomProperties customProperties;

 
    /**
     * Commencer une nouvelle transaction avec Paypal
     * @param orderDetail : Tous les infos nécessaires pour créer une transaction
     * @return Lien au Paypal pour que le client accède au transaction crée
     * @throws PayPalRESTException
     */
    public String authorizePayment(OrderDetail orderDetail) throws PayPalRESTException {

        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(orderDetail);
         
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");
 
        APIContext apiContext = new APIContext(customProperties.getClient_id(), customProperties.getClient_secret(), customProperties.getMode());
        
        Payment approvedPayment = requestPayment.create(apiContext);
 
        return getApprovalLink(approvedPayment);
 
    }

    /**
     * Recuperer les infos du transaction d'après Paypal
     * @param paymentId : ID du transaction qui est généré par Paypal
     * @return <code>Payment</code> qui contient tout les infos sur la transaction
     * @throws PayPalRESTException
     */
    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(customProperties.getClient_id(), customProperties.getClient_secret(), customProperties.getMode());
        return Payment.get(apiContext, paymentId);
    }

    /**
     * Executer la transaction
     * @param paymentId : ID du transaction qui est généré par Paypal
     * @param payerId : ID du compte Paypal de client
     * @return <code>Payment</code> qui contient tout les infos sur la transaction
     * @throws PayPalRESTException
     */
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException 
    {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
    
        Payment payment = new Payment().setId(paymentId);
    
        APIContext apiContext = new APIContext(customProperties.getClient_id(), customProperties.getClient_secret(), customProperties.getMode());
    
        return payment.execute(apiContext, paymentExecution);
    }





    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("William")
                .setLastName("Peterson")
                .setEmail("william.peterson@company.com");
        
        payer.setPayerInfo(payerInfo);
        
        return payer;
    }
     
    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/Panier");
        redirectUrls.setReturnUrl("http://localhost:8080/Panier/review_payment");
        
        return redirectUrls;
    }
     
    private List<Transaction> getTransactionInformation(OrderDetail orderDetail) {    
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(Float.toString(orderDetail.getTotal()));
    
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        String description = "";
        boolean firstOccurence = true;
        for (Panier panier : orderDetail.getPanier()) {
            if(!firstOccurence) description += " + ";
            description += panier.getProduit().getNomProduit();
            firstOccurence = false;
        }
        transaction.setDescription(description);
        
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
        
        for (Panier panier : orderDetail.getPanier()) {
            Item item = new Item();
            item.setCurrency("USD");
            item.setName(panier.getProduit().getNomProduit());
            item.setPrice(Float.toString(panier.getProduit().getPrixProduit() / 10));
            item.setTax(Float.toString(0));
            item.setQuantity(Integer.toString(panier.getNbProduit()));
            items.add(item);
        }
        

        itemList.setItems(items);
        transaction.setItemList(itemList);
    
        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);  
        
        return listTransaction;
    }
     
    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
        
        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }      
        
        return approvalLink;
    }
}