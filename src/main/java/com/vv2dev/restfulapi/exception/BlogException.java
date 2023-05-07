package com.vv2dev.restfulapi.exception;

public class BlogException extends BaseException {
    public BlogException(String code) {
        super("blog."+code);
    }
    public static BlogException requestNull() {
        return new BlogException("request.null");
    }

    public static BlogException createTitleNull() {
        return new BlogException("create.title.null");
    }
    public static BlogException createContentNull() {
        return new BlogException("create.content.null");
    }

    public static BaseException notFound() {
        return new BlogException("not.found");
    }

    public static BaseException deleteFail() {
        return new BlogException("delete.fail");
    }
    public static BaseException unauthorized() {
        return new BlogException("unauthorized");
    }

    public static BaseException idNull() {
        return new BlogException("id.null");
    }
}
