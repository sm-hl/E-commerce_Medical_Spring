package com.miniprojet.miniprojet.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.NoSuchElementException;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.miniprojet.miniprojet.Model.Admin;
import com.miniprojet.miniprojet.Model.Client;
import com.miniprojet.miniprojet.Model.Compte;
import com.miniprojet.miniprojet.Model.Form.ForgotPasswordForm;
import com.miniprojet.miniprojet.Model.Form.LoginUsernameForm;
import com.miniprojet.miniprojet.Model.Form.RegisterCompteForm;
import com.miniprojet.miniprojet.Repository.AdminRepository;
import com.miniprojet.miniprojet.Repository.ClientRepository;
import com.miniprojet.miniprojet.Repository.CompteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import net.bytebuddy.utility.RandomString;

@Service
public class CompteService {
    @Autowired CompteRepository compteRepository;
    @Autowired ClientRepository clientRepository;
    @Autowired AdminRepository adminRepository;
    @Autowired UserDetailsService userDetailsService;
    @Autowired AuthenticationManager authenticationManager;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired JavaMailSender mailSender;

    /**
     * Créer un compte avec son client lié dans la base de donnée
     * Le username et mail doit être unique dans la base de donnée
     * @param compteForm : form qui contient les infos du compte et les infos du personne
     * @param isAdmin : Est ce que ce compte est Admin ou non
     * @return <code>True</code> si l'enregistrement a été effectué avec succès,
     * <code>False</code> sinon.
     */
    public boolean creerCompte(RegisterCompteForm compteForm, boolean isAdmin)
    {
        Compte compte = new Compte();
        compte.setMail(compteForm.getEmail());
        compte.setUsername(compteForm.getUsername());
        compte.setPassword(passwordEncoder.encode(compteForm.getPassword()));

        if(!isAdmin)
        {
            Client client = new Client();
            client.setAdresse(compteForm.getAdresse());
            client.setNomComplet(compteForm.getNomComplet());
            client.setPays(compteForm.getPays());
            client.setProvince(compteForm.getProvince());
            client.setTel(compteForm.getTel());

            try
            {
                compte = compteRepository.save(compte);
            }
            catch(Exception e)
            {
                return false;
            }

            try
            {
                compte.setClient(client);
                client.setCompte(compte);
                client = clientRepository.save(client);
            }
            catch(Exception e)
            {
                compteRepository.deleteById(compte.getId());
                return false;
            }
        }
        if(isAdmin)
        {
            Admin admin = new Admin();
            admin.setAdresse(compteForm.getAdresse());
            admin.setNomComplet(compteForm.getNomComplet());
            admin.setPays(compteForm.getPays());
            admin.setProvince(compteForm.getProvince());
            admin.setTel(compteForm.getTel());
            try
            {
                compte = compteRepository.save(compte);
            }
            catch(Exception e)
            {
                return false;
            }

            try
            {
                compte.setAdmin(admin);
                admin.setCompte(compte);
                admin = adminRepository.save(admin);
            }
            catch(Exception e)
            {
                compteRepository.deleteById(compte.getId());
                return false;
            }
        }
        
        return true;
    }

