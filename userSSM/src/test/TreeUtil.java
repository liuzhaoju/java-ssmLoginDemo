/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ZMoffice
 */
public class TreeUtil {

	public static List<Book> tree = null;
	public static Map<String, Book> map = new HashMap<String, Book>();

	// 关联构建树形结构方法
	public static List<Book> buildTree(List<Book> nodes) {
		Map<Integer, List<Book>> sub = nodes.stream().filter(node -> node.getParent_id() != null)
				.collect(Collectors.groupingBy(node -> node.getParent_id()));
		nodes.forEach(node -> node.setChildList(sub.get(node.getId())));
		return nodes.stream().filter(node -> node.getParent_id() == null).collect(Collectors.toList());
	}

	// 静态代码块 , 加载数据进入内存，然后构建成一个树形结构
	static {
		List<Book> nodes = new ArrayList<>();
		nodes.add(new Book(1, "高等数学", 5, 10, true));
		nodes.add(new Book(2, "线性代数", 5, 20, true));
		nodes.add(new Book(3, "唐诗", 6, 15, true));
		nodes.add(new Book(4, "宋词", 6, 14, true));
		nodes.add(new Book(5, "数学类", 7, -3, false));
		nodes.add(new Book(6, "语文类", 7, -2, false));
		nodes.add(new Book(7, "教材类", null, -5, false));
		tree = buildTree(nodes);
		getTreeMap(tree, null);
	}

	public static void getTreeMap(List<Book> nodes, Book node) {
		//遍历
		for (int i = 0; i < nodes.size(); i++) {
			Book book = nodes.get(i);
			//将Book及对应子树放入map中 , 方便后边按code查询Book树
			map.put(book.getCode(), book);
			// 判断当前树是否有子树
			if (!CollectionUtils.isEmpty(book.getChildList())) {
				// 递归查询
				getTreeMap(book.getChildList(), node);
			}
		}

	}

	public static Book getTree(String code, List<Book> nodes, Book node) {
		for (int i = 0; i < nodes.size(); i++) {
			Book book = nodes.get(i);
			// 判断当前树code与传入的参数是否相等 , 若想等则赋值
			if (book.getCode().equals(code)) {
				node = book;
			} else {
				// 判断当前树是否有子树
				if (!CollectionUtils.isEmpty(book.getChildList())) {
					// 递归查询code ,并返回
					Book ook = getTree(code, book.getChildList(), node);
					// 判断返回值是否不为空 ,并赋值
					if (null != ook) {
						node = ook;
					}
				}
			}
		}
		// 若返回值为空 , 则当前树未查询到code对应的子树
		return node;
	}
}
