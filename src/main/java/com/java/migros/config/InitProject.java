package com.java.migros.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.java.migros.service.CaseProjectService;

@Component
public class InitProject {

	@Autowired
	CaseProjectService service;
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
	   service.initProject();
	}
}
