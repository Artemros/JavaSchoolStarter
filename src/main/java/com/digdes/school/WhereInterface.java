package com.digdes.school;

import java.util.Map;
import java.util.function.Predicate;

public interface WhereInterface extends Predicate<Map<String, Object>> {
    boolean test(Map<String, Object> row);
}
