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
        System.out.println(getPrice("�ߵ���ѧ"));
        System.out.println(getPrice("��ʫ"));
        System.out.println(getPrice("��ѧ��"));
        System.out.println(getPrice("�̲���"));
        System.out.println(getPrice("�̲���qwrwetq"));
    }

    public static Integer getPrice(String code) {
    	 //��ȡcode��Ӧ���� �����ӽṹ����
    	//    Book book = TreeUtil.getTree(code,TreeUtil.tree,null);
         Book book = TreeUtil.map.get(code);
        if (null == book) {
        	  //��ѯ������Ӧ�����ṹ���ؿ�ֵ
            return null;
        }
      //������ , �ݹ����price, �õ��ܼ۲�����
        return sumPrice(0, book);
    }

    public static int sumPrice(int price, Book book) {
    	 //�ݹ�õ�ÿ�������price ,�����
    	price +=book.getPrice();
    	 //�ж��Ƿ�������
    	if (!CollectionUtils.isEmpty(book.getChildList())) {
            List<Book> nodes = book.getChildList();
            for (int i = 0; i < nodes.size(); i++) {
                Book node = nodes.get(i);
                //�ݹ���ӵõ�price
                price = sumPrice(price, node);
            }
        }
        return price;
    }
}
