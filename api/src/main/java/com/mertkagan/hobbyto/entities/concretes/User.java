package com.mertkagan.hobbyto.entities.concretes;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler" , "likes"})

public class User {

    public User(Long id, String name, String surName, String userName, String email, String password) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.userName = userName;
        this.email = email;
        this.password = password;



    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name" , length = 15 , nullable = false)
    private String name;

    @Column(name = "surname" , length = 15 , nullable = false)
    private String surName;

    @Column(name = "username" , nullable = false)
    private String userName;

    @Column(name = "email" , nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "city_id")
    //@JsonIgnore
    private City city;

    @Column(name = "password" , nullable = false)
    @JsonIgnore
    private String password;
    @Column(name = "profile_pic" , columnDefinition = "varchar(500) ")
    private String profilePic = "defaultProfilePic.jpg";

    @Column(name = "cover_pic" ,columnDefinition = "varchar(500)")
    private String coverPic;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Like> likes;

    @OneToMany(mappedBy = "followerUser")
    @JsonIgnore
    private Set<RelationShip> followerRelationships;

    @OneToMany(mappedBy = "followedUser")
    @JsonIgnore
    private Set<RelationShip> followedRelationships;





}
