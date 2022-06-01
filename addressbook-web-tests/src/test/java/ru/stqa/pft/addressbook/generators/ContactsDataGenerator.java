package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactsDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

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
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")){
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")){
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }


  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
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

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactData.class);
    String xml = xStream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for (int i=0; i<count;i++){
      int day;
      if (i<31){
        day=i;
      } else {
        day = i - (31 * (i / 31));
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
              .withBirthdayDay(String.format("%s", day+1))
              .withBirthdayMonth("January")
              .withBirthdayYear("1990")
              .withAddress2(String.format("TestAddress2_%s", i+1))
      );
    }
    return contacts;
  }
}
