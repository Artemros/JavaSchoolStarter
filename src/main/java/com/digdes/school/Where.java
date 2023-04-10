package com.digdes.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Where {

    public Predicate<Map<String, Object>> getWhere(List<String> data) throws Exception {
        return getAllOr(data);
    }

    private static Predicate<Map<String, Object>> getAllAnd(List<String> data) throws Exception {
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
            Predicate<Map<String, Object>> smth = getSimplePredicate(l);
            predicateList.add(smth);
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

    private static Predicate<Map<String, Object>> getAllOr(List<String> data) throws Exception {
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
            Predicate<Map<String, Object>> smth = getAllAnd(l);
            predicateList.add(smth);
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

    private static Predicate<Map<String, Object>> getSimplePredicate(List<String> data) throws Exception {
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
                Object column_data=null;
                switch (column_name) {
                    case ("id"), ("age"):
                        column_data = Long.parseLong(data.get(i + 1));
                        break;
                    case ("lastname"):
                        column_data = data.get(i + 1);
                        break;
                    case ("cost"):
                        column_data = Double.parseDouble(data.get(i + 1));
                        break;
                    case ("active"):
                        column_data = data.get(i + 1).equals("true");
                        break;

                }
                switch (data.get(i)) {
                    case ("="):
                        return new WhereEquals(column_name, column_data);
                    case ("!="):
                        return new WhereEquals(column_name, column_data).negate();
                    case ("<="):
                        return new WhereLess(column_name, column_data).or(new WhereEquals(column_name, column_data));
                    case (">="):
                        return new WhereMore(column_name, column_data).or(new WhereEquals(column_name, column_data));
                    case ("<"):
                        return new WhereLess(column_name, column_data);
                    case (">"):
                        return new WhereMore(column_name, column_data);
                    case("like"):
                        return new WhereLike(column_name, column_data);
                    case("ilike"):
                        return new WhereILike(column_name, column_data);
                }
            }
        }
        return stringObjectMap -> true;
    }
}
