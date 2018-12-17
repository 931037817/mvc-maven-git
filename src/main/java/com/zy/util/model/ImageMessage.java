package com.zy.util.model;

import com.thoughtworks.xstream.XStream;

public class ImageMessage extends BaseMessage{

	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image Image) {
		this.Image = Image;
	}
	
	 /**
     * 将对象转换为XML
     * @return
     */
    public String Msg2Xml(){
        XStream xstream=new XStream();
        xstream.alias("xml", this.getClass());
        xstream.alias("Image", Image.getClass());
        return xstream.toXML(this);
    }
}
