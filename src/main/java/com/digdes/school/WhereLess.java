package com.digdes.school;

import java.util.Map;

public class WhereLess implements WhereInterface {
    private final String key;
    private final Object value;

    public WhereLess(String key, Object value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public boolean test(Map<String, Object> row) {
        int compare = switch (key) {
            case ("id"), ("age") -> Long.compare((Long) value, (Long) row.get(key));
            case ("cost") -> Double.compare((Double) value, (Double) row.get(key));
            default -> throw new IllegalArgumentException("Wrong column type");
        };
        return compare > 0;
    }
}
