package com.model.pojo;
// Generated Oct 2, 2019 11:39:09 PM by Hibernate Tools 4.3.1



/**
 * Sospol generated by hbm2java
 */
public class Sospol  implements java.io.Serializable {


     private Integer idSospol;
     private String judulSospol;
     private String deskripsiSospol;

    public Sospol() {
    }

    public Sospol(String judulSospol, String deskripsiSospol) {
       this.judulSospol = judulSospol;
       this.deskripsiSospol = deskripsiSospol;
    }
   
    public Integer getIdSospol() {
        return this.idSospol;
    }
    
    public void setIdSospol(Integer idSospol) {
        this.idSospol = idSospol;
    }
    public String getJudulSospol() {
        return this.judulSospol;
    }
    
    public void setJudulSospol(String judulSospol) {
        this.judulSospol = judulSospol;
    }
    public String getDeskripsiSospol() {
        return this.deskripsiSospol;
    }
    
    public void setDeskripsiSospol(String deskripsiSospol) {
        this.deskripsiSospol = deskripsiSospol;
    }




}

