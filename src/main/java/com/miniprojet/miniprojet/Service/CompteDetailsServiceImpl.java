package com.miniprojet.miniprojet.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.miniprojet.miniprojet.Model.Compte;
import com.miniprojet.miniprojet.Repository.CompteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CompteDetailsServiceImpl implements UserDetailsService {
   @Autowired MessagesService messagesService;
   private CompteRepository CompteRepository;

   public CompteDetailsServiceImpl(CompteRepository CompteRepository) {
      this.CompteRepository = CompteRepository;
   }

   @Override
   public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
      Compte Compte = CompteRepository.chercherAvecUsername(userName);
      if (Compte == null) {
         throw new UsernameNotFoundException(messagesService.getMessage("account_not_found"));
      }
      return new org.springframework.security.core.userdetails.User(Compte.getUsername(),
            Compte.getPassword(),
            mapRolesToAuthorities(Compte.getIsAdmin()));
   }
   
   /**
    * <p>Donner les roles de chaque compte qui est en train de connecter</p>
    * <p>Les rôles disponibles sont: <code>Client</code> et <code>Admin</code></p>
    * @param isAdmin : Est ce que le compte est Admin ou non?
    * @return <code>Collection</code> qui contient les rôles associés du compte
    */
   private Collection<? extends GrantedAuthority> mapRolesToAuthorities(boolean isAdmin) {
      List<String> roles = new ArrayList<String>();
      roles.add("Client");
      if (isAdmin) roles.add("Admin");
      return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role))
            .collect(Collectors.toList());
   }
}