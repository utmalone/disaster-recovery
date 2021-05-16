package com.disasterrecovery.repository;

import com.disasterrecovery.model.Jobs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepo extends JpaRepository<Jobs, Long> {
}
