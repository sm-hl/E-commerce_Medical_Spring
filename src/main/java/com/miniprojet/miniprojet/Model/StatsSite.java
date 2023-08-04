package com.miniprojet.miniprojet.Model;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class StatsSite {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int nbUsersToday;

    @Temporal(TemporalType.DATE)
    @Column(unique = true, nullable = false)
    private Date date;
}
