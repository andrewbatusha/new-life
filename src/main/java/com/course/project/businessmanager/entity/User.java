package com.course.project.businessmanager.entity;

import com.course.project.businessmanager.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NamedQuery(
        name = "findEmail",
        query = "select u from User u where u.email= :email"
)
@NamedQuery(
        name = "findToken",
        query = "select u from User u where u.token= :token"
)
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@Table(name = "users")
@JsonIgnoreProperties({"businessList"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, length = 40)
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(length = 20, columnDefinition = "varchar(32) default 'ROLE_OWNER'")
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_OWNER;

    @ManyToMany(mappedBy = "users")
    private List<Business> businessList = new ArrayList<>();

    @JsonIgnore
    private String token;

    @OneToMany(mappedBy = "user")
    private List<Note> notes = new ArrayList<>();
}

