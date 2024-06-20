package com.example.ag.helpers;

public class LoginBody {

    public String grant_type;

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String audience;
    public String scope;

    public LoginBody(String grant_type, String audience, String scope) {
        this.grant_type = grant_type;
        this.audience = audience;
        this.scope = scope;
    }


    public String getGrant_type() {
        return grant_type;
    }

    public String getAudience() {
        return audience;
    }

    public String getScope() {
        return scope;
    }
}
