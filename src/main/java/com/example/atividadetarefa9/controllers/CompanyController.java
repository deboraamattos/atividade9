package com.example.atividadetarefa9.controllers;

import com.example.atividadetarefa9.model.Company;
import com.example.atividadetarefa9.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping({"/companies"})
public class CompanyController {
	@Autowired
	private CompanyRepository repository;

	@GetMapping
	public List findAll(){
		return repository.findAll();
	}

	@PostMapping
	public Company create(@RequestBody Company company){
		company.setDate(LocalDateTime.now());
		return repository.save(company);
	}

	@GetMapping(path = {"/{id}"})
	public ResponseEntity findById(@PathVariable long id){
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
}