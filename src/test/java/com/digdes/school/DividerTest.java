package com.digdes.school;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class DividerTest {
    @Test
    public void dividerTestInsert(){
        String request = "INSERT VALUES ‘lastName’ = ‘Федоров’ , ‘id’=3, ‘age’=40, ‘active’=true";
        Divider divider = new Divider();
        ArrayList<ArrayList<String>> data = divider.divide(request);
        System.out.println(data);
    }

    @Test
    public void dividerTestSelectMoreOrEquals(){
        String request = "SELECT WHERE ‘age’>=30";
        Divider divider = new Divider();
        ArrayList<ArrayList<String>> data = divider.divide(request);
        System.out.println(data);
    }
    @Test
    public void dividerTestSelectLessOrEquals(){
        String request = "SELECT WHERE ‘age’<=30";
        Divider divider = new Divider();
        ArrayList<ArrayList<String>> data = divider.divide(request);
        System.out.println(data);
    }
    @Test
    public void dividerTestSelectNotEquals(){
        String request = "SELECT WHERE ‘age’!=30";
        Divider divider = new Divider();
        ArrayList<ArrayList<String>> data = divider.divide(request);
        System.out.println(data);
    }
    @Test
    public void dividerTestSelectMore(){
        String request = "SELECT WHERE ‘age’>30";
        Divider divider = new Divider();
        ArrayList<ArrayList<String>> data = divider.divide(request);
        System.out.println(data);
    }

    @Test
    public void dividerTestUpdate(){
        String request = "UPDATE VALUES ‘active’=true  where ‘active’=false";
        Divider divider = new Divider();
        ArrayList<ArrayList<String>> data = divider.divide(request);
        System.out.println(data);
    }

}