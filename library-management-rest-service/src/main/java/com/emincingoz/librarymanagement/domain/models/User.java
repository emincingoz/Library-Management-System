package com.emincingoz.librarymanagement.domain.models;

import com.emincingoz.librarymanagement.domain.enums.AccountStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(schema = "rest", name = "lms_user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;

    // Instead of creating a new column address_id, mark the primary key column (user_id)
    // of the lms_address table as the foreign key to the lms_user table
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Size(max = 50)
    @Column(name = "user_name", length = 50)
    private String userName;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private AccountStatusEnum status;

    private String email;

    private String phone;

    @Column(name = "date_of_membership")
    private LocalDateTime dateOfMembership;

    @Column(name = "total_books_checked_out")
    private Long totalBooksCheckedOut;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @Size(max = 256)
    @Column(name = "image_url", length = 256)
    private String imageUrl;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Column(name = "tckimlik", length = 11, unique = true)
    private String tcKimlik;

    @Column(name = "refresh_token_expiration_date")
    private Instant refreshTokenExpirationDate;

    @Column(name = "refresh_token")
    private String refreshToken;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_user_id"), referencedColumnName = "id", name = "user_id")
    private List<UserAuthority> roles;

    public User(String userName, String firstName, String lastName, String tcKimlik, String email, String phone, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tcKimlik = tcKimlik;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
