package com.sketch.productionfeatures.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class PostEntity extends  AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @PrePersist
    void beforeSave(){
        System.out.println("calling before save");
    }

    @PreUpdate
    void beforeUpdate(){
        System.out.println("calling before update");
    }


}
