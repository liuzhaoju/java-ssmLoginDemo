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

	// �����������νṹ����
	public static List<Book> buildTree(List<Book> nodes) {
		Map<Integer, List<Book>> sub = nodes.stream().filter(node -> node.getParent_id() != null)
				.collect(Collectors.groupingBy(node -> node.getParent_id()));
		nodes.forEach(node -> node.setChildList(sub.get(node.getId())));
		return nodes.stream().filter(node -> node.getParent_id() == null).collect(Collectors.toList());
	}

	// ��̬����� , �������ݽ����ڴ棬Ȼ�󹹽���һ�����νṹ
	static {
		List<Book> nodes = new ArrayList<>();
		nodes.add(new Book(1, "�ߵ���ѧ", 5, 10, true));
		nodes.add(new Book(2, "���Դ���", 5, 20, true));
		nodes.add(new Book(3, "��ʫ", 6, 15, true));
		nodes.add(new Book(4, "�δ�", 6, 14, true));
		nodes.add(new Book(5, "��ѧ��", 7, -3, false));
		nodes.add(new Book(6, "������", 7, -2, false));
		nodes.add(new Book(7, "�̲���", null, -5, false));
		tree = buildTree(nodes);
		getTreeMap(tree, null);
	}

	public static void getTreeMap(List<Book> nodes, Book node) {
		//����
		for (int i = 0; i < nodes.size(); i++) {
			Book book = nodes.get(i);
			//��Book����Ӧ��������map�� , �����߰�code��ѯBook��
			map.put(book.getCode(), book);
			// �жϵ�ǰ���Ƿ�������
			if (!CollectionUtils.isEmpty(book.getChildList())) {
				// �ݹ��ѯ
				getTreeMap(book.getChildList(), node);
			}
		}

	}

	public static Book getTree(String code, List<Book> nodes, Book node) {
		for (int i = 0; i < nodes.size(); i++) {
			Book book = nodes.get(i);
			// �жϵ�ǰ��code�봫��Ĳ����Ƿ���� , �������ֵ
			if (book.getCode().equals(code)) {
				node = book;
			} else {
				// �жϵ�ǰ���Ƿ�������
				if (!CollectionUtils.isEmpty(book.getChildList())) {
					// �ݹ��ѯcode ,������
					Book ook = getTree(code, book.getChildList(), node);
					// �жϷ���ֵ�Ƿ�Ϊ�� ,����ֵ
					if (null != ook) {
						node = ook;
					}
				}
			}
		}
		// ������ֵΪ�� , ��ǰ��δ��ѯ��code��Ӧ������
		return node;
	}
}
