package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String nickname;
  private final String address;
  private final String mobile;
  private final String email;
  private final String birthdayDay;
  private final String birthdayMonth;
  private final String birthdayYear;
  private final String address2;
  private final String group;


  public ContactData(String firstName, String lastName, String nickname, String address, String mobile, String email,
                     String birthdayDay, String birthdayMonth, String birthdayYear, String address2, String group) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.address = address;
    this.mobile = mobile;
    this.email = email;
    this.birthdayDay = birthdayDay;
    this.birthdayMonth = birthdayMonth;
    this.birthdayYear = birthdayYear;
    this.address2 = address2;
    this.group = group;
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

  public String getAddress() {
    return address;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
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

  public String getGroup() {
    return group;
  }
}