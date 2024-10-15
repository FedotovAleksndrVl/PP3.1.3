package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //              имя колонки таблицы по умолчанию
    private String  name;
    private String  lastName;
    private Byte    age;
    @Column(unique=true)
    private String  login;
    private String  password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private List<Role> roles;

    public User() {

    }

    public User(String login, String password, List<Role> roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }


    @Override
    public String toString() {
        return String.format("%d, %s, %s, %d", getId(), getName(), getLastName(), getAge());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
