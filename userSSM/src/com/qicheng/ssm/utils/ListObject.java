package com.qicheng.ssm.utils;

import java.util.List;

/*
 * ����JSON������ListObject
 */
public class ListObject extends AbstractJSON {
 
    private List<?> items;                       // �б�����
 
	public List<?> getItems() {
		return items;
	}
 
	public void setItems(List<?> items) {
		this.items = items;
	}
    
}