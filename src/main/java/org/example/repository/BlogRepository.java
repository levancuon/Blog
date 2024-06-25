package org.example.repository;

import org.example.model.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BlogRepository extends CrudRepository<Blog, Long> {
}
