package com.digdes.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WhereEquals implements WhereInterface {
    private final String key;
    private final String value;

    public WhereEquals(String key, String value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public boolean compare(Map<String, Object> row) {
        for(int i=0;i<row.size();i++){
            if(row.containsKey(key)){
                return row.get(key).equals(Long.parseLong(value));
            }
        }
        return false;
    }
}
