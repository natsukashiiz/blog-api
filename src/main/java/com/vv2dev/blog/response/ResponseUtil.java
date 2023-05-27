package com.vv2dev.blog.response;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseUtil {

    public static ResponseEntity<?> error(HttpStatus code, String message, HttpServletRequest request) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Map<String, Object> errors = new LinkedHashMap<String, Object>();

        errors.put("status", code.value());
        errors.put("code", code);
        errors.put("message", message);

        String url = request.getScheme()
                + "://"
                + request.getServerName()
                + ":"
                + request.getServerPort()
                + request.getContextPath();
        errors.put("links", url + "/errorcode/" + code.value());

        map.put("errors", errors);

        return ResponseEntity.ok().body(map);
    }

    public static ResponseEntity<?> page(Page page, int totalRecords, Sort.Direction sort, String sortFields, HttpServletRequest request) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Map<String, Object> _meta = new LinkedHashMap<String, Object>();
        Map<String, Object> _links = new LinkedHashMap<String, Object>();
        Map<String, Object> _embedded = new LinkedHashMap<String, Object>();

        map.put("total_records", totalRecords);

        _meta.put("page", page.getNumber() + 1);
        _meta.put("limit", page.getSize());

        String url = request.getRequestURL().toString();

        int pageNumber = page.getNumber() + 1;
        int pageSize = page.getSize();
        int totalPage = page.getTotalPages();

        _links.put("_self", url + "?page=" + (pageNumber) + "&limit=" + pageSize + "&sort=" + sort + "&sortFields=" + sortFields);

        if (totalPage > pageNumber) {
            _links.put("_next", url + "?page=" + (pageNumber + 1) + "&limit=" + pageSize + "&sort=" + sort + "&sortFields=" + sortFields);
        }

        if (pageNumber > 1) {
            _links.put("_prev", url + "?page=" + (pageNumber - 1) + "&limit=" + pageSize + "&sort=" + sort + "&sortFields=" + sortFields);
        }

        _embedded.put("count", page.getContent().toArray().length);
        _embedded.put("data", page.getContent());

        map.put("_meta", _meta);
        map.put("_links", _links);
        map.put("_embedded", _embedded);

        return ResponseEntity.ok(map);
    }

    public static ResponseEntity<?> addedIsOk(Object obj) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("status", HttpStatus.CREATED);
        map.put("code", HttpServletResponse.SC_CREATED);
        map.put("message", "Data added successfully");
        map.put("data", obj);
        return ResponseEntity.ok(map);
    }

    public static ResponseEntity<?> addedIsFail() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("status", HttpStatus.BAD_REQUEST);
        map.put("code", HttpServletResponse.SC_BAD_REQUEST);
        map.put("message", "Failed to add data");
        return ResponseEntity.ok(map);
    }

    public static ResponseEntity<?> deletedIsOk(Long id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("status", HttpStatus.NO_CONTENT);
        map.put("code", HttpServletResponse.SC_NO_CONTENT);
        map.put("message", "Deleted successfully");
        return ResponseEntity.ok(map);
    }

    public static ResponseEntity<?> updatedIsOk(Long id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("status", HttpStatus.OK);
        map.put("code", HttpServletResponse.SC_OK);
        map.put("message", "Updated successfully");
        return ResponseEntity.ok(map);
    }

    public static ResponseEntity<?> notFoundById(Long id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("status", HttpStatus.NOT_FOUND);
        map.put("code", HttpServletResponse.SC_NOT_FOUND);
        map.put("message", String.format("ID %s not found", id));
        return ResponseEntity.badRequest().body(map);
    }
}
