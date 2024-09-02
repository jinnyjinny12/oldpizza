package com.ohgiraffers.pizzahouse.user.model;


import com.ohgiraffers.pizzahouse.utill.Utills;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "user_name", nullable = false)
    private String userName; // 이름

    @Column(name = "user_age", nullable = false)
    private Integer userAge; // 나이

    @Column(name = "user_postcode")
    private int postCode; // 우편번호

    @Column(name = "user_address", nullable = false)
    private String address; //기본주소

    @Column(name = "user_address_detail")
    private String addressDetail; //상세주소

    @Column(name = "user_status")
    private int status;


    public UserEntity() {
    }

    public static class Builder {
        private String userName;
        private Integer userAge;
        private int postCode;

        private String address;
        private String addressDetail;

        private int status;


        public UserEntity builder() {
            if (userName == null || userAge == null || address == null || addressDetail == null) {
                throw new IllegalArgumentException("곳간을 채워주세요");
            }
            return new UserEntity(this);
        }

        public Builder setUserName(String userName) {

            if (userName == null || userName.isEmpty()) {
                throw new IllegalArgumentException("입력해 주세요");
            } else if (!Utills.regex(userName)) {
                throw new IllegalArgumentException("한글로 입력해 주세요");
            } else if (userName.length() > 3) {
                throw new IllegalArgumentException("3글자 이하로 입력해 주세요");
            }else {
                this.userName = userName;
                return this;
            }
        }
        public Builder setUserAge(Integer userAge) {
            if (userAge == null || userAge < 0) {
                throw new IllegalArgumentException("입력해 주세요");
            }else if (userAge < 20) {
                throw new IllegalArgumentException("20세 이상만 가입이 가능합니다.");
            }else {
                this.userAge = userAge;
                return this;
            }
        }
        public Builder setPostCode(int postCode) {
            this.postCode = postCode;
            return this;
        }
        public Builder setAdderess(String adderess) {
            this.address = adderess;
            return this;
        }
        public Builder setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
            return this;
        }

    }
    public UserEntity(Builder builder) {
        this.userName = builder.userName;
        this.userAge = builder.userAge;
        this.postCode = builder.postCode;

        this.address = builder.address;
        this.addressDetail = builder.addressDetail;

        this.status = builder.status;

    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public int getPostCode() {
        return postCode;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddressDetail(String addrssDetail) {
        this.addressDetail = addrssDetail;
    }

    public int getStatus() {return status; }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAge='" + userAge + '\'' +
                ", postCode=" + postCode +
                ", address='" + address + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                '}';
    }
}
