package entity;

public class OrganizationEntity {
    String name;
    int inn;
    Long checkingAccount;

    public OrganizationEntity(String name, int inn, Long checkingAccount){
        this.name = name;
        this.inn = inn;
        this.checkingAccount = checkingAccount;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getInn(){
        return inn;
    }
    public void setInn(int inn){
        this.inn = inn;
    }

    public Long getCheckingAccount(){
        return checkingAccount;
    }
    public void setCheckingAccount(Long checkingAccount){
        this.checkingAccount = checkingAccount;
    }
}
