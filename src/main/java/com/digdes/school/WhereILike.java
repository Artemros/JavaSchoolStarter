package com.digdes.school;

import java.util.Map;

public class WhereILike implements WhereInterface {
    private final String key;
    private final Object value;

    public WhereILike(String key, Object value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public boolean test(Map<String, Object> row) {
        String sv = (String) value;
        String svk;
        if(key.equalsIgnoreCase("lastName")) {
            svk = (String) row.get("lastName");
        }else{
            svk = (String) row.get(key);
        }
        if (row.containsKey(key) || key.equalsIgnoreCase("lastName")) {
            if(sv.charAt(0)!='%' && !String.valueOf(sv.charAt(0)).equalsIgnoreCase(String.valueOf(svk.charAt(0)))){
                return false;
            }
            int i=0;
            int j=0;
            if(sv.charAt(0)=='%'){
                i++;
                while(!String.valueOf(sv.charAt(i)).equalsIgnoreCase(String.valueOf(svk.charAt(j)))){
                    j++;
                }
            }
            while (i<sv.length()){
                if(sv.charAt(i)=='%'){
                    return true;
                }
                if(!String.valueOf(sv.charAt(i)).equalsIgnoreCase(String.valueOf(svk.charAt(j))) && sv.charAt(i)!='%'){
                    return false;
                }
                i++;
                j++;
            }
            return j >= svk.length();
        }
        return false;
    }
}
