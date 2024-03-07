package com.dpdtest.dpdtestapp.repositories;

import com.dpdtest.dpdtestapp.entities.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p WHERE p.id >= :startId ORDER BY p.id ASC")
    List<Person> getListFromId(@Param("startId") Long startId, Pageable pageable);
}

