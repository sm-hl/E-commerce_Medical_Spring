package com.miniprojet.miniprojet.Repository;

import java.util.Date;

import com.miniprojet.miniprojet.Model.StatsSite;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StatsSiteRepository extends CrudRepository<StatsSite, Integer>{
    @Query("from StatsSite where date = :date")
    StatsSite recupererStatsAvecDate(@Param("date") Date date);
}
