package ie.ucd.csnl.comp47500.impl;

import java.util.Arrays;

import ie.ucd.csnl.comp47500.api.Contact;

public class ContactImpl implements Contact {

    private String id;
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String photoUri;
    private String organisation;
    private String[] secondaryEmails;
    private boolean favorite;
    
    public ContactImpl() {}
    
    public ContactImpl(String id, String name, String phoneNumber, String organisation, String emailAddress) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.organisation = organisation;
        this.emailAddress = emailAddress;
    }

    @Override
    public int compareTo(Contact o) {
        int value = this.getName().compareTo(o.getName());
        return (value == 0) ? this.getId().compareTo(o.getId()) : value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNumber() {
        return phoneNumber;
    }

    @Override
    public String getEmail() {
        return emailAddress;
    }

    @Override
    public String getPhotoUri() {
        return photoUri;
    }

    @Override
    public String getOrganisation() {
        return organisation;
    }

    @Override
    public String[] getSecondaryEmails() {
        return secondaryEmails;
    }

    @Override
    public boolean isFavorite() {
        return favorite;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setNumber(String number) {
        this.phoneNumber = number;
    }

    @Override
    public void setEmail(String email) {
        this.emailAddress = email;
    }

    @Override
    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    @Override
    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    @Override
    public void setSecondaryEmails(String[] secondaryEmails) {
        this.secondaryEmails = secondaryEmails;
    }

    @Override
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ContactImpl contact = (ContactImpl) obj;
        return id.equals(contact.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        // return "{" +
        //         "id='" + id + '\'' +
        //         ", name='" + name + '\'' +
        //         ", phoneNumber='" + phoneNumber + '\'' +
        //         ", emailAddress='" + emailAddress + '\'' +
        //         ", photoUri='" + photoUri + '\'' +
        //         ", organisation='" + organisation + '\'' +
        //         ", secondaryEmails=" + Arrays.toString(secondaryEmails) +
        //         ", favorite=" + favorite +
        //         '}';
        return name + ":" + id;
    }

}
