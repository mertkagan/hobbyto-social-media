package com.mertkagan.hobbyto.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="relationships")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RelationShip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "followed_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User followedUser;
    @ManyToOne
    @JoinColumn(name = "follower_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User followerUser;
}
