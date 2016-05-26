package com.onlyfun.learn.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Created by dujiacheng on 2016/5/5.
 */
public class TestProducer {

	public static int  test(int a) throws Exception{
		int b = 1;
		try {
			b = 2 / a;
		} catch (Exception e) {
			throw e;
		}
		
		b++;
		return b;
	}
	
	
    public static void main(String[] args) {
    	try {
			System.out.println(test(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
