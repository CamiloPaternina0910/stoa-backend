package com.backend.stoa.repositories;

import com.backend.stoa.entities.Ingeniero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngenieroRepository extends JpaRepository<Ingeniero, Long> {

}
