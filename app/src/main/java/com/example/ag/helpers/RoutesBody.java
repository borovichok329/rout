package com.example.ag.helpers;

import java.util.ArrayList;

public class RoutesBody {
    public String action = "Domain.school_tourism_cs_routes";
    private ArrayList<RoutesFilter> data = new ArrayList<>();

    public RoutesBody() {
        data.add(new RoutesFilter());
    }


    public ArrayList<RoutesFilter> getData() {
        return data;
    }

    public void setData(ArrayList<RoutesFilter> data) {
        this.data = data;
    }



    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String tid = "c55dcb69-2293-bacd-8c9a-b9a56704cbf4";
    public String type = "rpc";
    public String method ="Query";

}
