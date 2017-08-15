package com.pm.cms.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/*
 * 网站内容
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Help_Content")
public class HelpContent extends MetaObject {

    /**
     * 所属栏目
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "column_ID")
    private HelpColumns cmsColumns;

    /**
     * 内容缩略图
     */
    @Column(name = "Img")
    private String img;

    /**
     * 内容大图
     */
    @Column(name = "big_img")
    private String bigImg;

    /**
     * 内容标题
     */
    @Column(name = "tile")
    private String tile;

    /**
     * 内容详情
     */
    @Column(name = "content")
    private String content;

    /**
     * 内容顺序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 是否启用
     */
    @Column(name = "status")
    private Integer status;


    /**
     * 关键字
     */
    @Column(name = "key_word")
    private String keyWord;


 


    public HelpColumns getCmsColumns() {
        return cmsColumns;
    }


    public void setCmsColumns(HelpColumns cmsColumns) {
        this.cmsColumns = cmsColumns;
    }


    public String getImg() {
        return img;
    }


    public void setImg(String img) {
        this.img = img;
    }


    public String getBigImg() {
        return bigImg;
    }


    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
    }


    public String getTile() {
        return tile;
    }


    public void setTile(String tile) {
        this.tile = tile;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public Integer getSort() {
        return sort;
    }


    public void setSort(Integer sort) {
        this.sort = sort;
    }


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getKeyWord() {
        return keyWord;
    }


    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

   

}
