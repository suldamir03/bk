package com.example.demo.Repository;

import com.example.demo.entity.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BurgerRepository extends JpaRepository<Burger, Long> {
    Burger findBurgerById(Long id);
}
