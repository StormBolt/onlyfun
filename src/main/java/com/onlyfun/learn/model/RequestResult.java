package com.onlyfun.learn.model;


import java.util.HashMap;
import java.util.Map;

public class RequestResult{
	private int error;
	//耗时
	private double cost;
	
	//描述
	private String msg;
	
	private Map<String, Object> map=new HashMap<String, Object>();


	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
		map.put("error", error);
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
		map.put("cost", cost);
	}

	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
		map.put("msg", msg);
	}
	
	public void set(String key,Object value){
		map.put(key, value);
	}
	
	public void addAll(Map<String, Object> map){
		this.map.putAll(map);
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}



}
