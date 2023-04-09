package com.digdes.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Where {

    public WhereInterface getWhere(ArrayList<String> data) {
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
                switch (data.get(i)) {
                    case ("="):
                        return new WhereEquals(data.get(i - 1), data.get(i + 1));
                    case ("!="):
                        return new WhereNotEquals(data.get(i - 1), data.get(i + 1));
                    case("<="):
                        return new WhereLessOrEquals(data.get(i - 1), data.get(i + 1));
                    case(">="):
                        return new WhereMoreOrEquals(data.get(i - 1), data.get(i + 1));
                    case("<"):
                        return new WhereLess(data.get(i - 1), data.get(i + 1));
                    case(">"):
                        return new WhereMore(data.get(i - 1), data.get(i + 1));
                }
            }
        }
        return new WhereAlwaysTrue();
    }
}
