package com.dataql.godstady.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by godstady on 2020/11/19.
 */
public class StadyTest {



    public void test(){

    }

    public static void main(String[] args) {

        List<Integer> list =  new ArrayList<>();
        list.add(2);
        //list.add("a");
        Class<? extends List> aClass = list.getClass();
        try {
            Method add = aClass.getDeclaredMethod("add", Object.class);
            add.invoke(list,"aa");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        System.out.println(list);

        String s1  = "qq";
        String s2  = "qq";

        System.out.println(s1==s2);

        System.out.println(s1.equals(s2));
        System.out.println("-------------------");

        String s3 = new String("qq");
        String s4 = new String("qq");

        System.out.println(s3==s4);
        System.out.println(s3.equals(s4));

        System.out.println("-------------------");
        System.out.println(s1==s3);

        System.out.println(s1.equals(s3));

        System.out.println(s3+"---"+s4);
    }
}
