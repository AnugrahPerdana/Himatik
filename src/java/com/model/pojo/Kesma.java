package com.model.pojo;
// Generated Oct 2, 2019 11:39:09 PM by Hibernate Tools 4.3.1



/**
 * Kesma generated by hbm2java
 */
public class Kesma  implements java.io.Serializable {


     private Integer idKesma;
     private String judulKesma;
     private String deskripsiKesma;

    public Kesma() {
    }

    public Kesma(String judulKesma, String deskripsiKesma) {
       this.judulKesma = judulKesma;
       this.deskripsiKesma = deskripsiKesma;
    }
   
    public Integer getIdKesma() {
        return this.idKesma;
    }
    
    public void setIdKesma(Integer idKesma) {
        this.idKesma = idKesma;
    }
    public String getJudulKesma() {
        return this.judulKesma;
    }
    
    public void setJudulKesma(String judulKesma) {
        this.judulKesma = judulKesma;
    }
    public String getDeskripsiKesma() {
        return this.deskripsiKesma;
    }
    
    public void setDeskripsiKesma(String deskripsiKesma) {
        this.deskripsiKesma = deskripsiKesma;
    }




}


