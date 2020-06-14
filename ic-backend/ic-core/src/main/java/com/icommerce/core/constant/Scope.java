package com.icommerce.core.constant;

/**
 * The scope of token
 */
public enum Scope {

    REFRESH_TOKEN;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
