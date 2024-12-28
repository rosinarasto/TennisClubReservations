package com.tennisclub.reservations.config;

public abstract class ApiUris {
    public static final String BASE_URI               = "/api";

    public static final String CREATE_URI             = "/create";
    public static final String UPDATE_URI             = "/update/{id}";
    public static final String DELETE_ALL_URI         = "/delete";
    public static final String DELETE_URI             = "/delete/{id}";
    public static final String GET_ALL_URI            = "/get";
    public static final String GET_URI                = "/get/{id}";

    public static final String COURT_URI              = BASE_URI + "/court";
    public static final String COURT_RESERVATIONS_URI = BASE_URI + "/court/{number}/reservations";

    public static final String SURFACE_URI            = BASE_URI + "/surface";

    public static final String RESERVATION_URI        = BASE_URI + "/reservation";

    public static final String USER_URI               = BASE_URI + "/user";
    public static final String USER_RESERVATIONS_URI  = BASE_URI + "/user/{phoneNumber}/reservations";
}