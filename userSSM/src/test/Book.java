/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ZMoffice
 */
public class Book {
    private int id;
    private  String code;
    private  Integer Parent_id;
    private  int price;
    private  boolean is_book;
    private List<Book> childList = new ArrayList<>();

    public Book() {
    }

    public Book(int id, Integer Parent_id) {
        this.id = id;
        this.Parent_id = Parent_id;
    }

    public Book(int id, String code, Integer Parent_id, int price, boolean is_book) {
        this(id, Parent_id);
        this.code = code;
        this.price = price;
        this.is_book = is_book;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the Parent_id
     */
    public Integer getParent_id() {
        return Parent_id;
    }

    /**
     * @param Parent_id the Parent_id to set
     */
    public void setParent_id(Integer Parent_id) {
        this.Parent_id = Parent_id;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the is_book
     */
    public boolean isIs_book() {
        return is_book;
    }

    /**
     * @param is_book the is_book to set
     */
    public void setIs_book(boolean is_book) {
        this.is_book = is_book;
    }

    /**
     * @return the childList
     */
    public List<Book> getChildList() {
        return childList;
    }

    /**
     * @param childList the childList to set
     */
    public void setChildList(List<Book> childList) {
        this.childList = childList;
    }
    
}
