package com.miniprojet.miniprojet.Repository;

import java.util.List;

import com.miniprojet.miniprojet.Model.ProduitAchete;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProduitAcheteRepository extends CrudRepository<ProduitAchete, Integer>{
    /**
     * Recupérer tous les achats dans un delai determiné <code>[aujourd'hui - jours ; aujourd'hui]</code>
     * @param jours
     * @return
     */
    @Query("from ProduitAchete a where FUNCTION('DATEDIFF', CURRENT_DATE, a.dateAchat) <= :jours")
    List<ProduitAchete> recupererAchatsAvecDate(@Param("jours") int jours);

    /**
     * Recupérer tous les achats dans un delai determiné <code>[aujourd'hui - jours1 ; aujourd'hui - jours2]</code>
     * @param jours
     * @return
     */
    @Query("from ProduitAchete a where FUNCTION('DATEDIFF', CURRENT_DATE, a.dateAchat) <= :jours1 and FUNCTION('DATEDIFF', CURRENT_DATE, a.dateAchat) >= :jours2")
    List<ProduitAchete> recupererAchatsAvecIntervalle(@Param("jours1") int jours1, @Param("jours2") int jours2);
}
