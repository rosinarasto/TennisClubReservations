package com.tennisclub.reservations.config;

public abstract class ApiUris {
    public static final String BASE_URI               = "/api";

    public static final String ID_URI                = "/{id}";

    public static final String COURT_URI              = BASE_URI + "/court";
    public static final String COURT_RESERVATIONS_URI = "/{number}/reservations";

    public static final String SURFACE_URI            = BASE_URI + "/surface";

    public static final String RESERVATION_URI        = BASE_URI + "/reservation";

    public static final String USER_URI               = BASE_URI + "/user";
    public static final String USER_RESERVATIONS_URI  = "/{phoneNumber}/reservations";
}
