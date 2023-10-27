package com.generali.mailservice.mail.entitymanager;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {

  private final IGeneraliMailServiceRepository repository;

  
//  @PostConstruct
//  void save(){
//    repository.findAll();
//  }
}
