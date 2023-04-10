package com.digdes.school;

import java.util.Map;

public class WhereAlwaysTrue implements WhereInterface {

    @Override
    public boolean test(Map<String, Object> row) {
        return true;
    }
}
