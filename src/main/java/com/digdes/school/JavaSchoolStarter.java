package com.digdes.school;

import java.util.*;
import java.util.function.Predicate;

public class JavaSchoolStarter {
    public List<Map<String, Object>> getJssList() {
        return jssList;
    }

    //Дефолтный конструктор
    private final List<Map<String, Object>> jssList = new ArrayList<>();

    public JavaSchoolStarter() {

    }

    //На вход запрос, на выход результат выполнения запроса
    public List<Map<String, Object>> execute(String request) {
        //Здесь начало исполнения вашего кода
        ArrayList<ArrayList<String>> data = Divider.divide(request);
        ArrayList<String> valueStrings = data.get(1);
        Map<String, Object> valueInsertMap = getInsertMap(valueStrings);
        Map<String, Object> valueUpdateMap = getUpdateMap(valueStrings);
        ArrayList<String> whereStrings = data.get(2);
        Predicate<Map<String, Object>> where = new Where().getWhere(whereStrings);

        for (int i = 0; i < valueStrings.size(); i += 2) {
            haveColumn(valueStrings.get(i));
        }
        return switch (data.get(0).get(0).toLowerCase()) {
            case ("insert") -> insert(valueInsertMap);
            case ("update") -> update(valueUpdateMap, where);
            case ("delete") -> delete(where);
            case ("select") -> select(where);
            default -> throw new IllegalStateException("Unexpected value: " + data.get(0));
        };
    }

    private void haveColumn(String v) {
        List<String> columns = new ArrayList<>();
        columns.add("id");
        columns.add("lastname");
        columns.add("age");
        columns.add("cost");
        columns.add("active");
        if (!columns.contains(v.toLowerCase())) {
            throw new IllegalArgumentException("Wrong column name");
        }
    }

    private Map<String, Object> getUpdateMap(ArrayList<String> valueStrings) {
        List<String> columns = new ArrayList<>();
        columns.add("id");
        columns.add("lastName");
        columns.add("age");
        columns.add("cost");
        columns.add("active");
        Map<String, Object> result = new HashMap<>();
        int iterator = 0;
        while (iterator < valueStrings.size()) {
            String column_name = valueStrings.get(iterator);
            if (columns.contains(column_name)) {
                String column_data = valueStrings.get(iterator + 1);
                if (!Objects.equals(column_data, "null")) {
                    switch (column_name) {
                        case ("id"), ("age") -> result.put(column_name, Long.parseLong(column_data));
                        case ("lastName") -> result.put(column_name, column_data);
                        case ("cost") -> result.put(column_name, Double.parseDouble(column_data));
                        case ("active") -> result.put(column_name, column_data.equals("true"));
                    }
                } else {
                    result.put(column_name, null);
                }
            }
            iterator += 2;
        }
        return result;
    }

    private Map<String, Object> getInsertMap(ArrayList<String> valueStrings) {
        List<String> columns = new ArrayList<>();
        columns.add("id");
        columns.add("lastName");
        columns.add("age");
        columns.add("cost");
        columns.add("active");
        Map<String, Object> result = new HashMap<>();
        for (String s : columns) {
            result.put(s, null);
        }
        int iterator = 0;
        while (iterator < valueStrings.size()) {
            String column_name = valueStrings.get(iterator);
            if (columns.contains(column_name)) {
                String column_data = valueStrings.get(iterator + 1);
                if (!column_data.isEmpty()) {
                    switch (column_name) {
                        case ("id"), ("age") -> result.replace(column_name, Long.parseLong(column_data));
                        case ("lastName") -> result.replace(column_name, column_data);
                        case ("cost") -> result.replace(column_name, Double.parseDouble(column_data));
                        case ("active") -> result.replace(column_name, column_data.equals("true"));
                    }
                }
            }
            iterator += 2;
        }
        return result;
    }

    private List<Map<String, Object>> update(Map<String, Object> valueMap, Predicate<Map<String, Object>> where) {
        List<Map<String, Object>> to_update = select(where);
        List<Map<String, Object>> result = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        columns.add("id");
        columns.add("lastName");
        columns.add("age");
        columns.add("cost");
        columns.add("active");

        for (Map<String, Object> jssRow : jssList) {
            for (Map<String, Object> to_update_row : to_update) {
                if (jssRow.equals(to_update_row)) {
                    int iterator = 0;
                    while (iterator < valueMap.size()) {
                        String column_name = (String) valueMap.keySet().toArray()[iterator];
                        if (columns.contains(column_name)) {
                            if (valueMap.get(column_name) != null) {
                                String column_data = valueMap.get(column_name).toString();
                                switch (column_name) {
                                    case ("id"), ("age") ->
                                            jssRow.replace(columns.get(columns.indexOf(column_name)), Long.parseLong(column_data));
                                    case ("lastName") ->
                                            jssRow.replace(columns.get(columns.indexOf(column_name)), column_data);
                                    case ("cost") ->
                                            jssRow.replace(columns.get(columns.indexOf(column_name)), Double.parseDouble(column_data));
                                    case ("active") -> {
                                        if (column_data.isEmpty()) {
                                            jssRow.replace(columns.get(columns.indexOf(column_name)), null);
                                        } else {
                                            jssRow.replace(columns.get(columns.indexOf(column_name)), column_data.equals("true"));
                                        }
                                    }
                                }
                            } else {
                                jssRow.replace(columns.get(columns.indexOf(column_name)), null);
                            }
                        }
                        iterator += 1;
                    }
                    result.add(jssRow);
                }
            }
        }
        return result;
    }

    private List<Map<String, Object>> insert(Map<String, Object> valueMap) {
        List<Map<String, Object>> result = new ArrayList<>();
        result.add(valueMap);
        jssList.add(valueMap);
        return result;
    }

    private List<Map<String, Object>> delete(Predicate<Map<String, Object>> where) {
        List<Map<String, Object>> to_delete = new ArrayList<>();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> jssLine : jssList) {
            if (where.test(jssLine)) {
                to_delete.add(jssLine);
            } else {
                result.add(jssLine);
            }
        }
        jssList.clear();
        jssList.addAll(result);
        return to_delete;
    }

    private List<Map<String, Object>> select(Predicate<Map<String, Object>> where) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> jssLine : jssList) {
            if (where.test(jssLine)) {
                result.add(jssLine);
            }
        }
        return result;
    }


}


