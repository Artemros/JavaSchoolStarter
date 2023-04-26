package com.digdes.school;

import java.util.*;
import java.util.function.Predicate;

public class WhereParser {

    public Predicate<Map<String, Object>> getWhere(String data) {
        return getAllOr(data);
    }

    private static Predicate<Map<String, Object>> getAllAnd(String data) {
        Optional<Predicate<Map<String, Object>>> optional = Arrays.stream(new Parser().customSplit(data, "and"))
                .map(String::trim)
                .map(WhereParser::getSimplePredicate)
                .reduce(Predicate::and);

        return optional.orElseGet(() -> stringObjectMap1 -> true);
    }

    private static Predicate<Map<String, Object>> getAllOr(String data) {
        Optional<Predicate<Map<String, Object>>> optional = Arrays.stream(new Parser().customSplit(data, "or"))
                .map(String::trim)
                .map(WhereParser::getAllAnd)
                .reduce(Predicate::or);

        return optional.orElseGet(() -> stringObjectMap1 -> true);

    }

    private static Predicate<Map<String, Object>> getSimplePredicate(String data) {
        List<String> operators = new ArrayList<>();
        operators.add("=");
        operators.add("!=");
        operators.add("like");
        operators.add("ilike");
        operators.add(">=");
        operators.add("<=");
        operators.add("<");
        operators.add(">");
        data = data.replaceAll("['‘’]", "");
        String separator;
        if (data.contains("!=")) {
            separator = "!=";
        } else if (data.contains("<=")) {
            separator = "<=";
        } else if (data.contains(">=")) {
            separator = ">=";
        } else if (data.contains(">")) {
            separator = ">";
        } else if (data.contains("<")) {
            separator = "<";
        } else if (data.contains("=")) {
            separator = "=";
        } else if (data.contains("ilike")) {
            separator = "ilike";
        } else if (data.contains("like")) {
            separator = "like";
        } else {
            throw new IllegalArgumentException("Bad WHERE " + data);
        }

        if (operators.contains(separator)) {
            Parser parser = new Parser();
            String column_name = parser.getStringBeforeIndex(data, parser.getIndex(data, separator)).trim().toLowerCase();
            Object column_data = parser.getStringAfterIndex(data, parser.getIndex(data, separator) + separator.length()).trim();
            column_data = switch (column_name) {
                case ("id"), ("age") -> Long.parseLong((String) column_data);
                case ("lastname") -> column_data;
                case ("cost") -> Double.parseDouble((String) column_data);
                case ("active") -> column_data.equals("true");
                default -> null;
            };
            switch (separator) {
                case ("=") -> {
                    return new WhereEquals(column_name, column_data);
                }
                case ("!=") -> {
                    return new WhereEquals(column_name, column_data).negate();
                }
                case ("<=") -> {
                    return new WhereLess(column_name, column_data).or(new WhereEquals(column_name, column_data));
                }
                case (">=") -> {
                    return new WhereMore(column_name, column_data).or(new WhereEquals(column_name, column_data));
                }
                case ("<") -> {
                    return new WhereLess(column_name, column_data);
                }
                case (">") -> {
                    return new WhereMore(column_name, column_data);
                }
                case ("like") -> {
                    return new WhereLike(column_name, column_data);
                }
                case ("ilike") -> {
                    return new WhereILike(column_name, column_data);
                }
            }
        }
        return stringObjectMap -> true;
    }


}
