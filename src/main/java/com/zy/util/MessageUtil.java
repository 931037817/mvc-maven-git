package com.zy.util;

import java.util.Date;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import com.zy.util.model.Image;
import com.zy.util.model.ImageMessage;
import com.zy.util.model.TextMessage;

/**
* @desc: 自动回复消息根据不同的类型回复
* @author: jiangyuzhuang
* @createTime: 2018年12月11日 下午3:28:17
* @history:
* @version: v1.0
*/
public class MessageUtil {

	public static final String MESSAGE_TEXT = "text";// 文本
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_MUSIC = "music";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_SCANCODE = "scancode_push";
	
	public  static String appendReplyMes(Document doc){
		Element root = doc.getRootElement();
		//消息类型
		String msgType = XMLUtil.readNode(root, "MsgType");
		String fromUserName = XMLUtil.readNode(root, "FromUserName");
		String toUserName = XMLUtil.readNode(root, "ToUserName");	
		
		String returnStr = "";
		
		switch (msgType) {
		case MESSAGE_TEXT: {
			TextMessage message = new TextMessage();
			message.setFromUserName(toUserName);
			message.setToUserName(fromUserName);
			message.setContent("您输入的值为："+XMLUtil.readNode(root, "Content"));
			message.setMsgType(msgType);
			message.setCreateTime(new Date().getTime());
			 
			returnStr=message.Msg2Xml();
			
			break;
		}
		case MESSAGE_EVENT: {
			TextMessage message = new TextMessage();
			message.setFromUserName(toUserName);
			message.setToUserName(fromUserName);
			message.setContent("您好，欢迎关注~");
			message.setMsgType(MESSAGE_TEXT);
			message.setCreateTime(new Date().getTime());
			 
			returnStr=message.Msg2Xml();
			
			break;
		}
		case MESSAGE_IMAGE:{
			ImageMessage message = new ImageMessage();
			message.setFromUserName(toUserName);
			message.setToUserName(fromUserName);
			message.setMsgType(msgType);
			message.setCreateTime(new Date().getTime());
		    Image image = new Image();
		    image.setMediaId(XMLUtil.readNode(root, "MediaId"));
		    message.setImage(image);
		    
		    returnStr = message.Msg2Xml();
		    
		    break;
		}
		default:
			TextMessage message = new TextMessage();
			message.setFromUserName(toUserName);
			message.setToUserName(fromUserName);
			message.setContent("请输入正确的关键词\n暂时不支持："+msgTypeFormat(msgType));
			message.setMsgType(MESSAGE_TEXT);
			message.setCreateTime(new Date().getTime());
			 
			returnStr=message.Msg2Xml();
			
			break;
		}
		return returnStr;
	}
	
	
	/**
	* 获取接受消息的类型
	* @author: jiangyuzhuang
	* @createTime: 2018年12月11日 下午3:49:29
	* @history:
	* @param msgType
	* @return String
	*/
	public static String msgTypeFormat(String msgType){
		String returnStr = "";
		
		switch(msgType){
		case MESSAGE_TEXT:{
			returnStr = "文本";
			break;
		}
		case MESSAGE_NEWS:{
			returnStr = "新闻";
			break;
		}
		case MESSAGE_IMAGE:{
			returnStr = "图片";
			break;
		}
		case MESSAGE_MUSIC:{
			returnStr = "音乐";
			break;
		}
		case MESSAGE_VOICE:{
			returnStr = "音频";
			break;
		}
		case MESSAGE_VIDEO:{
			returnStr = "视频";
			break;
		}
		default:
			returnStr = "其他格式";
			break;
		}
		return returnStr;
	}
}
