package com.clafu.model;

public class CategoryModel {
    
    private String key;
    
    private String name;
    
    public CategoryModel(String key, String name) {
        this.setKey(key);
        this.setName(name);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