    /**
     * Connecter au compte en utilisant Username & Password
     * @param form : doit contenir le champ <code>username</code> et <code>password</code>
     * @return <code>True</code> si la connexion a été fait avec succès, <code>False</code> sinon
     */
    public boolean connecterAvecUsername(LoginUsernameForm form) 
    {
        String username = form.getUsername();
        String password = form.getPassword();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            return true;
        }
        return false;
    }

    /**
     * Recuperer <code>Compte</code> connecté
     * @return <code>Compte</code> si vous êtes connecté, <code>null</code> sinon
     */
    public Compte recupererCompteActuel() {
        Compte compte = null;
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof UserDetails) {
            String username = ((UserDetails)userDetails).getUsername();
            compte = compteRepository.chercherAvecUsername(username);
        }
        return compte;
    }

    public boolean nouveauToken(ForgotPasswordForm form, HttpServletRequest request) {
        String email = form.getEmail();
        String token = RandomString.make(30);
        Compte Compte = compteRepository.chercherAvecMail(email);
        if (Compte != null) {
            Compte.setResetPasswordToken(token);
            try {
                compteRepository.save(Compte);
            } catch (Exception e) {
                return false;
            }
            if(!envoyerMailResetPassword(token, email, request))
                return false;
        } else {
            return false;
        }
        return true;
    }

    public boolean envoyerMailResetPassword(String token, String email, HttpServletRequest request)
    {
        String from = "oussamabader@gmail.com";
        String to = email;

        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getServerPort();

        String link = scheme + "://" + host + 
        ((("http".equals(scheme) && port == 80) || ("https".equals(scheme) && port == 443)) ? "" : ":" + port) +
        "/reset_password?token=" + token;
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setSubject("Demande de réinitialisation du mot de passe");
            helper.setFrom(from);
            helper.setTo(to);
            
            String text = "";
            text += "<b>Demande de réinitialisation du mot de passe</b><br>";
            text += "<p><b>Email : </b><i>"+email+"</i></p><br>";
            text += "<p>Pour réinitialiser votre mot de passe, cliquez sur ce lien : ";
            text += "<a href=\"" + link + "\">"+ link +"</a>";
            text += "</p>";
            helper.setText(text, true);
            
            mailSender.send(message);
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
     
    public Compte recupererCompteAvecToken(String token) {
        return compteRepository.chercherAvecToken(token);
    }
     
    public boolean miseAJourPassword(Compte Compte, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        Compte.setPassword(encodedPassword);
         
        Compte.setResetPasswordToken(null);
        try {
            compteRepository.save(Compte);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * <p>Changer les infos d'un compte.</p>
     * <p>Les champs disponibles :</p>
     * <ul>
     * <li><code>email</code></li>
     * <li><code>username</code></li>
     * <li><code>nomComplet</code></li>
     * <li><code>adresse</code></li>
     * <li><code>pays</code></li>
     * <li><code>province</code></li>
     * <li><code>tel</code></li>
     * <li><code>imageUrl</code></li>
     * </ul>
     * @param compte : <code>Compte</code> à changer
     * @param champ : Le nom du champ
     * @param valeur : La nouvelle valeur
     * @return <code>True</code> s'il est changé, <code>False</code> sinon
     */
    public boolean miseAjourCompte(Compte compte, String champ, String valeur)
    {
        Compte compteBD = null;
        try {
            compteBD = compteRepository.findById(compte.getId()).get();
        } catch (NoSuchElementException e) {
            return false;
        }
        
        try {
            switch(champ)
            {
                case "email":
                    compteBD.setMail(valeur);
                    compteRepository.save(compteBD);
                    return true;
                case "username":
                    compteBD.setUsername(valeur);
                    compteRepository.save(compteBD);
                    return true;
                case "nomComplet":
                    if(compteBD.getIsAdmin())
                    {
                        Admin admin = compteBD.getAdmin();
                        admin.setNomComplet(valeur);
                        adminRepository.save(admin);
                        return true;
                    }
                    else
                    {
                        Client Client = compteBD.getClient();
                        Client.setNomComplet(valeur);
                        clientRepository.save(Client);
                        return true;
                    }
                case "adresse":
                    if(compteBD.getIsAdmin())
                    {
                        Admin admin = compteBD.getAdmin();
                        admin.setAdresse(valeur);
                        adminRepository.save(admin);
                        return true;
                    }
                    else
                    {
                        Client Client = compteBD.getClient();
                        Client.setAdresse(valeur);
                        clientRepository.save(Client);
                        return true;
                    }
                case "pays":
                    if(compteBD.getIsAdmin())
                    {
                        Admin admin = compteBD.getAdmin();
                        admin.setPays(valeur);
                        adminRepository.save(admin);
                        return true;
                    }
                    else
                    {
                        Client Client = compteBD.getClient();
                        Client.setPays(valeur);
                        clientRepository.save(Client);
                        return true;
                    }
                case "province":
                    if(compteBD.getIsAdmin())
                    {
                        Admin admin = compteBD.getAdmin();
                        admin.setProvince(valeur);
                        adminRepository.save(admin);
                        return true;
                    }
                    else
                    {
                        Client Client = compteBD.getClient();
                        Client.setProvince(valeur);
                        clientRepository.save(Client);
                        return true;
                    }
                case "tel":
                    if(compteBD.getIsAdmin())
                    {
                        Admin admin = compteBD.getAdmin();
                        admin.setTel(valeur);
                        adminRepository.save(admin);
                        return true;
                    }
                    else
                    {
                        Client Client = compteBD.getClient();
                        Client.setTel(valeur);
                        clientRepository.save(Client);
                        return true;
                    }
                case "imageUrl":
                    if(compteBD.getIsAdmin())
                    {
                        Admin admin = compteBD.getAdmin();
                        admin.setImageUrl(valeur);
                        adminRepository.save(admin);
                        return true;
                    }
                    else
                    {
                        Client Client = compteBD.getClient();
                        Client.setImageUrl(valeur);
                        clientRepository.save(Client);
                        return true;
                    }
                default:
                    return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public void uploadImageToCompte(MultipartFile image, Compte compte)
    {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        try
        {
            saveFile("src/main/webapp/images/", fileName, image);
        }
        catch(IOException e){}

        miseAjourCompte(compte, "imageUrl", "/images/"+fileName);
    }

    private void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
         
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }      
    }
}
