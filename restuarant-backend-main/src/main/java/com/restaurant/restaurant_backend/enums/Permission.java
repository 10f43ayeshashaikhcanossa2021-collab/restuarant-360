package com.restaurant.restaurant_backend.enums;

public enum Permission {

    // USER
    USER_CREATE,
    USER_UPDATE,
    USER_DELETE,
    USER_VIEW,

    // ROLE
    ROLE_CREATE,
    ROLE_UPDATE,
    ROLE_DELETE,
    ROLE_VIEW,

    // MENU
    MENU_CREATE,
    MENU_UPDATE,
    MENU_DELETE,
    MENU_VIEW,

    // ORDER
    ORDER_CREATE,
    ORDER_CANCEL,
    ORDER_VIEW,

    // PAYMENT
    PAYMENT_PROCESS,

    // REPORT
    REPORT_VIEW,

    // STOCK
    STOCK_UPDATE,

    // KOT
    KOT_UPDATE,

    // OUTLET
    OUTLET_CREATE,
    OUTLET_UPDATE,
    OUTLET_DELETE,
    OUTLET_VIEW,

    // TERMINAL
    TERMINAL_CREATE,
    TERMINAL_UPDATE,
    TERMINAL_DELETE,
    TERMINAL_VIEW
}