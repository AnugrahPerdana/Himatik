package com.model.pojo;
// Generated Oct 2, 2019 11:39:09 PM by Hibernate Tools 4.3.1



/**
 * Danlog generated by hbm2java
 */
public class Danlog  implements java.io.Serializable {


     private Integer idDanlog;
     private String judulDanlog;
     private String deskripsiKeroh;

    public Danlog() {
    }

    public Danlog(String judulDanlog, String deskripsiKeroh) {
       this.judulDanlog = judulDanlog;
       this.deskripsiKeroh = deskripsiKeroh;
    }
   
    public Integer getIdDanlog() {
        return this.idDanlog;
    }
    
    public void setIdDanlog(Integer idDanlog) {
        this.idDanlog = idDanlog;
    }
    public String getJudulDanlog() {
        return this.judulDanlog;
    }
    
    public void setJudulDanlog(String judulDanlog) {
        this.judulDanlog = judulDanlog;
    }
    public String getDeskripsiKeroh() {
        return this.deskripsiKeroh;
    }
    
    public void setDeskripsiKeroh(String deskripsiKeroh) {
        this.deskripsiKeroh = deskripsiKeroh;
    }




}


