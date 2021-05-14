/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import static test.TreeUtil.getTree;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ZMoffice
 */
public class BookTest {

    public static void main(String[] args) {
        System.out.println(getPrice("高等数学"));
        System.out.println(getPrice("唐诗"));
        System.out.println(getPrice("数学类"));
        System.out.println(getPrice("教材类"));
        System.out.println(getPrice("教材类qwrwetq"));
    }

    public static Integer getPrice(String code) {
    	 //获取code对应的树 及其子结构数据
    	//    Book book = TreeUtil.getTree(code,TreeUtil.tree,null);
         Book book = TreeUtil.map.get(code);
        if (null == book) {
        	  //查询不到对应的树结构返回空值
            return null;
        }
      //根据树 , 递归相加price, 得到总价并返回
        return sumPrice(0, book);
    }

    public static int sumPrice(int price, Book book) {
    	 //递归得到每个树层的price ,并相加
    	price +=book.getPrice();
    	 //判断是否有子树
    	if (!CollectionUtils.isEmpty(book.getChildList())) {
            List<Book> nodes = book.getChildList();
            for (int i = 0; i < nodes.size(); i++) {
                Book node = nodes.get(i);
                //递归相加得到price
                price = sumPrice(price, node);
            }
        }
        return price;
    }
}
