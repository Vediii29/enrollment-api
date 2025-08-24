package com.example.enrollment_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_role",discriminatorType = DiscriminatorType.STRING)
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @Column(nullable = false)
    private String firstName;

     @Column(nullable = false)
    private String lastName;

     @Column(nullable = false, unique = true)
    private String email;

     @Column(nullable = false)
    private String password;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "organization_id")
    private Organization organization;

     @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Enrollment> enrollmentList;

     @Column(name = "user_role", insertable = false, updatable = false)
     private String role;
}
