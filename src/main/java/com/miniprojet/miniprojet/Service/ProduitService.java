package com.miniprojet.miniprojet.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import com.miniprojet.miniprojet.Model.Fournisseur;
import com.miniprojet.miniprojet.Model.Produit;
import com.miniprojet.miniprojet.Repository.ProduitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProduitService {
    @Autowired ProduitRepository produitRepository;

    /**
     * Ajouter un nouveau produit
     * @param produit
     * @param fournisseur : doit être déjà existe dans la base de donnée
     * @return <code>True</code> si le produit a été ajouté sans erreur,
     * <code>False</code> sinon
     */
    public boolean ajouterProduit(Produit produit, Fournisseur fournisseur)
    {
        produit.setFournisseur(fournisseur);
        try
        {
            produitRepository.save(produit);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * Modifier un produit
     * @param produit
     * @return <code>True</code> si le produit a été modifié sans erreur,
     * <code>False</code> sinon
     */
    public boolean modifierProduit(Produit produit)
    {
        try
        {
            produitRepository.save(produit);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }


    public void supprimerProduit(Produit produit)
    {
        produit.setDeleted(true);
        produitRepository.save(produit);
    }

    /**
     * Recuperer un produit avec son ID
     * @param id
     * @return <code>Produit</code> s'il existe, <code>null</code> sinon
     */
    public Produit recupererProduitAvecId(int id)
    {
        Produit produit = null;
        try
        {
            produit = produitRepository.findById(id).get();
        }
        catch(Exception e) {}

        return produit;
    }

    public Produit recupererProduitAvecNom(String nom)
    {
        Produit produit = null;
        try
        {
            produit = produitRepository.chercherProduitAvecNom(nom, false);
        }
        catch(Exception e) {}

        return produit;
    }

    /**
     * Recuperer des produits dans une catégorie précise
     * @param categorie
     * @param deleted <code>boolean</code> : Est ce que les produits supprimés va être inclus?
     * @return <code>List</code> de type <code>Produit</code>
     */
    public List<Produit> recupererProduitAvecCategorie(String categorie, boolean deleted)
    {
        List<Produit> produits = produitRepository.chercherProduit(categorie, deleted);

        return produits;
    }

    /**
     * Recuperer tous les produits
     * @param deleted <code>boolean</code> : Est ce que les produits supprimés va être inclus?
     * @return <code>List</code> de type <code>Produit</code>
     */
    public List<Produit> recupererTousProduits(boolean deleted)
    {
        Iterable<Produit> result = produitRepository.findAll();
        List<Produit> list = new ArrayList<Produit>();

        for(Produit produit : result)
        {
            if(!deleted)
            {
                if(!produit.isDeleted()) list.add(produit);
            }
            else list.add(produit);
        }

        return list;
    }


    public void uploadImageToProduit(MultipartFile image, Produit produit)
    {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        try
        {
            saveFile("src/main/webapp/images/", fileName, image);
        }
        catch(IOException e){return;}

        produit.setUrlImageProduit("/images/"+fileName);
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
