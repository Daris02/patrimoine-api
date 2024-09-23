package com.patrimoine.app.patrimoineapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrimoine.app.patrimoineapi.model.Patrimoine;

@Service
public class PatrimoineService {
    private static final String FILE_PATH = "patrimoines.json";
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Patrimoine> patrimoines = new ArrayList<>();


    public PatrimoineService() {
        loadPatrimoines();
    }

    public Patrimoine getById(int id) {
        return patrimoines.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Patrimoine crupdate(int id, Patrimoine patrimoine) {
        if (getById(id) != null) {
            patrimoines.removeIf(p -> p.getId() == id);
            patrimoine.setId(id);
            patrimoine.setDerniereModification();
            patrimoines.add(patrimoine);
        } else {
            Patrimoine newPatrimoine = new Patrimoine(patrimoine.getPossesseur());
            patrimoines.add(newPatrimoine);
            savePatrimoines();
            return newPatrimoine;
        }
        savePatrimoines();
        return patrimoine;
    }

    private void savePatrimoines() {
        try {
            objectMapper.writeValue(Paths.get(FILE_PATH).toFile(), patrimoines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPatrimoines() {
        try {
            Path path = Paths.get(FILE_PATH);
            if (Files.exists(path)) {
                Patrimoine[] loadedPatrimoines = objectMapper.readValue(Files.readAllBytes(path), Patrimoine[].class);
                for (Patrimoine p : loadedPatrimoines) {
                    patrimoines.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
