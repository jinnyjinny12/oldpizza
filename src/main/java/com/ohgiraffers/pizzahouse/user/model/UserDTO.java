package com.ohgiraffers.pizzahouse.user.model;


public class UserDTO {

    private Integer userId;
    private String userName; // 이름
    private int userAge; // 나이
    private int postCode; // 우편번호

    private String address; //기본주소
    private String addressDetail; //상세주소

    private String status;



    public UserDTO() {
    }


    public UserDTO(Integer userId, String userName, int userAge, int postCode, String address, String adderessDetail) {


        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
        this.postCode = postCode;
        this.address = address;
        this.addressDetail = adderessDetail;
        this.status = status;

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", postCode=" + postCode +
                ", adderess='" + adderess + '\'' +
                ", adderessDetail='" + adderessDetail + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}