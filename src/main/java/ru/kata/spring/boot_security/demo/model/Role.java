package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;
    private String value;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String role, String value) {
        this.id = id;
        this.role = role;
        this.value = value;
    }

    @Override
    public String getAuthority() {
        return getRole();
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
