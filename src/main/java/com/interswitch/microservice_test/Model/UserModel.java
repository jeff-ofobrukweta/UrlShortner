package com.interswitch.microservice_test.Model;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserModel implements Serializable {

    private String Position;
    @NotNull(message = "A url is required in this field")
    @Length(min =3,max =50)
    private String Url;
    private String Email;
    private int RoomNumber;
    private String Username;
    private String id;
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getRoomNumber() {return RoomNumber; }

    public void setRoomNumber(int roomNumber) {
        RoomNumber = roomNumber;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "Position='" + Position + '\'' +
                ", Url='" + Url + '\'' +
                ", Email='" + Email + '\'' +
                ", RoomNumber=" + RoomNumber +
                ", Username='" + Username + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }


    public UserModel(String username, String position, String url, String email, int roomNumber,String id) {
        Username = username;
        Position = position;
        Url = url;
        Email = email;
        RoomNumber = roomNumber;
        this.id = id;
    }

    public UserModel(){}

}
