package org.example.ejemploh2biblioteca.repositories;

import org.example.ejemploh2biblioteca.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
