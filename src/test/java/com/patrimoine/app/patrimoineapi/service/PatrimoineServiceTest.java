package com.patrimoine.app.patrimoineapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrimoine.app.patrimoineapi.model.Patrimoine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class PatrimoineServiceTest {

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrupdate() throws IOException {
        Patrimoine patrimoine = new Patrimoine("Owner");
        PatrimoineService patrimoineService = new PatrimoineService("patrimoines-test-crupdate.json");
        patrimoine.setId(1);

        doNothing().when(objectMapper).writeValue(any(File.class), any());

        Patrimoine result = patrimoineService.crupdate(1, patrimoine);

        assertEquals(patrimoine, result);
    }

    @Test
    public void testGetByIdFound() throws IOException {
        Patrimoine patrimoine = new Patrimoine("Owner");
        PatrimoineService patrimoineService = new PatrimoineService("patrimoines-test-found.json");
        patrimoine.setId(1);

        when(objectMapper.readValue(any(byte[].class), eq(Patrimoine[].class)))
                .thenReturn(new Patrimoine[] { patrimoine });
        
        patrimoineService.crupdate(1, patrimoine);

        Patrimoine result = patrimoineService.getById(1);

        assertEquals(patrimoine, result);
    }

    @Test
    public void testCrupdateRemovesOldPatrimoine() {
        PatrimoineService patrimoineService = new PatrimoineService("patrimoines-test-remove-old.json");
        Patrimoine oldPatrimoine = new Patrimoine("Old Owner");
        patrimoineService.crupdate(1, oldPatrimoine);

        Patrimoine newPatrimoine = new Patrimoine("New Owner");
        
        Patrimoine result = patrimoineService.crupdate(1, newPatrimoine);

        assertEquals(newPatrimoine.getPossesseur(), result.getPossesseur());
        
        assertEquals(1, patrimoineService.getAllPatrimoines().size());
    }

    @Test
    public void testGetByIdNotFound() throws IOException {
        PatrimoineService patrimoineService = new PatrimoineService("patrimoines-test-not-found.json");
        
        when(objectMapper.readValue(any(byte[].class), eq(Patrimoine[].class)))
                .thenReturn(new Patrimoine[] {});

        Patrimoine result = patrimoineService.getById(1);

        assertEquals(null, result);
    }
}