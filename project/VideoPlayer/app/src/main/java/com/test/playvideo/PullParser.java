package com.test.playvideo;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PullParser {
    //解析xml文件
    public static List<Message> pull2xml(InputStream is) throws Exception{
        List<Message> list = null;
        Message msg = null;
        //创建xml解析器
        XmlPullParser parser = Xml.newPullParser();
        //初始化解析器
        parser.setInput(is,"utf-8");
        //读取文件类型
        int type = parser.getEventType();
        while(type!=XmlPullParser.END_DOCUMENT){
            switch(type){
                case XmlPullParser.START_TAG:
                    if("messages".equals(parser.getName())){
                        list = new ArrayList<>();
                    }else if("message".equals(parser.getName())){
                        msg = new Message();
                    }else if("_id".equals(parser.getName())){
                        String id = parser.nextText();
                        msg.setId(id);
                    }else if("feedurl".equals(parser.getName())){
                        String feedurl = parser.nextText();
                        msg.setFeedurl(feedurl);
                    }else if("nickname".equals(parser.getName())){
                        String nickname = parser.nextText();
                        msg.setNickname(nickname);
                    }else if("description".equals(parser.getName())){
                        String des = parser.nextText();
                        msg.setDescription(des);
                    }else if("likecount".equals(parser.getName())){
                        String likcount = parser.nextText();
                        int like = Integer.parseInt(likcount);
                        msg.setLikecount(like);
                    }else if("avatar".equals(parser.getName())){
                        String avatar = parser.nextText();
                        msg.setAvactar(avatar);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if("message".equals(parser.getName())){
                        list.add(msg);
                    }
                    break;
            }
            type = parser.next();
        }
    return list;
    }

}
