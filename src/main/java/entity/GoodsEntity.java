package entity;

public class GoodsEntity {
    String name;
    int innerCode;
    public GoodsEntity(String name, int innerCode){
        this.name = name;
        this.innerCode = innerCode;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getInnerCode(){
        return innerCode;
    }
    public void setInnerCode(int innerCode){
        this.innerCode = innerCode;
    }
}
