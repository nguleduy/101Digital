package com.example.codebase.constant;

/**
 * URL constants.
 */
public final class Path {
    Path() {
    }

    public static final String DEFAULT_API_VERSION = "api/v1";
    public static final String AUTH = "/auth";
    public static final String AUTH_LOGIN = "/login";
    public static final String AUTH_REGISTER = "/register";
    public static final String SHOP_OWNER = "/shop-owner";
    public static final String SHOP_OWNER_SETUP_SHOP = "/setup-shop";
    public static final String SHOP_OWNER_MANAGE_MENU = "/manage-menu";
    public static final String SHOP_OWNER_CONFIGURE_QUEUE = "/configure-queue";
    public static final String SHOP_OPERATOR = "/shop-operator";
    public static final String SHOP_OPERATOR_VIEW_QUEUE = "/queue";
    public static final String SHOP_OPERATOR_SERVE_CUSTOMER = "/serve-customer";
    public static final String SHOP = "/shop";
    public static final String CUSTOMER = "/customer";
    public static final String SIGNUP = "/signup";
    public static final String LOGIN = "/login";
    public static final String CUSTOMER_FIND_NEAREST_SHOPS = "/find-nearest-shops";
    public static final String CUSTOMER_PLACE_ORDER = "/place-order";
    public static final String CUSTOMER_VIEW_QUEUE = "/{shopId}/queue";
    public static final String CUSTOMER_CANCEL_ORDER = "/cancel-order";

}
