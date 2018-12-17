package com.zy.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @desc: 消息内容格式转换
 * @author: jiangyuzhuang
 * @createTime: 2018年12月11日 下午2:24:11
 * @history:
 * @version: v1.0
 */
public class XMLUtil {

	public static String content = "";

	/**
     * xml 转 map
     * 
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
	public static Document xmlToDoc(HttpServletRequest request)
            throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);
        return doc;
    }
    
	/**
	 * 读取根节点下每一个节点信息
	 * 
	 * @param node
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String readNodes(Element node) {
		content += node.getName() + ":" + node.getTextTrim() + "\n";
		// 递归遍历当前节点所有的子节点
		List<Element> listElement = node.elements();// 所有一级子节点的list
		for (Element e : listElement) {// 遍历所有一级子节点
			readNodes(e);// 递归
		}
		return content;
	}

	/**
	 * 读取单个节点信息
	 * 
	 * @param node
	 * @param name
	 * @return
	 */
	public static String readNode(Element node, String name) {
		Element e = node.element(name);
		String nodeString = e.getTextTrim();
		return nodeString;

	}
}
