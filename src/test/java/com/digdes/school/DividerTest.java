package com.digdes.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class DividerTest {//просмотр того, что получается на выходе у класса Divider, не автоматизированные тесты

    @Test
    public void dividerTestInsert() {
        String request = "INSERT VALUES ‘lastName’ = ‘Федоров’ , ‘id’=3, ‘age’=40, ‘active’=true";
        RequestParameters data = Divider.divide(request);
        System.out.println("Command: " + data.operation());
        System.out.println("Values: " + data.values());
        System.out.println("Where: " + data.where());
    }

    @Test
    public void dividerTestSelectMoreOrEquals() {
        String request = "SELECT WHERE ‘age’>=30";
        RequestParameters data = Divider.divide(request);
        System.out.println("Command: " + data.operation());
        System.out.println("Values: " + data.values());
        System.out.println("Where: " + data.where());
    }

    @Test
    public void dividerTestSelectLessOrEquals() {
        String request = "SELECT WHERE ‘age’<=30";
        RequestParameters data = Divider.divide(request);
        System.out.println("Command: " + data.operation());
        System.out.println("Values: " + data.values());
        System.out.println("Where: " + data.where());
    }

    @Test
    public void dividerTestSelectNotEquals() {
        String request = "SELECT WHERE ‘age’!=30";
        RequestParameters data = Divider.divide(request);
        System.out.println("Command: " + data.operation());
        System.out.println("Values: " + data.values());
        System.out.println("Where: " + data.where());
    }

    @Test
    public void dividerTestSelectMore() {
        String request = "SELECT WHERE ‘age’>30";
        RequestParameters data = Divider.divide(request);
        System.out.println("Command: " + data.operation());
        System.out.println("Values: " + data.values());
        System.out.println("Where: " + data.where());
    }

    @Test
    public void dividerTestUpdate() {
        String request = "UPDATE VALUES ‘active’=true  where ‘active’=false";
        RequestParameters data = Divider.divide(request);
        System.out.println("Command: " + data.operation());
        System.out.println("Values: " + data.values());
        System.out.println("Where: " + data.where());
    }

    @Test
    public void dividerTestUpdateOrAndWhere() {
        String request = "UPDATE VALUES ‘active’=true, ‘lastName’ = ‘Egor Andrew Where’  where ‘active’=false or ‘age’>30 and ‘lastName’ = ‘Egor Andrew Where’ or ‘age’<20 and ‘active’=true";
        RequestParameters data = Divider.divide(request);
        System.out.println("Command: " + data.operation());
        System.out.println("Values: " + data.values());
        System.out.println("Where: " + data.where());
    }

    @Test
    public void dividerTestInsertOrAnd() {
        String request = "INSERT VALUES ‘lastName’ = ‘Egor Andrew Where’ , ‘id’=1, ‘age’=30, ‘active’=true, ‘cost’=5.4";
        RequestParameters data = Divider.divide(request);
        System.out.println("Command: " + data.operation());
        System.out.println("Values: " + data.values());
        System.out.println("Where: " + data.where());
    }

    @Test
    public void parseValuesTest() {
        Divider divider = new Divider();
        Map<String, Object> values = divider.parseValues(" ‘lastName’ = ‘Петров’ , ‘id’=1, ‘age’=30, ‘active’=true, ‘cost’=5.4  ");
        Map<String, Object> row1 = new HashMap<>();
        row1.put("id", 1L);
        row1.put("lastName", "Петров");
        row1.put("age", 30L);
        row1.put("cost", 5.4);
        row1.put("active", true);
        Assertions.assertEquals(row1, values);
    }

    @Test
    public void parseUpdateTest() {
        Divider divider = new Divider();
        Map<String, Object> values = divider.parseValues(" ‘lastName’ = ‘Петров’ , ‘id’=1, ‘age’=30, ‘active’=true, ‘cost’=5.4  ");
        Map<String, Object> row1 = new HashMap<>();
        row1.put("id", 1L);
        row1.put("lastName", "Петров");
        row1.put("age", 30L);
        row1.put("cost", 5.4);
        row1.put("active", true);
        Assertions.assertEquals(row1, values);
    }
}