package com.mosesm.zsstestint.Repo;

import com.mosesm.zsstestint.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
