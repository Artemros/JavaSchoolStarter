package com.digdes.school;

import java.util.Map;

public class WhereMore implements WhereInterface {
    private final String key;
    private final Object value;

    public WhereMore(String key, Object value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public boolean test(Map<String, Object> row){
        int compare=1;
        switch (key) {
            case ("id"), ("age"):
                compare = Long.compare((Long)value, (Long)row.get(key));
                break;
            case ("cost"):
                compare = Double.compare((Double)value, (Double)row.get(key));
                break;
            default:
                throw new IllegalArgumentException("Wrong column type");
        }
        return compare < 0;
    }
}
