package com.zy.util.model;

import com.thoughtworks.xstream.XStream;

public class TextMessage extends BaseMessage{

	private String Content;

	
	public String getContent() {
		return Content;
	}


	public void setContent(String content) {
		Content = content;
	}


	/**
     * 将对象转换为XML
     * @return
     */
    public String Msg2Xml(){
        XStream xstream=new XStream();
        xstream.alias("xml", this.getClass());
        return xstream.toXML(this);
    }
}
