package com.digdes.school;

import java.util.*;

public class Divider {
    public static ArrayList<ArrayList<String>> divide(String request) {
        ArrayList<String> data = new ArrayList<>();
        ArrayList<ArrayList<String>> returned = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add(request);
        List<String> tokens = new ArrayList<>();
        for (String s : Arrays.asList("‘", "’", ",", " ")) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                StringTokenizer tokenizer = new StringTokenizer(list.get(i), s);

                while (tokenizer.hasMoreElements()) {
                    temp.add(tokenizer.nextToken());
                }
            }
            list.clear();
            list.addAll(temp);

        }
        ArrayList<String> prefix = new ArrayList<>();
        prefix.add(list.get(0));
        returned.add(prefix);
        int iterator;
        for (iterator = 1; iterator < list.size(); iterator++) {
            if (list.get(iterator).equalsIgnoreCase("where")) {
                iterator++;
                break;
            }
            if (!list.get(iterator).equalsIgnoreCase("values") && !list.get(iterator).equals("=")) {
                data.add(list.get(iterator));
            }
        }
        for (String s : Arrays.asList("=")) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                StringTokenizer tokenizer = new StringTokenizer(data.get(i), s);

                while (tokenizer.hasMoreElements()) {
                    temp.add(tokenizer.nextToken());
                }
            }
            data.clear();
            data.addAll(temp);

        }
        ArrayList<String> values = new ArrayList<>(data);
        data.clear();
        returned.add(values);
        data.addAll(list.subList(iterator, list.size()));
        ArrayList<String> where = new ArrayList<>(data);
        Integer where_iterator = 0;
        while (where_iterator < where.size()) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < where.size(); i++) {
                if (where.get(i).contains(">=")) {
                    temp.add(">=");
                    StringBuilder sb = new StringBuilder(where.get(i));
                    sb.delete(0, 2);
                    if (!sb.isEmpty()) {
                        temp.add(String.valueOf(sb));
                    }
                } else if (where.get(i).contains("<=")) {
                    temp.add("<=");
                    StringBuilder sb = new StringBuilder(where.get(i));
                    sb.delete(0, 2);
                    if (!sb.isEmpty()) {
                        temp.add(String.valueOf(sb));
                    }
                } else if (where.get(i).contains("!=")) {
                    temp.add("!=");
                    StringBuilder sb = new StringBuilder(where.get(i));
                    sb.delete(0, 2);
                    if (!sb.isEmpty()) {
                        temp.add(String.valueOf(sb));
                    }
                } else if (where.get(i).contains("=")) {
                    temp.add("=");
                    StringBuilder sb = new StringBuilder(where.get(i));
                    sb.delete(0, 1);
                    if (!sb.isEmpty()) {
                        temp.add(String.valueOf(sb));
                    }
                } else if (where.get(i).contains(">")) {
                    temp.add(">");
                    StringBuilder sb = new StringBuilder(where.get(i));
                    sb.delete(0, 1);
                    if (!sb.isEmpty()) {
                        temp.add(String.valueOf(sb));
                    }
                } else if (where.get(i).contains("<")) {
                    temp.add("<");
                    StringBuilder sb = new StringBuilder(where.get(i));
                    sb.delete(0, 1);
                    if (!sb.isEmpty()) {
                        temp.add(String.valueOf(sb));
                    }
                } else {
                    temp.add(where.get(i));
                }
            }
            where.clear();
            where.addAll(temp);
            where_iterator++;
        }
        returned.add(where);
        return returned;
    }
}

