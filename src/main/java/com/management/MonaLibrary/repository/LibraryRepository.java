package com.management.MonaLibrary.repository;

import com.management.MonaLibrary.Entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library,Long> {
    public Library findByLibraryName(String libraryName);



}
