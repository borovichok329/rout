package com.example.ag.helpers;

public class Route {
    private String ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcom() {
        return idcom;
    }

    public void setIdcom(String idcom) {
        this.idcom = idcom;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    private String name;
    private String idcom;
    private String comname;
    int limit;

    public String getC_Name() {
        return C_Name;
    }

    public void setC_Name(String c_Name) {
        C_Name = c_Name;
    }

    public String getF_Provider() {
        return F_Provider;
    }

    public void setF_Provider(String f_Provider) {
        F_Provider = f_Provider;
    }

    public String getF_Provider___C_Name() {
        return F_Provider___C_Name;
    }

    public void setF_Provider___C_Name(String f_Provider___C_Name) {
        F_Provider___C_Name = f_Provider___C_Name;
    }

    public int getN_Limit() {
        return N_Limit;
    }

    public void setN_Limit(int n_Limit) {
        N_Limit = n_Limit;
    }

    private String C_Name;
    private String F_Provider;
    private String F_Provider___C_Name;
    private int N_Limit;




}
