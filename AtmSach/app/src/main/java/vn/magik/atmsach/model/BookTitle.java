package vn.magik.atmsach.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DucThanh on 11/25/2017.
 */

public class BookTitle implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("price")
    private double price;
    @SerializedName("list_price")
    private double listPrice;
    @SerializedName("discount_percent")
    private int discountPercent;
    @SerializedName("name")
    private String name;
    @SerializedName("sku")
    private String sku;
    @SerializedName("isbn")
    private String isbn;
    @SerializedName("logo")
    private String logo;
    @SerializedName("back_image")
    private String backImage;
    @SerializedName("left_image")
    private String leftImage;
    @SerializedName("right_image")
    private String rightImage;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("modify_at")
    private String modifyAt;
    @SerializedName("author")
    private String author;
    @SerializedName("star")
    private float star;
    @SerializedName("type")
    private String type;
    @SerializedName("description")
    private String description ;
    @SerializedName("info")
    private String info;

    public BookTitle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(String modifyAt) {
        this.modifyAt = modifyAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public String getLeftImage() {
        return leftImage;
    }

    public void setLeftImage(String leftImage) {
        this.leftImage = leftImage;
    }

    public String getRightImage() {
        return rightImage;
    }

    public void setRightImage(String rightImage) {
        this.rightImage = rightImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static class BookTitleResponse implements Serializable {
        @SerializedName("info")
        private List<BookTitle> bookTitleList;

        public List<BookTitle> getBookTitleList() {
            return bookTitleList;
        }

        public void setBookTitleList(List<BookTitle> bookTitleList) {
            this.bookTitleList = bookTitleList;
        }
    }
}
