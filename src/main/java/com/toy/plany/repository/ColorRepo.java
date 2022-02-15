package com.toy.plany.repository;

import com.toy.plany.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepo extends JpaRepository<Color, Long> {
    @Query("SELECT c FROM Color c WHERE c.isUsed = 0")
    List<Color> findAllColorUnused();
}
