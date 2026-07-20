package com.restaurant.restaurant_backend.enums;

public enum Permission {

    // USER
    USER_CREATE,
    USER_UPDATE,
    USER_DELETE,
    USER_VIEW,

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

    // INVENTORY
    STOCK_UPDATE,

    // KITCHEN
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