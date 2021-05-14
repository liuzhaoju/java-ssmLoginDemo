package com.qicheng.ssm.utils;

import java.util.List;

/*
 * 建立JSON数组类ListObject
 */
public class ListObject extends AbstractJSON {
 
    private List<?> items;                       // 列表对象
 
	public List<?> getItems() {
		return items;
	}
 
	public void setItems(List<?> items) {
		this.items = items;
	}
    
}
