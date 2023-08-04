package com.miniprojet.miniprojet.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.miniprojet.miniprojet.Model.OrderDetail;
import com.miniprojet.miniprojet.Model.Panier;
import com.miniprojet.miniprojet.Model.Produit;
import com.miniprojet.miniprojet.Service.PaymentServices;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("paypal")
public class OrderController {
    @Autowired PaymentServices paymentServices;

    @GetMapping()
    public String checkout()
    {
        return "checkout";
    }

    @PostMapping(path="/achat")
    public String authorize_payment(HttpServletRequest request, final Model model)
    {
        OrderDetail orderDetail = new OrderDetail();


        Panier panier1 = new Panier();
        Produit produit1 = new Produit();
        produit1.setNomProduit("Produit1");
        produit1.setPrixProduit(20);
        panier1.setProduit(produit1);
        panier1.setNbProduit(10);

        Panier panier2 = new Panier();
        Produit produit2 = new Produit();
        produit2.setNomProduit("Produit2");
        produit2.setPrixProduit(15);
        panier2.setProduit(produit2);
        panier2.setNbProduit(2);
        
        List<Panier> paniers = new ArrayList<Panier>();
        paniers.add(panier1);
        paniers.add(panier2);

        orderDetail.setPanier(paniers);
        orderDetail.setTotal(230);
        
        try {
            String approvalLink = paymentServices.authorizePayment(orderDetail);
 
            return "redirect:"+approvalLink;
             
        } catch (PayPalRESTException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            return "error";
        }

        //return "register";
    }

    @GetMapping(path="/review_payment")
    public String review_payment(@Param("paymentId") String paymentId, @Param("PayerID") String PayerID, final Model model)
    {     
        try {
            Payment payment = paymentServices.getPaymentDetails(paymentId);
             
            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);
            ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
             
            model.addAttribute("payer", payerInfo);
            model.addAttribute("transaction", transaction);
            model.addAttribute("shippingAddress", shippingAddress);
            model.addAttribute("paymentId", paymentId);
            model.addAttribute("PayerID", PayerID);

            return "review";
             
        } catch (PayPalRESTException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            return "error";
        }
    }

    @PostMapping(path="/execute_payment")
    public String execute_payment(@Param("paymentId") String paymentId, @Param("PayerID") String PayerID, final Model model)
    {     
        try {
            Payment payment = paymentServices.executePayment(paymentId, PayerID);
             
            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);
             
            model.addAttribute("payer", payerInfo);
            model.addAttribute("transaction", transaction);    
 
            return "receipt";
             
        } catch (PayPalRESTException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            return "error";
        }
    }
}
