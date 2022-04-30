package ru.stqa.pft.addressbook;

public record ContactsGroup(String name, String lastName, String nickname, String address, String mobile, String email,
                            String birthdayDay, String birthdayMonth, String birthdayYear, String address2) {
}