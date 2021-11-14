package entity;

import java.sql.Date;

public class WaybillPositionEntity {
    private int id;
    private int amount;
    private Float price;
    private int goodsID;
    private int waybillId;

    public WaybillPositionEntity(int id,int goodsID, Float price, int amount, int waybillId){
        this.id = id;
        this.goodsID = goodsID;
        this.price = price;
        this.amount = amount;
        this.waybillId = waybillId;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getWaybillId(){
        return waybillId;
    }
    public void setWaybillId(int waybillId){
        this.waybillId = waybillId;
    }

    public int getGoodsID(){
        return goodsID;
    }
    public void setGoodsID(int goodsID){
        this.goodsID = goodsID;
    }

    public Float getPrice(){
        return price;
    }
    public void setPrice(Float price){
        this.price = price;
    }

    public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }

    public int compareTo(WaybillPositionEntity wbp) {
        if (this.getAmount() > wbp.getAmount())
            return -1;
        return 1;
    }
}
