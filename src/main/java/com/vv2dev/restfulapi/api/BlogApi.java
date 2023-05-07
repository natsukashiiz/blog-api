package com.vv2dev.restfulapi.api;

import com.vv2dev.restfulapi.business.BlogBusiness;
import com.vv2dev.restfulapi.entity.Blog;
import com.vv2dev.restfulapi.exception.BaseException;
import com.vv2dev.restfulapi.model.MBlogRequest;
import com.vv2dev.restfulapi.model.MBlogResponse;
import com.vv2dev.restfulapi.repository.BlogRepository;
import com.vv2dev.restfulapi.response.ResponseUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogApi {
    private final BlogRepository repository;
    private final BlogBusiness business;

    public BlogApi(BlogRepository repository, BlogBusiness business) {
        this.repository = repository;
        this.business = business;
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sort,
            @RequestParam(required = false, defaultValue = "id") String sortFields,
            HttpServletRequest request) {

        int total = repository.findAll().size();
        Page<Blog> data = repository.findAll(PageRequest.of(page - 1, limit, Sort.by(sort, sortFields)));
        return ResponseUtil.page(data, total, sort, sortFields, request);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws BaseException {
        MBlogResponse response = business.getById(id);
        return ResponseEntity.ok(response);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MBlogRequest request) throws BaseException {
        MBlogResponse response = business.create(request);
        return ResponseEntity.ok(response);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<?> editAllById(@PathVariable Long id, @RequestBody MBlogRequest request) throws BaseException {
        MBlogResponse response = business.updateById(id, request);
        return ResponseEntity.ok(response);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/{id}")
    public ResponseEntity<?> editById(@PathVariable Long id, @RequestBody MBlogRequest request) throws BaseException {
        MBlogResponse response = business.updateById(id, request);
        return ResponseEntity.ok(response);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws BaseException {
        business.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
