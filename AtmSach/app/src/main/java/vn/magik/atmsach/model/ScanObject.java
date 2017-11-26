package vn.magik.atmsach.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by DucThanh on 11/25/2017.
 */

public class ScanObject extends RealmObject implements Serializable{
    @PrimaryKey
    private int id;
//    private String listImage;
    private String imageFront;
    private String imageBack;
    private String imageLeft;
    private String imageRight;
    private String nameBook;
    private int status;
    private int countImage = 0;

    public ScanObject(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCountImage() {
        return countImage;
    }

    public void setCountImage(int countImage) {
        this.countImage = countImage;
    }

    public String getImageFront() {
        return imageFront;
    }

    public void setImageFront(String imageFront) {
        this.imageFront = imageFront;
    }

    public String getImageBack() {
        return imageBack;
    }

    public void setImageBack(String imageBack) {
        this.imageBack = imageBack;
    }

    public String getImageLeft() {
        return imageLeft;
    }

    public void setImageLeft(String imageLeft) {
        this.imageLeft = imageLeft;
    }

    public String getImageRight() {
        return imageRight;
    }

    public void setImageRight(String imageRight) {
        this.imageRight = imageRight;
    }
}
