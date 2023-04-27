package com.example.TextGrabber.repository;

import com.example.TextGrabber.model.TextData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextDataRepository extends JpaRepository<TextData, Long> {
}
