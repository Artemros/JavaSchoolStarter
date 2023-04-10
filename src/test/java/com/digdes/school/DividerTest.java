package com.digdes.school;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DividerTest {//просмотр того, что получается на выходе у класса Divider
    @Test
    public void dividerTestInsert(){
        String request = "INSERT VALUES ‘lastName’ = ‘Федоров’ , ‘id’=3, ‘age’=40, ‘active’=true";
        ArrayList<ArrayList<String>> data = Divider.divide(request);
        System.out.println(data);
    }

    @Test
    public void dividerTestSelectMoreOrEquals(){
        String request = "SELECT WHERE ‘age’>=30";
        ArrayList<ArrayList<String>> data = Divider.divide(request);
        System.out.println(data);
    }
    @Test
    public void dividerTestSelectLessOrEquals(){
        String request = "SELECT WHERE ‘age’<=30";
        ArrayList<ArrayList<String>> data = Divider.divide(request);
        System.out.println(data);
    }
    @Test
    public void dividerTestSelectNotEquals(){
        String request = "SELECT WHERE ‘age’!=30";
        ArrayList<ArrayList<String>> data = Divider.divide(request);
        System.out.println(data);
    }
    @Test
    public void dividerTestSelectMore(){
        String request = "SELECT WHERE ‘age’>30";
        ArrayList<ArrayList<String>> data = Divider.divide(request);
        System.out.println(data);
    }

    @Test
    public void dividerTestUpdate(){
        String request = "UPDATE VALUES ‘active’=true  where ‘active’=false";
        ArrayList<ArrayList<String>> data = Divider.divide(request);
        System.out.println(data);
    }

}