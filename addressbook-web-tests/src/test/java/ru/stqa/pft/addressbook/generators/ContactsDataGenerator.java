package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactsDataGenerator {
  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<ContactData> contacts = generateContacts(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for(ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
              contact.getFirstName(),
              contact.getLastName(),
              contact.getNickname(),
              contact.getAddress(),
              contact.getHomePhone(),
              contact.getMobile(),
              contact.getWorkPhone(),
              contact.getEmail(),
              contact.getBirthdayDay(),
              contact.getBirthdayMonth(),
              contact.getBirthdayYear(),
              contact.getAddress2()
              ));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i=0; i<count;i++){
      int d = 0;
      if (i<30){
        d=i;
      }
      contacts.add(new ContactData()
              .withFirstName(String.format("FirstName_%s", i+1))
              .withLastName(String.format("LastName_%s", i+1))
              .withNickname(String.format("Nickname_%s", i+1))
              .withAddress(String.format("Address_%s", i+1))
              .withHomePhone("+73333333333")
              .withMobile("+79999999999")
              .withWorkPhone("+78888888888")
              .withEmail(String.format("Test%s@mail.ru", i+1))
              .withBirthdayDay(String.format("%s", d+1))
              .withBirthdayMonth("January")
              .withBirthdayYear("1990")
              .withAddress2(String.format("TestAddress2_%s", i+1))
      );
    }
    return contacts;
  }
}
