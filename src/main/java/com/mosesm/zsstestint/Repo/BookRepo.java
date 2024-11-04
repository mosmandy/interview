package com.mosesm.zsstestint.Repo;

import com.mosesm.zsstestint.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
    public List<Book> findByCategory(long cat);
}
