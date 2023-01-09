package com.example.readingisgood.repository;

import com.example.readingisgood.model.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStockRepository extends JpaRepository<BookStock,String> {
    BookStock findBookByBookId(String id);
}
