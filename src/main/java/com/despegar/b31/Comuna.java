package com.despegar.b31;

import java.util.Set;

public class Comuna {

    private String id;
    private Set<String> barrios;

    public Comuna(String id, Set<String> barrios) {
        this.id = id;
        this.barrios = barrios;
    }

    public String getId() {
        return id;
    }

    public Set<String> getBarrios() {
        return barrios;
    }
}
