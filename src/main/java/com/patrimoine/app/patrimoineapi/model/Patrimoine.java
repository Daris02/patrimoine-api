package com.patrimoine.app.patrimoineapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patrimoine {
    private int id;
    private String possesseur;
    private LocalDateTime derniereModification;

    public Patrimoine(String possesseur) {
        this.possesseur = possesseur;
        this.derniereModification = LocalDateTime.now();
    }
}
