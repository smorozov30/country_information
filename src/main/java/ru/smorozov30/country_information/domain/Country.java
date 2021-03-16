package ru.smorozov30.country_information.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "COUNTRY")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    private String name;
    private int population;
    private String flag;

    @ElementCollection
    @CollectionTable(
            name = "DOMAINS",
            joinColumns = @JoinColumn(name = "COUNTRY_ID"))
    @Column(name = "DOMAIN")
    private Set<String> topLevelDomain = new HashSet<>();


    @ElementCollection
    @CollectionTable(
            name = "LATLNGS",
            joinColumns = @JoinColumn(name = "COUNTRY_ID"))
    @Column(name = "LATLNG")
    private List<String> latlng = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "COUNTRY_CURRENCY",
            joinColumns = @JoinColumn(name = "COUNTRY_ID"),
            inverseJoinColumns = @JoinColumn(name = "CURRENCY_ID"))
    private Set<Currency> currencies = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "COUNTRY_LANGUAGE",
            joinColumns = @JoinColumn(name = "COUNTRY_ID"),
            inverseJoinColumns = @JoinColumn(name = "LANGUAGE_ID"))
    private Set<Language> languages = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Translation translations;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "COUNTRY_BLOCK",
            joinColumns = @JoinColumn(name = "COUNTRY_ID"),
            inverseJoinColumns = @JoinColumn(name = "BLOCK_ID"))
    private Set<RegionalBlock> regionalBlocs = new HashSet<>();
}
