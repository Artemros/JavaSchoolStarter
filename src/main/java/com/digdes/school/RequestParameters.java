package com.digdes.school;

import java.util.Map;
import java.util.function.Predicate;

public record RequestParameters(String operation, Map<String, Object> values, Predicate<Map<String, Object>> where) {
}
