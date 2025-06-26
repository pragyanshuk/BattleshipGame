package models;

public class Player extends BaseModel{
    private String name;
    private Integer safeShipCnt;

    public Player(String name){
        this.name = name;
        this.safeShipCnt = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSafeShipCnt() {
        return safeShipCnt;
    }

    public void setSafeShipCnt(Integer safeShipCnt) {
        this.safeShipCnt = safeShipCnt;
    }
}
