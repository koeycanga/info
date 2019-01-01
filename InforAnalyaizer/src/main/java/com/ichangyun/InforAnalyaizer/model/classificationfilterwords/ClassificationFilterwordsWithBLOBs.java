/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model.classificationfilterwords;

import java.util.Date;

public class ClassificationFilterwordsWithBLOBs extends ClassificationFilterwords {
    private String allcorephrases = "";

    private String allexcludephrases = "";

    private String titlecorephrases = "";

    private String titleexcludephrases = "";

    private String summarycorephrases = "";

    private String summaryexcludephrases = "";

    private String textcorephrases = "";

    private String textexcludephrases = "";

    public String getAllcorephrases() {
        return allcorephrases;
    }

    public void setAllcorephrases(String allcorephrases) {
        this.allcorephrases = allcorephrases == null ? null : allcorephrases.trim();
    }

    public String getAllexcludephrases() {
        return allexcludephrases;
    }

    public void setAllexcludephrases(String allexcludephrases) {
        this.allexcludephrases = allexcludephrases == null ? null : allexcludephrases.trim();
    }

    public String getTitlecorephrases() {
        return titlecorephrases;
    }

    public void setTitlecorephrases(String titlecorephrases) {
        this.titlecorephrases = titlecorephrases == null ? null : titlecorephrases.trim();
    }

    public String getTitleexcludephrases() {
        return titleexcludephrases;
    }

    public void setTitleexcludephrases(String titleexcludephrases) {
        this.titleexcludephrases = titleexcludephrases == null ? null : titleexcludephrases.trim();
    }

    public String getSummarycorephrases() {
        return summarycorephrases;
    }

    public void setSummarycorephrases(String summarycorephrases) {
        this.summarycorephrases = summarycorephrases == null ? null : summarycorephrases.trim();
    }

    public String getSummaryexcludephrases() {
        return summaryexcludephrases;
    }

    public void setSummaryexcludephrases(String summaryexcludephrases) {
        this.summaryexcludephrases = summaryexcludephrases == null ? null : summaryexcludephrases.trim();
    }

    public String getTextcorephrases() {
        return textcorephrases;
    }

    public void setTextcorephrases(String textcorephrases) {
        this.textcorephrases = textcorephrases == null ? null : textcorephrases.trim();
    }

    public String getTextexcludephrases() {
        return textexcludephrases;
    }

    public void setTextexcludephrases(String textexcludephrases) {
        this.textexcludephrases = textexcludephrases == null ? null : textexcludephrases.trim();
    }

    public ClassificationFilterwordsWithBLOBs(String classificationId, String informationtropism, String createuser,
            Date createdatetime, String updateuser, String updatedatetime, String allcorephrases,
            String allexcludephrases, String titlecorephrases, String titleexcludephrases, String summarycorephrases,
            String summaryexcludephrases, String textcorephrases, String textexcludephrases) {
        super(classificationId, informationtropism, createuser, createdatetime, updateuser, updatedatetime);
        this.allcorephrases = allcorephrases;
        this.allexcludephrases = allexcludephrases;
        this.titlecorephrases = titlecorephrases;
        this.titleexcludephrases = titleexcludephrases;
        this.summarycorephrases = summarycorephrases;
        this.summaryexcludephrases = summaryexcludephrases;
        this.textcorephrases = textcorephrases;
        this.textexcludephrases = textexcludephrases;
    }

    public ClassificationFilterwordsWithBLOBs() {
        super();
    }

}