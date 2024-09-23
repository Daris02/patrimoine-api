package com.patrimoine.app.patrimoineapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrimoine.app.patrimoineapi.model.Patrimoine;
import com.patrimoine.app.patrimoineapi.service.PatrimoineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/patrimoines")
public class PatrimoineController {
  @Autowired
  private PatrimoineService service;

  @GetMapping("/{id}")
  public Patrimoine getPatrimoineById(@PathVariable int id) {
      return service.getById(id);
  }

  @PutMapping("/{id}")
  public Patrimoine crupdatePatrimoine(@PathVariable int id, @RequestBody Patrimoine patrimoine) {
      return service.crupdate(id, patrimoine);
  }
}
