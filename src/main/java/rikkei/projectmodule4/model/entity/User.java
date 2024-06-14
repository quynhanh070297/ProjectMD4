package rikkei.projectmodule4.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")

public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 100)
    private String fullname;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 255)
    private String avatar;

    @Column(nullable = false, unique = true, length = 15)
    private String phone;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false)
    private Date createdAt = new Date();


    private Date updatedAt;

    @Column(nullable = false)
    private boolean isDeleted = false;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userId"),// Hai Bảng lưu list của những quyền.
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Roles> roles;
}
