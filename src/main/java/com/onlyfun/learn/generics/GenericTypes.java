package com.onlyfun.learn.generics;

import java.util.List;

public class GenericTypes {
    public static void method(List<String> list) {  
        System.out.println("invoke method(List<String> list)");  
    }  

    public static void method(List<Integer> list) {  
        System.out.println("invoke method(List<Integer> list)");  
    } 
}
