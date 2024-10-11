package core.controllers.utils;

public abstract class Status {

    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int NO_CONTENT = 204;

    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final int UNPROCESSABLE_CONTENT = 422;

    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int NOT_IMPLEMENTED = 501;
}
