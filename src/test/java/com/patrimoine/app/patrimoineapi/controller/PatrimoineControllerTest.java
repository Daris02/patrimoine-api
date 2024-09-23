package com.patrimoine.app.patrimoineapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.patrimoine.app.patrimoineapi.model.Patrimoine;
import com.patrimoine.app.patrimoineapi.service.PatrimoineService;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class PatrimoineControllerTest {

    @InjectMocks
    private PatrimoineController patrimoineController;

    @Mock
    private PatrimoineService patrimoineService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPatrimoineById() {
        Patrimoine patrimoine = new Patrimoine("Owner");
        patrimoine.setId(1);

        when(patrimoineService.getById(anyInt())).thenReturn(patrimoine);

        Patrimoine result = patrimoineController.getPatrimoineById(1);

        assertEquals(patrimoine, result);
    }

    @Test
    public void testCrupdatePatrimoine() {
        Patrimoine patrimoine = new Patrimoine("Owner");
        patrimoine.setId(1);

        when(patrimoineService.crupdate(anyInt(), any(Patrimoine.class))).thenReturn(patrimoine);

        Patrimoine result = patrimoineController.crupdatePatrimoine(1, patrimoine);

        assertEquals(patrimoine, result);
    }
}