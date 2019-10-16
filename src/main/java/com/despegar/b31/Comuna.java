package com.despegar.b31;

import java.util.Set;

public class Comuna {

    private Integer id;
    private Set<String> barrios;

    public Comuna(Integer id, Set<String> barrios) {
        this.id = id;
        this.barrios = barrios;
    }

    public Integer getId() {
        return id;
    }

    public Set<String> getBarrios() {
        return barrios;
    }
}
