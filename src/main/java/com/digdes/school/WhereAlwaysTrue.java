package com.digdes.school;

import java.util.Map;

public class WhereAlwaysTrue implements WhereInterface {

    @Override
    public boolean compare(Map<String, Object> row) {
        return true;
    }
}
