package ru.stqa.pft.addressbook.model;

public class GroupData {
  private final String gname;
  private final String header;
  private final String footer;
  private final String name;
  private final String lastName;
  private final String nickname;
  private final String address;
  private final String mobile;
  private final String email;
  private final String birthdayDay;
  private final String birthdayMonth;
  private final String birthdayYear;
  private final String address2;


  public GroupData(String gname, String header, String footer, String name, String lastName, String nickname, String address, String mobile, String email,
                   String birthdayDay, String birthdayMonth, String birthdayYear, String address2) {
    this.gname = gname;
    this.header = header;
    this.footer = footer;
    this.name = name;
    this.lastName = lastName;
    this.nickname = nickname;
    this.address = address;
    this.mobile = mobile;
    this.email = email;
    this.birthdayDay = birthdayDay;
    this.birthdayMonth = birthdayMonth;
    this.birthdayYear = birthdayYear;
    this.address2 = address2;
  }

  public String getGname() {
    return gname;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  public String getName() {
    return name;
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
}
