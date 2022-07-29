package com.example.cytanbookstore.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@Setter
@ToString
public class User extends IdClass {

    @Column(unique = true)
    private String username;
    private String password;
    private String email;

    private String name;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;


}
