package com.example.ferreteria_api.global;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.HashMap;
import java.util.Map;

public class GetResponse {
    private boolean ok;
    private String message;

    // map con campos dinamios que va en la raiz
    private Map<String, Object> additionalFields = new HashMap<>();

    public GetResponse(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Anotacion para que los campos aparezcan el el json
    @JsonAnyGetter
    public Map<String, Object> getAdditionalFields() {
        return additionalFields;
    }

    public void addField(String key, Object value) {
        this.additionalFields.put(key, value);
    }
}
