package com.vv2dev.restfulapi.model;

import lombok.Data;

import java.util.List;

@Data
public class MPageResponse {
    private long total_records;
    private _META _meta;
    private _LINKS _links;
    private _EMBEDDED _embedded;

    @Data
    private static class _META {
        private int page;
        private int limit;
    }

    @Data
    private static class _LINKS {
        private String _self;
        private String _next;
        private String _prev;
    }

    @Data
    private static class _EMBEDDED {
        private int count;
        private List<Object> data;
    }
}
