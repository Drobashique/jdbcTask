package entity;

import java.sql.Date;

public class WaybillEntity {
    private int id;
    private Date orgDate;
    private int orgInn;

    public WaybillEntity(int id, Date orgDate, int orgInn){
        this.id = id;
        this.orgDate = orgDate;
        this.orgInn = orgInn;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public Date getOrgDate(){
        return orgDate;
    }
    public void setOrgDate(Date orgDate){
        this.orgDate = orgDate;
    }

    public int getOrgInn(){
        return orgInn;
    }
    public void setOrgInn(int orgInn){
        this.orgInn = orgInn;
    }
}
