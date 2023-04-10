package com.digdes.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WhereLess implements WhereInterface {
    private final String key;
    private final Object value;

    public WhereLess(String key, Object value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public boolean test(Map<String, Object> row) {
        int compare=-1;
//        try {
//            if(row.get(key).equals("true") || row.get(key).equals("false")) throw new Exception("Wrong data type");
            switch (key) {
                case ("id"), ("age"):
                    compare = Long.compare((Long) value, (Long) row.get(key));
                    break;
                case ("cost"):
                    compare = Double.compare((Double) value, (Double) row.get(key));
                    break;
                default:
                    throw new IllegalArgumentException("Wrong column type");
            }
//        } catch (Exception e){
//            throw new RuntimeException(e);
//        }
        return compare > 0;
    }
}
