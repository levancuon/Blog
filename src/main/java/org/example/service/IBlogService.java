package org.example.service;

import org.example.model.Blog;

import java.util.List;

public interface IBlogService {
    Iterable<Blog> findAll();
    Blog findById(Long id);
    void save(Blog blog);
    void deleteById(Long id);
}
