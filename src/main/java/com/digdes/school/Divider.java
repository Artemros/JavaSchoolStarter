package com.digdes.school;

import java.util.*;

public class Divider {
    public static ArrayList<ArrayList<String>> divide(String request) {
        ArrayList<String> data = new ArrayList<>();
        ArrayList<ArrayList<String>> returned = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add(request);
        List<String> tokens = new ArrayList<>();
        divideByComma(list);
        divideByCommand(list);
        divideByWhere(list);
        divideByOr(list);
        divideByAnd(list);
        divideByQuotes(list);
        extractPrefix(returned, list);
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
        extractValues(data, returned);
        extractWhere(data, returned, list, iterator);
        return returned;
    }

    private static void divideByWhere(List<String> list) {
        int curr = 0;
        while (curr < list.size()) {
            String tmp = list.get(curr);
            if (tmp.toLowerCase().contains("where") && !tmp.equalsIgnoreCase("where")) {
                int l = -1;
                int r = 0;
                int k = 0;
                for (int j = 0; j < tmp.length() - 2; j++) {
                    if (tmp.charAt(j) == '‘' || tmp.charAt(j) == '\'' && k==0) {
                        l = j;
                        k=1;
                    }else if (tmp.charAt(j) == '’' || tmp.charAt(j) == '\'') {
                        r = j;
                        k=0;
                    }
                    if (tmp.toLowerCase().charAt(j) == 'w' && tmp.toLowerCase().charAt(j + 1) == 'h'&& tmp.toLowerCase().charAt(j + 2) == 'e'&& tmp.toLowerCase().charAt(j + 3) == 'r'&& tmp.toLowerCase().charAt(j + 4) == 'e' && r > l) {
                        List<String> new_list = new ArrayList<>();
                        for(int i=0;i<curr;i++){
                            new_list.add(list.get(i));
                        }
                        new_list.add(tmp.substring(0,j-1).trim());
                        new_list.add("where");
                        new_list.add(tmp.substring(j+5).trim());
                        for(int i=curr+1;i<list.size();i++){
                            new_list.add(list.get(i));
                        }
                        list.clear();
                        list.addAll(new_list);
                        curr--;
                        break;
                    }
                }
            }
            curr++;
        }
    }

    private static void divideByAnd(List<String> list) {
        int curr = 0;
        while (curr < list.size()) {
            String tmp = list.get(curr);
            if (tmp.toLowerCase().contains("and") && !tmp.equalsIgnoreCase("and")) {
                int l = -1;
                int r = 0;
                int k = 0;
                for (int j = 0; j < tmp.length() - 2; j++) {
                    if (tmp.charAt(j) == '‘' || tmp.charAt(j) == '\'' && k==0) {
                        l = j;
                        k=1;
                    } else if (tmp.charAt(j) == '’' || tmp.charAt(j) == '\'') {
                        r = j;
                        k=0;
                    }
                    if (tmp.toLowerCase().charAt(j) == 'a' && tmp.toLowerCase().charAt(j + 1) == 'n' && tmp.toLowerCase().charAt(j + 2) == 'd' && r > l) {
                        List<String> new_list = new ArrayList<>();
                        for(int i=0;i<curr;i++){
                            new_list.add(list.get(i));
                        }
                        new_list.add(tmp.substring(0,j-1).trim());
                        new_list.add("and");
                        new_list.add(tmp.substring(j+3).trim());
                        for(int i=curr+1;i<list.size();i++){
                            new_list.add(list.get(i));
                        }
                        list.clear();
                        list.addAll(new_list);
                        curr--;
                        break;
                    }
                }
            }
            curr++;
        }
    }

    private static void divideByOr(List<String> list) {
        int curr = 0;
        while (curr < list.size()) {
            String tmp = list.get(curr);
            if (tmp.toLowerCase().contains("or") && !tmp.equalsIgnoreCase("or")) {
                int l = -1;
                int r = 0;
                int k = 0;
                for (int j = 0; j < tmp.length() - 2; j++) {
                    if (tmp.charAt(j) == '‘' || tmp.charAt(j) == '\'' && k==0) {
                        l = j;
                        k=1;
                    }else if (tmp.charAt(j) == '’' || tmp.charAt(j) == '\'') {
                        r = j;
                        k=0;
                    }
                    if (tmp.toLowerCase().charAt(j) == 'o' && tmp.toLowerCase().charAt(j + 1) == 'r' && r > l) {
                        List<String> new_list = new ArrayList<>();
                        for(int i=0;i<curr;i++){
                            new_list.add(list.get(i));
                        }
                        new_list.add(tmp.substring(0,j-1).trim());
                        new_list.add("or");
                        new_list.add(tmp.substring(j+2).trim());
                        for(int i=curr+1;i<list.size();i++){
                            new_list.add(list.get(i));
                        }
                        list.clear();
                        list.addAll(new_list);
//                        curr--;
                        break;
                    }
                }
            }
            curr++;
        }
    }

    private static void extractPrefix(ArrayList<ArrayList<String>> returned, List<String> list) {
        ArrayList<String> prefix = new ArrayList<>();
        prefix.add(list.get(0));
        returned.add(prefix);
    }

    private static void extractWhere(ArrayList<String> data, ArrayList<ArrayList<String>> returned, List<String> list, int iterator) {
        data.addAll(list.subList(iterator, list.size()));
        ArrayList<String> where = new ArrayList<>(data);
        int where_iterator = 0;
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
    }

    private static void extractValues(ArrayList<String> data, ArrayList<ArrayList<String>> returned) {
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
    }

    private static void divideByQuotes(List<String> list) {
        for (String s : Arrays.asList("‘", "’", "'")) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                StringTokenizer tokenizer = new StringTokenizer(list.get(i), s);

                while (tokenizer.hasMoreElements()) {
                    String token = tokenizer.nextToken();
                    if (!token.isEmpty() && !token.isBlank()) {
                        temp.add(token.trim());
                    }
                }
            }
            list.clear();
            list.addAll(temp);

        }
    }

    private static void divideByCommand(List<String> list) {
        List<String> temp = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(list.get(0), " ");
        while (tokenizer.hasMoreElements()) {
            String token = tokenizer.nextToken();
            if (!token.contains("‘")) {
                temp.add(token);
            } else {
                StringBuilder sb = new StringBuilder(list.get(0));
                sb.replace(0, temp.get(0).length(), "");
                sb.replace(0, temp.get(1).length() + 1, "");
                if (!sb.isEmpty()) {
                    temp.add(String.valueOf(sb));
                }
                break;
            }
        }
        for (int i = 1; i < list.size(); i++) {
            temp.add(list.get(i));
        }
        list.clear();
        list.addAll(temp);
    }

    private static void divideByComma(List<String> list) {
        for (String s : Arrays.asList(",")) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                StringTokenizer tokenizer = new StringTokenizer(list.get(i), s);

                while (tokenizer.hasMoreElements()) {
                    String token = tokenizer.nextToken();
                    temp.add(token);
                }
            }
            list.clear();
            list.addAll(temp);

        }
    }
}

