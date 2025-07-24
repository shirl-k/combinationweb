package com.example.combinationweb2.repository;

import com.example.combinationweb2.entity.Boxing;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface BoxingRepository extends CrudRepository<Boxing,Long> {
    @Override
    ArrayList<Boxing> findAll();
}
