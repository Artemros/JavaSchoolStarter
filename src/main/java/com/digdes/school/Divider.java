package com.digdes.school;

import java.util.*;
import java.util.function.Predicate;

public class Divider {

    public static RequestParameters divide(final String request) {
        String prefix = request.split(" ")[0].toLowerCase();
        Map<String, Object> value = null;
        Predicate<Map<String, Object>> where = stringObjectMap -> true;
        Parser parser = new Parser();


        int whereIndex = parser.getIndex(request, "where");
        if (whereIndex > -1 && parser.getIndex(request, "values") > -1) {
            value = parseValues(parser.getStringBetweenIndex(request, parser.getIndex(request, "values") + "values".length(), whereIndex));
            where = new WhereParser().getWhere(parser.getStringAfterIndex(request, whereIndex +"where".length()));
        }
        if (whereIndex == -1 && parser.getIndex(request, "values") > -1) {
            value = parseValues(parser.getStringAfterIndex(request, parser.getIndex(request, "values") + "values".length()));
        }
        if (whereIndex > -1 && parser.getIndex(request, "values") == -1) {
            where=new WhereParser().getWhere(parser.getStringAfterIndex(request, whereIndex +"where".length()));
        }



        return new RequestParameters(prefix, value, where);
    }

    public static Map<String, Object> parseValues(String valuesStrings) {
        return Arrays.stream(valuesStrings.split(","))
                .map(String::trim)
                .collect(HashMap::new, (s,v)->s.put(getColumn(v), getValue(v)), HashMap::putAll);
    }

    private static String getColumn(String column) {
        List<String> columns = new ArrayList<>();
        columns.add("id");
        columns.add("lastname");
        columns.add("age");
        columns.add("cost");
        columns.add("active");
        String columnName = column.split("=")[0].trim().replaceAll("['‘’]", "");
        if(!columns.contains(columnName.toLowerCase())){
            throw new IllegalArgumentException("Bad column name " + column);
        }
        return columnName;

    }

    private static Object getValue(String column) {
        String[] data = column.split("=");
        if(data[1].trim().replaceAll("['‘’]", "").equals("null")){
            return null;
        }
        switch (data[0].trim().replaceAll("['‘’]", "")) {
            case ("id"), ("age") -> {
                return Long.parseLong(data[1].trim().replaceAll("['‘’]", ""));
            }
            case ("lastName") -> {
                return data[1].trim().replaceAll("['‘’]", "");
            }
            case ("cost") -> {
                return Double.parseDouble(data[1].trim().replaceAll("['‘’]", ""));
            }
            case ("active") -> {
                return data[1].trim().replaceAll("['‘’]", "").equals("true");
            }
        }
        throw new IllegalArgumentException("Bad column name " + column);
    }

}

