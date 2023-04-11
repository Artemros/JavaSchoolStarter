package com.digdes.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DelTest {
    @Test
    public void deleteAllTest1() throws Exception {
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
        List<Map<String, Object>> deleted = jss.execute("DELETE");
        Assertions.assertIterableEquals(data, deleted);
//        System.out.println(jss.getJssList());
    }

    @Test
    public void deleteAllTest2() throws Exception {
        JavaSchoolStarter jss = new JavaSchoolStarter();
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String,Object> row1 = new HashMap<>();
        row1.put("id",1L);
        row1.put("lastName","Петров");
        row1.put("age",30L);
        row1.put("cost",5.4);
        row1.put("active", true);
        Map<String,Object> row2 = new HashMap<>();
        row2.put("id",2L);
        row2.put("lastName","Иванов");
        row2.put("age",25L);
        row2.put("cost",4.3);
        row2.put("active", false);

        data.add(row1);
        data.add(row2);
        jss.execute("INSERT VALUES ‘lastName’ = ‘Петров’ , ‘id’=1, ‘age’=30, ‘active’=true, ‘cost’=5.4");
        jss.execute("INSERT VALUES ‘lastName’ = ‘Иванов’ , ‘id’=2, ‘age’=25, ‘active’=false, ‘cost’=4.3");
        List<Map<String, Object>> deleted = jss.execute("DELETE");
        Assertions.assertIterableEquals(data, deleted);
//        System.out.println(jss.getJssList());
    }

    @Test
    public void deleteTestID3() throws Exception {
        JavaSchoolStarter jss = new JavaSchoolStarter();
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String,Object> row3 = new HashMap<>();
        row3.put("id",3L);
        row3.put("lastName","Федоров");
        row3.put("age",40L);
        row3.put("cost",10.1);
        row3.put("active", true);
        data.add(row3);
        jss.execute("INSERT VALUES ‘lastName’ = ‘Петров’ , ‘id’=1, ‘age’=30, ‘active’=true, ‘cost’=5.4");
        jss.execute("INSERT VALUES ‘lastName’ = ‘Иванов’ , ‘id’=2, ‘age’=25, ‘active’=false, ‘cost’=4.3");
        jss.execute("INSERT VALUES ‘lastName’ = ‘Федоров’ , ‘id’=3, ‘age’=40, ‘active’=true, ‘cost’=10.1");
        List<Map<String, Object>> deleted = jss.execute("DELETE WHERE ‘id’=3");
        Assertions.assertIterableEquals(data, deleted);
//      System.out.println(jss.getJssList());
    }
}
