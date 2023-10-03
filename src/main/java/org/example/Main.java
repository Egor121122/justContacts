package org.example;

import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    Contact[] contacts = new Contact[0];
    ContactManager contactManager = new ContactManager(contacts);

    public static void main(String[] args) {
        System.out.println();
        System.out.println("Options:");
        System.out.println("add <name> <surName> <number>");
        System.out.println("delete <index>");
        System.out.println("edit <index> <name> <surName> <number>");
        System.out.println("list");
        System.out.println("quit");
        System.out.println("indexes 0 - ...");
        new Main().update();
    }

    void update(){
        String input = scanner.nextLine();
        String[] arg = input.split(" ");
        switch (arg[0]){
            case "add":
                contactManager.add(arg[1], arg[2], arg[3]);
                break;
            case "delete":
                contactManager.delete(Integer.parseInt(arg[1]));
                break;
            case "edit":
                contactManager.edit(Integer.parseInt(arg[1]), arg[2], arg[3], arg[4]);
                break;
            case "list":
                contactManager.list();
                break;
            case "quit":
                contactManager.quit();
                break;
            default:
                System.out.println("invalid input");
                break;
        }

        if(!contactManager.isQuite())
            update();
    }
}

class Contact{
    private String name;
    private String surName;
    private String number;

    public void setData(String name, String surName, String number){
        this.name = name;
        this.surName = surName;
        this.number = number;
    }
    public String getName(){return name;}
    public String getSurName() {return surName;}
    public String getNumber() {return number;}
}

class ContactManager{
    public ContactManager(Contact[] contacts) {this.contacts = contacts;}
    private Contact[] contacts;
    private boolean isQuite = false;

    public void add(String name, String  surName, String number){
        Contact[] tmpContacts = new Contact[contacts.length + 1];
        for (int i = 0; i < tmpContacts.length; i++)
            tmpContacts[i] = new Contact();
        System.arraycopy(contacts, 0, tmpContacts, 0, contacts.length);
        tmpContacts[tmpContacts.length-1].setData(name, surName, number);
        contacts = tmpContacts;
    }
    public void delete(int index){
        if(index < contacts.length) {
            Contact[] tmpContacts = new Contact[contacts.length - 1];
            for (int i = 0; i < tmpContacts.length; i++)
                tmpContacts[i] = new Contact();
            System.arraycopy(contacts, 0, tmpContacts, 0, index);
            System.arraycopy(contacts, index + 1, tmpContacts, index, tmpContacts.length - index);
            contacts = tmpContacts;
        } else {System.out.println("invalid index");}
    }
    public void edit(int index, String name, String  surName, String number){
        if(index < contacts.length)
            contacts[index].setData(name, surName, number);
        else
            System.out.println("invalid index");
    }
    public void list(){
        for (Contact contact : contacts) {
            System.out.print(contact.getName() + " " + contact.getSurName() + " " + contact.getNumber());
            System.out.println();
        }
    }
    public void quit(){isQuite = true;}
    public boolean isQuite() {return isQuite;}
}