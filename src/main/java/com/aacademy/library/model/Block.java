package com.aacademy.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "blocks")
@Builder
@Getter
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false,unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "blocks_sections",
            joinColumns = @JoinColumn(name = "block_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id"))
    private Set<Section> sections;
}
