package com.example.demo.service;

import com.example.demo.Repository.BurgerRepository;
import com.example.demo.entity.Burger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BurgerService {
    @Autowired
    BurgerRepository burgerRepository;

    public void saveBurger(Burger burger){
        burgerRepository.save(burger);
    }
    public List<Burger> findAll(){
       return burgerRepository.findAll();
    }
    public Burger findBurger(Long id) {
        return burgerRepository.findBurgerById(id);
    }

}
