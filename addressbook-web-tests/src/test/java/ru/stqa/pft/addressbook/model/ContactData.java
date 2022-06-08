package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jakarta.persistence.*;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField @Id private int id = Integer.MAX_VALUE;
  @Expose private String firstName = "";
  @Expose private String lastName = "";
  @Expose private String nickname = "";
  @XStreamOmitField @Transient private String photo = "";
  @Expose private String address = "";
  @Expose @Column(name = "home") private String homePhone = "";
  @Expose private String mobile = "";
  @Expose @Column(name = "work") private String workPhone = "";
  @Expose private String email = "";
  @Expose private String email2 = "";
  @Expose private String email3 = "";
  @Expose @Column(name = "bday", columnDefinition="tinyint") private String birthdayDay = "";
  @Expose @Column(name = "bmonth") private String birthdayMonth = "";
  @Expose @Column(name = "byear") private String birthdayYear = "";
  @Expose private String address2 = "";
  @Expose @Transient  private String allPhones = "";
  @Expose @Transient  private String allEmails = "";

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(nickname, that.nickname) && Objects.equals(photo, that.photo) && Objects.equals(address, that.address) && Objects.equals(homePhone, that.homePhone) && Objects.equals(mobile, that.mobile) && Objects.equals(workPhone, that.workPhone) && Objects.equals(email, that.email) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3) && Objects.equals(birthdayDay, that.birthdayDay) && Objects.equals(birthdayMonth, that.birthdayMonth) && Objects.equals(birthdayYear, that.birthdayYear) && Objects.equals(address2, that.address2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, nickname, photo, address, homePhone, mobile, workPhone, email, email2, email3, birthdayDay, birthdayMonth, birthdayYear, address2);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            /*"id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", nickname='" + nickname + '\'' +*/
            ", photo='" + photo + '\'' +
            /*", address='" + address + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobile='" + mobile + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", birthdayDay='" + birthdayDay + '\'' +
            ", birthdayMonth='" + birthdayMonth + '\'' +
            ", birthdayYear='" + birthdayYear + '\'' +
            ", address2='" + address2 + '\'' +*/
            '}';
  }

  /*Getters*/
  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickname() {
    return nickname;
  }

  public File getPhoto() {
    return new File(photo);
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getBirthdayDay() {
    return birthdayDay;
  }

  public String getBirthdayMonth() {
    return birthdayMonth;
  }

  public String getBirthdayYear() {
    return birthdayYear;
  }

  public String getAddress2() {
    return address2;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  /*Setters*/
  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withBirthdayDay(String birthdayDay) {
    this.birthdayDay = birthdayDay;
    return this;
  }

  public ContactData withBirthdayMonth(String birthdayMonth) {
    this.birthdayMonth = birthdayMonth;
    return this;
  }

  public ContactData withBirthdayYear(String birthdayYear) {
    this.birthdayYear = birthdayYear;
    return this;
  }

  public ContactData withAddress2(String address2) {
    this.address2 = address2;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }


}