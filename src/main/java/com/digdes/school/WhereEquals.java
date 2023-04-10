package com.digdes.school;

import java.util.Map;
import java.util.Objects;

public class WhereEquals implements WhereInterface {
    private final String key;
    private final Object value;

    public WhereEquals(String key, Object value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public boolean test(Map<String, Object> row) {
        if (row.containsKey(key)) {
            return Objects.equals(row.get(key), value);
        }
        return false;
    }
}
