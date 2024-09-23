package com.patrimoine.app.patrimoineapi.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Patrimoine {
    private int id;
    private String possesseur;
    private String derniereModification;

    public Patrimoine(String possesseur) {
        this.possesseur = possesseur;
        this.derniereModification = formatDate(LocalDateTime.now());
    }

    private String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm");
        return dateTime.format(formatter);
    }

    public void updateDernierModification() {
        this.derniereModification = formatDate(LocalDateTime.now());
    }
}
