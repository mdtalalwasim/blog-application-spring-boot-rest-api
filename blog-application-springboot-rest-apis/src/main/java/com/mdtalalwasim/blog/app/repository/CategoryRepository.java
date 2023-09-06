package com.mdtalalwasim.blog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdtalalwasim.blog.app.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
