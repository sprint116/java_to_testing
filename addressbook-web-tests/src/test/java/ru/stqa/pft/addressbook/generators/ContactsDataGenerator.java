package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactsDataGenerator {

  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  public static void main(String[] args) throws IOException {
    ContactsDataGenerator generator=new ContactsDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    }catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();

  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    save(contacts, new File(file));
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
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

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i=0; i<count;i++){
      int d;
      if (i<31){
        d=i;
      } else {
        d = i - (31 * (i / 31));
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
