package com.revature.minimint.authorization.entity.User;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_sequence")
    @Column(nullable = false)
    private long userId;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String username;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, optional = false)
    @JoinColumn(name = "user_password_password_id", nullable = false)
    private Password userPassword;

    public boolean checkPassword(String otherPassword) {
        return this.userPassword.getPassword().equals(otherPassword);
    }
}
