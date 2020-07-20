package com.twainlabs.twainnews;

public class ArticleModel {
    String articleImg, articleHL, articleSource, articleDate;

    public ArticleModel(String articleImg, String articleHL, String articleSource, String articleDate) {
        this.articleImg = articleImg;
        this.articleHL = articleHL;
        this.articleSource = articleSource;
        this.articleDate = articleDate;
    }

    public String getArticleImg() {
        return articleImg;
    }

    public String getArticleHL() {
        return articleHL;
    }

    public String getArticleSource() {
        return articleSource;
    }

    public String getArticleDate() {
        return articleDate;
    }

}
