package com.vv2dev.restfulapi.exception;

public class UserException extends BaseException {
    public UserException(String code) {
        super("user."+code);
    }
    public static UserException requestNull() {
        return new UserException("request.null");
    }
    public static UserException createUsernameNull() {
        return new UserException("create.username.null");
    }

    public static UserException createEmailNull() {
        return new UserException("create.email.null");
    }
    public static UserException createNameNull() {
        return new UserException("create.name.null");
    }
    public static UserException createPasswordNull() {
        return new UserException("create.password.null");
    }

    public static BaseException createEmailDuplicate() {
        return new UserException("create.email.duplicate");
    }
    public static BaseException createUsernameDuplicate() {
        return new UserException("create.username.duplicate");
    }
    public static BaseException loginFailEmailNotFound() {
        return new UserException("login.fail");
    }
    public static BaseException loginFailPasswordIncorrect() {
        return new UserException("login.fail");
    }
    public static BaseException notFound() {
        return new UserException("not.found");
    }

    public static BaseException deleteFail() {
        return new UserException("delete.fail");
    }
    public static BaseException unauthorized() {
        return new UserException("unauthorized");
    }

    public static BaseException idNull() {
        return new UserException("id.null");
    }
}
