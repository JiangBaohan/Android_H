package com.example.classdemo;

/**
 * data:2017/7/20
 * author:汉堡(Administrator)
 * function:
 */
public class DataBean {
    //类型
    private int type;
    //文本数据
    private String texts;
    //图片数据
    private int images;


    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "type=" + type +
                ", texts='" + texts + '\'' +
                ", images=" + images +
                '}';
    }
}
