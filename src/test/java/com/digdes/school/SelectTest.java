package com.digdes.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SelectTest {
    @Test
    public void selectAllTest1() throws Exception {
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
        List<Map<String, Object>> selected = jss.execute("SELECT ");
        Assertions.assertIterableEquals(data, selected);

    }
    @Test
    public void selectAllTest2() throws Exception {
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
        List<Map<String, Object>> selected = jss.execute("SELECT ");
        Assertions.assertIterableEquals(data, selected);

    }
    @Test
    public void selectEqualsTest1() throws Exception {
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
        jss.execute("INSERT VALUES ‘lastName’ = ‘Иванов’ , ‘id’=2, ‘age’=25, ‘active’=false, ‘cost’=4.3");
        List<Map<String, Object>> selected = jss.execute("SELECT WHERE ‘age’=30");
        Assertions.assertIterableEquals(data, selected);

    }

//    @Test
//    public void selectEqualsTest2() throws Exception {
//        JavaSchoolStarter jss = new JavaSchoolStarter();
//        List<Map<String, Object>> data = new ArrayList<>();
//        Map<String,Object> row1 = new HashMap<>();
//        row1.put("id",1);
//        row1.put("lastName","Петров");
//        row1.put("age",30);
//        row1.put("cost",5.4);
//        row1.put("active", true);
////        Map<String,Object> row2 = new HashMap<>();
////        row2.put("id",2);
////        row2.put("lastName","Иванов");
////        row2.put("age",25);
////        row2.put("cost",4.3);
////        row2.put("active", false);
//
//        data.add(row1);
////        data.add(row2);
//        jss.execute("INSERT VALUES ‘lastName’ = ‘Петров’ , ‘id’=1, ‘age’=30, ‘active’=true, ‘cost’=5.4");
//        jss.execute("INSERT VALUES ‘lastName’ = ‘Иванов’ , ‘id’=2, ‘age’=25, ‘active’=false, ‘cost’=4.3");
////        System.out.println(jss.getJssList().get(0).get("age"));
//        List<Map<String, Object>> selected = jss.execute("SELECT WHERE ‘age’=30");
//        Assertions.assertIterableEquals(data, selected);
//
//    }

    @Test
    public void selectNotEqualsTest1() throws Exception {
        JavaSchoolStarter jss = new JavaSchoolStarter();
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String,Object> row2 = new HashMap<>();
        row2.put("id",2L);
        row2.put("lastName","Иванов");
        row2.put("age",25L);
        row2.put("cost",4.3);
        row2.put("active", false);

        data.add(row2);
        jss.execute("INSERT VALUES ‘lastName’ = ‘Петров’ , ‘id’=1, ‘age’=30, ‘active’=true, ‘cost’=5.4");
        jss.execute("INSERT VALUES ‘lastName’ = ‘Иванов’ , ‘id’=2, ‘age’=25, ‘active’=false, ‘cost’=4.3");
        List<Map<String, Object>> selected = jss.execute("SELECT WHERE ‘age’!=30");
        Assertions.assertIterableEquals(data, selected);

    }
    @Test
    public void selectEqualsORTest1() throws Exception {
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
        List<Map<String, Object>> selected = jss.execute("SELECT WHERE ‘age’=30 OR ‘cost’=4.3" );
        Assertions.assertIterableEquals(data, selected);

    }

//    @Test
//    public void selectNotEqualsTest2() throws Exception {
//        JavaSchoolStarter jss = new JavaSchoolStarter();
//        List<Map<String, Object>> data = new ArrayList<>();
//        Map<String,Object> row1 = new HashMap<>();
//        row1.put("id",1);
//        row1.put("lastName","Петров");
//        row1.put("age",30);
//        row1.put("cost",5.4);
//        row1.put("active", true);
////        Map<String,Object> row2 = new HashMap<>();
////        row2.put("id",2);
////        row2.put("lastName","Иванов");
////        row2.put("age",25);
////        row2.put("cost",4.3);
////        row2.put("active", false);
//
//        data.add(row1);
////        data.add(row2);
//        jss.execute("INSERT VALUES ‘lastName’ = ‘Петров’ , ‘id’=1, ‘age’=30, ‘active’=true, ‘cost’=5.4");
//        jss.execute("INSERT VALUES ‘lastName’ = ‘Иванов’ , ‘id’=2, ‘age’=25, ‘active’=false, ‘cost’=4.3");
////        System.out.println(jss.getJssList().get(0).get("age"));
//        List<Map<String, Object>> selected = jss.execute("SELECT WHERE ‘age’!=30");
//        Assertions.assertIterableEquals(data, selected);
//
//    }

    @Test
    public void selectLessOrEqualsTest1() throws Exception {
        JavaSchoolStarter jss = new JavaSchoolStarter();
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String,Object> row2 = new HashMap<>();
        row2.put("id",2L);
        row2.put("lastName","Иванов");
        row2.put("age",25L);
        row2.put("cost",4.3);
        row2.put("active", false);

        data.add(row2);
        jss.execute("INSERT VALUES ‘lastName’ = ‘Петров’ , ‘id’=1, ‘age’=30, ‘active’=true, ‘cost’=5.4");
        jss.execute("INSERT VALUES ‘lastName’ = ‘Иванов’ , ‘id’=2, ‘age’=25, ‘active’=false, ‘cost’=4.3");
        List<Map<String, Object>> selected = jss.execute("SELECT WHERE ‘age’<=25" );
        Assertions.assertIterableEquals(data, selected);

    }

    @Test
    public void selectMoreOrEqualsTest1() throws Exception {
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
        jss.execute("INSERT VALUES ‘lastName’ = ‘Иванов’ , ‘id’=2, ‘age’=25, ‘active’=false, ‘cost’=4.3");
        List<Map<String, Object>> selected = jss.execute("SELECT WHERE ‘age’>=30" );
        Assertions.assertIterableEquals(data, selected);

    }

    @Test
    public void selectLessTest1() throws Exception {
        JavaSchoolStarter jss = new JavaSchoolStarter();
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String,Object> row2 = new HashMap<>();
        row2.put("id",2L);
        row2.put("lastName","Иванов");
        row2.put("age",25L);
        row2.put("cost",4.3);
        row2.put("active", false);

        data.add(row2);
        jss.execute("INSERT VALUES ‘lastName’ = ‘Петров’ , ‘id’=1, ‘age’=30, ‘active’=true, ‘cost’=5.4");
        jss.execute("INSERT VALUES ‘lastName’ = ‘Иванов’ , ‘id’=2, ‘age’=25, ‘active’=false, ‘cost’=4.3");
        List<Map<String, Object>> selected = jss.execute("SELECT WHERE ‘age’<30" );
        Assertions.assertIterableEquals(data, selected);

    }

    @Test
    public void selectMoreTest1() throws Exception {
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
        jss.execute("INSERT VALUES ‘lastName’ = ‘Иванов’ , ‘id’=2, ‘age’=25, ‘active’=false, ‘cost’=4.3");
        List<Map<String, Object>> selected = jss.execute("SELECT WHERE ‘age’>25" );
        Assertions.assertIterableEquals(data, selected);

    }
}