package com.course.project.businessmanager.entity;

import com.course.project.businessmanager.utils.EntityIdResolver;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NamedQuery(
        name = "findBuildingByEmail",
        query = "select b from Building b " +
                "join b.user u where u.email= :email"
)
@Entity
@NoArgsConstructor
@Data
@Table(name = "buildings")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        resolver = EntityIdResolver.class,
        scope=Building.class)
@ToString(exclude={"business", "equipments", "ledgers", "warehouses"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "business"})
public class Building implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 35, nullable = false)
    private String name;

    private String geolocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @OneToMany(mappedBy = "building")
    private List<Equipment> equipments = new ArrayList<>();

    @OneToMany(mappedBy = "building")
    private List<Ledger> ledgers = new ArrayList<>();

    @OneToMany(mappedBy = "building")
    private List<Warehouse> warehouses = new ArrayList<>();

    @OneToMany(mappedBy = "building")
    private List<Employee> employees = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
