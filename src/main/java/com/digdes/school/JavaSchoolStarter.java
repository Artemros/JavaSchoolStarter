package com.digdes.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaSchoolStarter {
    public List<Map<String, Object>> getJssList() {
        return jssList;
    }

    //Дефолтный конструктор
    private List<Map<String, Object>> jssList = new ArrayList<>();

    public JavaSchoolStarter() {
        List<String> columns = new ArrayList<>();
        columns.add("id");
        columns.add("lastName");
        columns.add("age");
        columns.add("cost");
        columns.add("active");

    }

    //На вход запрос, на выход результат выполнения запроса
    public List<Map<String, Object>> execute(String request) throws Exception {
        //Здесь начало исполнения вашего кода
        JavaSchoolStarter javaSchoolStarter = new JavaSchoolStarter();
        ArrayList<ArrayList<String>> data = Divider.divide(request);
        ArrayList<String> valueStrings = data.get(1);
        ArrayList<String> whereStrings = data.get(2);
        WhereInterface where = new Where().getWhere(whereStrings);

        switch (data.get(0).get(0)) {
            case ("INSERT"):
                return insert(valueStrings);
            case ("UPDATE"):
                return update(valueStrings, where);
            case ("DELETE"):
                return delete(where);
            case ("SELECT"):
                return select(where);
            default:
                throw new IllegalStateException("Unexpected value: " + data.get(0));
        }
//        return new ArrayList<>();
    }

    private List<Map<String, Object>> update(ArrayList<String> valueStrings, WhereInterface where) {
        List<Map<String, Object>> to_update = select(where);
        List<Map<String, Object>> result = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        columns.add("id");
        columns.add("lastName");
        columns.add("age");
        columns.add("cost");
        columns.add("active");

        for(Map<String, Object> jssRow:jssList){
            for(Map<String, Object> to_update_row:to_update){
                if(jssRow.equals(to_update_row)){
                    int iterator = 0;
                    while (iterator < valueStrings.size()) {
                        String column_name = valueStrings.get(iterator);
                        if (columns.contains(column_name)) {
                            String column_data = valueStrings.get(iterator + 1);
                            jssRow.replace(columns.get(columns.indexOf(column_name)), column_data);
                            switch (column_name) {
                                case ("id"), ("age"):
                                    jssRow.replace(columns.get(columns.indexOf(column_name)), Long.parseLong(column_data));
                                    break;
                                case ("lastName"):
                                    jssRow.replace(columns.get(columns.indexOf(column_name)), column_data);
                                    break;
                                case ("cost"):
                                    jssRow.replace(columns.get(columns.indexOf(column_name)), Double.parseDouble(column_data));
                                    break;
                                case ("active"):
                                    jssRow.replace(columns.get(columns.indexOf(column_name)), column_data.equals("true"));
                                    break;
                            }
                        }
                        iterator += 2;
                    }
                    result.add(jssRow);
                }
            }
        }
        return result;
    }

    private List<Map<String, Object>> insert(ArrayList<String> valueStrings) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        columns.add("id");
        columns.add("lastName");
        columns.add("age");
        columns.add("cost");
        columns.add("active");
        Map<String, Object> row = new HashMap<>();
        for (String s : columns) {
            row.put(s, null);
        }
        int iterator = 0;
        while (iterator < valueStrings.size()) {
            String column_name = valueStrings.get(iterator);
            if (columns.contains(column_name)) {
                String column_data = valueStrings.get(iterator + 1);
                row.replace(columns.get(columns.indexOf(column_name)), column_data);
                switch (column_name) {
                    case ("id"), ("age"):
                        row.replace(columns.get(columns.indexOf(column_name)), Long.parseLong(column_data));
                        break;
                    case ("lastName"):
                        row.replace(columns.get(columns.indexOf(column_name)), column_data);
                        break;
                    case ("cost"):
                        row.replace(columns.get(columns.indexOf(column_name)), Double.parseDouble(column_data));
                        break;
                    case ("active"):
                        row.replace(columns.get(columns.indexOf(column_name)), column_data.equals("true"));
                        break;
                }
            }
            iterator += 2;


        }
        result.add(row);
        jssList.add(row);
        return result;
    }

    private List<Map<String, Object>> delete(WhereInterface where) {
        List<Map<String, Object>> to_delete = new ArrayList<>();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> jssLine : jssList) {
            if (where.compare(jssLine)) {
                to_delete.add(jssLine);
            } else {
                result.add(jssLine);
            }
        }
        jssList.clear();
        jssList.addAll(result);
        return to_delete;
    }

    private List<Map<String, Object>> select(WhereInterface where) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> jssLine : jssList) {
            if (where.compare(jssLine)) {
                result.add(jssLine);
            }
        }
        return result;
    }


}


