package com.java.migros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.migros.repository.CaseProjectRepository;

@Service
public class CaseProjectService {

	@Autowired
	private CaseProjectRepository repository;
	
	public void initProject() {
		repository.initProject();
	}
}
