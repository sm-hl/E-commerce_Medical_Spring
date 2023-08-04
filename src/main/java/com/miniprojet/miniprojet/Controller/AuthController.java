package com.miniprojet.miniprojet.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.miniprojet.miniprojet.Model.Compte;
import com.miniprojet.miniprojet.Model.Form.ForgotPasswordForm;
import com.miniprojet.miniprojet.Model.Form.LoginUsernameForm;
import com.miniprojet.miniprojet.Model.Form.RegisterCompteForm;
import com.miniprojet.miniprojet.Model.Form.ResetPasswordForm;
import com.miniprojet.miniprojet.Service.CompteService;
import com.miniprojet.miniprojet.Service.MessagesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AuthController {
    @Autowired CompteService compteService;
    @Autowired MessagesService messagesService;

    @GetMapping(path="login")
    public String formLogin()
    {
        return "login";
    }

    @PostMapping(path="login")
    public String Login(final @Valid LoginUsernameForm form, final BindingResult bindingResult, final Model model)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "login";
        }

        if(!compteService.connecterAvecUsername(form))
        {
            model.addAttribute("error", messagesService.getMessage("username_password_incorrect"));
            return "login";
        } 

        return "redirect:/";
    }

    @GetMapping(path="register")
    public String formRegister()
    {
        return "register";
    }

    @PostMapping(path="register")
    public String Register(final @Valid  RegisterCompteForm compteForm, final BindingResult bindingResult, final Model model)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "register";
        }

        if(!compteService.creerCompte(compteForm, false)) 
        {
            model.addAttribute("error", messagesService.getMessage("account_duplicate"));
            return "register";
        }

        model.addAttribute("success", true);

        return "register";
    }

    @GetMapping(path = "forgot_password")
    public String forgotPasswordForm()
    {
        return "forgotPassword";
    }
    
    @PostMapping(path = "forgot_password")
    public String forgotPassword(final @Valid  ForgotPasswordForm form, final BindingResult bindingResult, final Model model, HttpServletRequest request)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "forgotPassword";
        }

        if(!compteService.nouveauToken(form, request))
        {
            model.addAttribute("error", messagesService.getMessage("account_not_found"));
            return "forgotPassword";
        }

        model.addAttribute("success", true); 

        return "forgotPassword";
    }

    @GetMapping(path = "reset_password")
    public String resetPasswordForm(@Param(value = "token") String token, Model model)
    {
        if(compteService.recupererCompteAvecToken(token) == null) return "tokenInvalid";

        model.addAttribute("token", token);
        return "resetPassword";
    }

    @PostMapping(path = "reset_password")
    public String resetPassword(final @Valid  ResetPasswordForm form, final BindingResult bindingResult, final Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "resetPassword";
        }

        Compte compte = compteService.recupererCompteAvecToken(form.getToken());
        if(compte == null)
        {
            return "redirect:/";
        }

        if(!compteService.miseAJourPassword(compte, form.getPassword()))
        {
            model.addAttribute("error", messagesService.getMessage("password_not_changed"));
            return "resetPassword";
        }

        model.addAttribute("success", true);

        return "resetPassword";
    }
}
