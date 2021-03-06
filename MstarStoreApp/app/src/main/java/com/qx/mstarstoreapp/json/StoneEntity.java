package com.qx.mstarstoreapp.json;

/**
 * Created by Administrator on 2016/10/19.
 */
public class StoneEntity implements Cloneable {
    /**
     * specId : 1
     * number : null
     * shapeId : 3
     * purityTitle : SI
     * shapeTitle : 马形
     * colorId : 1
     * typeId : 1
     * specTitle : 10
     * colorTitle : H+
     * typeTitle : 钻石
     * purityId : 1
     */

    private boolean isChecked;
    private String stroneName;
    private String price;
    private String specId;
    private String number;
    private String shapeId;
    private String purityTitle;
    private String shapeTitle;
    private String colorId;
    private String typeId;
    private String specTitle;
    private String colorTitle;
    private String typeTitle;
    private String purityId;
    private String specSelectTitle;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getSpecSelectTitle() {
        return specSelectTitle;
    }

    public void setSpecSelectTitle(String specSelectTitle) {
        this.specSelectTitle = specSelectTitle;
    }

    public String getStroneName() {
        return stroneName;
    }

    public void setStroneName(String stroneName) {
        this.stroneName = stroneName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setShapeId(String shapeId) {
        this.shapeId = shapeId;
    }

    public void setPurityTitle(String purityTitle) {
        this.purityTitle = purityTitle;
    }

    public void setShapeTitle(String shapeTitle) {
        this.shapeTitle = shapeTitle;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public void setSpecTitle(String specTitle) {
        this.specTitle = specTitle;
    }

    public void setColorTitle(String colorTitle) {
        this.colorTitle = colorTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    public void setPurityId(String purityId) {
        this.purityId = purityId;
    }

    public String getSpecId() {
        return specId;
    }

    public String getNumber() {
        return number;
    }

    public String getShapeId() {
        return shapeId;
    }

    public String getPurityTitle() {
        return purityTitle;
    }

    public String getShapeTitle() {
        return shapeTitle;
    }

    public String getColorId() {
        return colorId;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getSpecTitle() {
        return specTitle;
    }

    public String getColorTitle() {
        return colorTitle;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public String getPurityId() {
        return purityId;
    }

    @Override
    public String toString() {
        return "StoneEntity{" +
                "stroneName='" + stroneName + '\'' +
                ", price='" + price + '\'' +
                ", specId='" + specId + '\'' +
                ", number='" + number + '\'' +
                ", shapeId='" + shapeId + '\'' +
                ", purityTitle='" + purityTitle + '\'' +
                ", shapeTitle='" + shapeTitle + '\'' +
                ", colorId='" + colorId + '\'' +
                ", typeId='" + typeId + '\'' +
                ", specTitle='" + specTitle + '\'' +
                ", colorTitle='" + colorTitle + '\'' +
                ", typeTitle='" + typeTitle + '\'' +
                ", purityId='" + purityId + '\'' +
                ", specSelectTitle='" + specSelectTitle + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
