package com.miniprojet.miniprojet.Repository;

import com.miniprojet.miniprojet.Model.Compte;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CompteRepository extends CrudRepository<Compte, Integer>{
    @Query("from Compte where mail = :mail and password = :password")
    Compte connecterAvecMail(@Param("mail") String mail, @Param("password") String password);

    @Query("from Compte where username = :username and password = :password")
    Compte connecterAvecUsername(@Param("username") String username, @Param("password") String password);

    @Query("from Compte where username = :username")
    Compte chercherAvecUsername(@Param("username") String username);

    @Query("from Compte where mail = :mail")
    public Compte chercherAvecMail(@Param("mail") String email);

    @Query("from Compte where resetPasswordToken = :token")
    public Compte chercherAvecToken(@Param("token") String token);
}
