package com.gwy.bean;

import java.io.Serializable;

public class User  implements  Cloneable, Serializable {

    private String id;

    private String name;

    private String tel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public  Object clone() throws  CloneNotSupportedException{
        return super.clone();
    }

    public boolean equals(Object obj) {
        User user = (User) obj;
        if(id.equals(user.getId()) && name.equals(user.getName()) && tel.equals(user.getTel())){
            return true;
        }
        return false;
    }


    public String toString() {
        return this.getId()+"--"+this.getName()+"----"+this.getTel();
    }

}