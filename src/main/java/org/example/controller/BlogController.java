package org.example.controller;

import org.example.model.Blog;
import org.example.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public String listBlogs(Model model) {
        Iterable<Blog> blogs = blogService.findAll();
        model.addAttribute("blog", blogs);
        return "/list";
    }

    @GetMapping("/new")
    public String showNewBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "/new";
    }

    @PostMapping("/new")
    public String createBlog(@ModelAttribute Blog blog) {
        blog.setCreatedAt(LocalDateTime.now());
        blogService.save(blog);
        return "redirect:/blog";
    }

    @GetMapping("/view/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog",blog );
        return "/view";
    }

    @GetMapping("/{id}/edit")
    public String showEditBlogForm(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.findById(id));
        return "/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateBlog(@PathVariable Long id, @ModelAttribute Blog blog) {
        Blog existingBlog = blogService.findById(id);
        if (existingBlog != null) {
            existingBlog.setTitle(blog.getTitle());
            existingBlog.setContent(blog.getContent());
            existingBlog.setUpdatedAt(LocalDateTime.now());
            blogService.save(existingBlog);
        }
        return "redirect:/blog";
    }

    @GetMapping("/{id}/delete")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteById(id);
        return "redirect:/blog";
    }
}
