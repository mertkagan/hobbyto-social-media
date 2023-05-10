package com.mertkagan.hobbyto.entities.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler" , "likes","comments","attendees"})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="creation_date" , columnDefinition = "TIMESTAMP ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime creationDate;

    @Column(name="title" , columnDefinition = "varchar(25)")
    private String title;

    @Column(name="event_date" , columnDefinition = "DATE")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate eventDate;

    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name="text")
    private String text;

    @Column(name = "post_img",columnDefinition = "varchar(500)")
    private String img;

    @ManyToOne()
    @JoinColumn(name = "user_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;


    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> comments;

    @OneToMany(mappedBy = "post" )
    private List<Attendee> attendees;

    @OneToMany(mappedBy = "post" )
    private List<Like> likes;



}
