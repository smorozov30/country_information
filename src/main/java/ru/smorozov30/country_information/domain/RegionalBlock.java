package ru.smorozov30.country_information.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "REGIONAL_BLOCK")
public class RegionalBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String acronym;
    private String name;

    @ElementCollection
    @CollectionTable(
            name = "BLOCK_OTHER_ACRS",
            joinColumns = @JoinColumn(name = "REGIONAL_BLOCK_ID"))
    @Column(name = "OTHER_ACR")
    private Set<String> otherAcronyms = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "BLOCK_OTHER_NAMES",
            joinColumns = @JoinColumn(name = "REGIONAL_BLOCK_ID"))
    @Column(name = "OTHER_NAME")
    private Set<String> otherNames = new HashSet<>();
}
