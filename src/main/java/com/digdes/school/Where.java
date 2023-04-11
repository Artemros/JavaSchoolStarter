package com.digdes.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Where {

    public Predicate<Map<String, Object>> getWhere(List<String> data) {
        return getAllOr(data);
    }

    private static Predicate<Map<String, Object>> getAllAnd(List<String> data) {
        List<List<String>> sub_data = new ArrayList<>();
        int prev = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equalsIgnoreCase("and")) {
                sub_data.add(data.subList(prev, i));
                prev = i + 1;
            }
        }
        sub_data.add(data.subList(prev, data.size()));
        List<Predicate<Map<String, Object>>> predicateList = new ArrayList<>();
        for (List<String> l : sub_data
        ) {
            Predicate<Map<String, Object>> tmp = getSimplePredicate(l);
            predicateList.add(tmp);
        }
        Predicate<Map<String, Object>> result = stringObjectMap -> true;
        if (predicateList.size() == 1) {
            result = predicateList.get(0);
        } else {
            for (Predicate<Map<String, Object>> singlePredicate : predicateList) {
                result = result.and(singlePredicate);
            }
        }
        return result;
    }

    private static Predicate<Map<String, Object>> getAllOr(List<String> data) {
        List<List<String>> sub_data = new ArrayList<>();
        int prev = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equalsIgnoreCase("or")) {
                sub_data.add(data.subList(prev, i));
                prev = i + 1;
            }
        }
        sub_data.add(data.subList(prev, data.size()));
        List<Predicate<Map<String, Object>>> predicateList = new ArrayList<>();
        for (List<String> l : sub_data
        ) {
            Predicate<Map<String, Object>> tmp = getAllAnd(l);
            predicateList.add(tmp);
        }
        Predicate<Map<String, Object>> result = stringObjectMap -> false;
        if (predicateList.size() == 1) {
            result = predicateList.get(0);
        } else {
            for (Predicate<Map<String, Object>> singlePredicate : predicateList) {
                result = result.or(singlePredicate);
            }
        }
        return result;
    }

    private static Predicate<Map<String, Object>> getSimplePredicate(List<String> data) {
        List<String> operators = new ArrayList<>();
        operators.add("=");
        operators.add("!=");
        operators.add("like");
        operators.add("ilike");
        operators.add(">=");
        operators.add("<=");
        operators.add("<");
        operators.add(">");
        for (int i = 0; i < data.size(); i++) {

            if (operators.contains(data.get(i))) {
                String column_name = data.get(i - 1).toLowerCase();
                Object column_data = switch (column_name) {
                    case ("id"), ("age") -> Long.parseLong(data.get(i + 1));
                    case ("lastname") -> data.get(i + 1);
                    case ("cost") -> Double.parseDouble(data.get(i + 1));
                    case ("active") -> data.get(i + 1).equals("true");
                    default -> null;
                };
                switch (data.get(i)) {
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
        }
        return stringObjectMap -> true;
    }
}
