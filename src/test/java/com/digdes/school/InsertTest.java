package com.digdes.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class InsertTest {

    @Test
    public void insertTestAdd1() throws Exception {
        JavaSchoolStarter jss = new JavaSchoolStarter();
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String,Object> row1 = new HashMap<>();
        row1.put("id",1L);
        row1.put("lastName","Петров");
        row1.put("age",30L);
        row1.put("cost",5.4);
        row1.put("active", true);

        data.add(row1);

        jss.execute("INSERT VALUES ‘lastName’ = ‘Петров’ , ‘id’=1, ‘age’=30, ‘active’=true, ‘cost’=5.4");
        Assertions.assertIterableEquals(data, jss.getJssList());

    }

    @Test
    public void insertTestAddWithNull() throws Exception {
        JavaSchoolStarter jss = new JavaSchoolStarter();
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String,Object> row1 = new HashMap<>();
        row1.put("id",1L);
        row1.put("lastName","Петров");
        row1.put("age",30L);
        row1.put("cost",null);
        row1.put("active", true);

        data.add(row1);

        jss.execute("INSERT VALUES ‘lastName’ = ‘Петров’ , ‘id’=1, ‘age’=30, ‘active’=true");
        Assertions.assertIterableEquals(data, jss.getJssList());
    }

    @Test
    public void insertTestReturn1() throws Exception {
        JavaSchoolStarter jss = new JavaSchoolStarter();
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String,Object> row1 = new HashMap<>();
        row1.put("id",1L);
        row1.put("lastName","Петров");
        row1.put("age",30L);
        row1.put("cost",5.4);
        row1.put("active", true);

        data.add(row1);

        List<Map<String, Object>> returned = jss.execute("INSERT VALUES ‘lastName’ = ‘Петров’ , ‘id’=1, ‘age’=30, ‘active’=true, ‘cost’=5.4");
        Assertions.assertIterableEquals(data, returned);

    }

    @Test
    public void insertTestReturn2() throws Exception {
        JavaSchoolStarter jss = new JavaSchoolStarter();
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String,Object> row1 = new HashMap<>();
        row1.put("id",1L);
        row1.put("lastName","Петров");
        row1.put("age",30);
        row1.put("cost",5.4);
        row1.put("active", true);
        Map<String,Object> row2 = new HashMap<>();
        row2.put("id",2L);
        row2.put("lastName","Иванов");
        row2.put("age",25L);
        row2.put("cost",4.3);
        row2.put("active", false);

        data.add(row1);
        List<Map<String, Object>> returned = jss.execute("INSERT VALUES ‘lastName’ = ‘Петров’ , ‘id’=1, ‘age’=30, ‘active’=true, ‘cost’=5.4");
        Assertions.assertIterableEquals(data, returned);

        data.clear();
        data.add(row2);
        returned = jss.execute("INSERT VALUES ‘lastName’ = ‘Иванов’ , ‘id’=2, ‘age’=25, ‘active’=false, ‘cost’=4.3");
        Assertions.assertIterableEquals(data, returned);

    }
}
