package com.planitestexam.uitest;

public class ContactForm {

    private String forename="";
    private String surname="";
    private String telephone="";
    private String email="";
    private String message="";

    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getForename(){
        return forename;
    };

    public String getSurname(){
        return surname;
    };

    public String getTelephone(){
        return telephone;
    };

    public String getEmail(){
        return email;
    };

    public String getMessage(){
        return message;
    };

}
