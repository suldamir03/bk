package com.example.demo.service;

import com.example.demo.Repository.IngredientsRepository;
import com.example.demo.entity.Ingredients;
import com.example.demo.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsService {
    @Autowired
    IngredientsRepository ingredientsRepository;

    public void saveAll(List<Ingredients> ingredientsList){
        ingredientsRepository.saveAll(ingredientsList);
    }

    public List<Ingredients> filterByType(Type type) {
        return ingredientsRepository.findByType(type);
    }
}
