package com.mak.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mak.entity.Anuj;
import com.mak.repo.AnujRepo;

@Service
public class AnujService {
@Autowired
private AnujRepo objRepo;

public void setData() {
    Anuj s1 = new Anuj(null, "amanaaa", "Krmauryaaman@gmail.com", "Dehradun");
    Anuj s2 = new Anuj(null, "Anujbbb", "Amanuj98@gmail.com", "Meerut");
    Anuj s3 = new Anuj(null, "Ankitccc", "Ankitj98@gmail.com", "OakLand");
    
    List<Anuj> list = Arrays.asList(s1, s2, s3);
    List<Anuj> saveAll=objRepo.saveAll(list);
}

	
}
