package com.patrimoine.app.patrimoineapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrimoine.app.patrimoineapi.model.Patrimoine;

@Service
public class PatrimoineService {
    private String filePath;
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Patrimoine> patrimoines = new ArrayList<>();

    public PatrimoineService(@Value("${patrimoine.file.path}") String filePath) {
        this.filePath = filePath;
        loadPatrimoines();
    }

    public List<Patrimoine> getAllPatrimoines() {
        loadPatrimoines();
        return patrimoines;
    }

    public Patrimoine getById(int id) {
        if (patrimoines.isEmpty()) return null;
        return patrimoines.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Patrimoine crupdate(int id, Patrimoine patrimoine) {
        patrimoines.removeIf(p -> p.getId() == id);
        patrimoine.updateDernierModification();
        if (patrimoines.isEmpty()) patrimoine.setId(1);
        else {
            int maxId = patrimoines.stream().mapToInt(Patrimoine::getId).max().orElse(0);
            patrimoine.setId(maxId + 1);
        }
        patrimoines.add(patrimoine);
        savePatrimoines();
        return patrimoine;
    }

    private void savePatrimoines() {
        try {
            objectMapper.writeValue(Paths.get(filePath).toFile(), patrimoines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPatrimoines() {
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                Patrimoine[] loadedPatrimoines = objectMapper.readValue(Files.readAllBytes(path), Patrimoine[].class);
                patrimoines.clear();
                for (Patrimoine p : loadedPatrimoines) {
                    patrimoines.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
