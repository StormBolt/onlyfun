package com.onlyfun.learn.guava.collectiones;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;

import java.util.List;

/**
 * Created by dujiacheng on 2016/5/4.
 */
public class TestTables {

    public static void main(String[] args) {
      Table<String,String,String> table = HashBasedTable.create();
        table.put("1","2","AAA");
        table.put("1","2","BBB");
        table.put("2","2","CCC");

        System.out.println(table.row("2"));



        Multimap<String,Integer> mMap = ArrayListMultimap.create();
        mMap.put("1",232);
        mMap.put("1",231);
        mMap.put("2",233);

        List<Integer> values = (List<Integer>) mMap.get("1");
        System.out.println(values);


    }

}
