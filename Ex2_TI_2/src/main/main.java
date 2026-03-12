package main;

import X.X;
import XDAO.XDAO;

import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        XDAO dao = new XDAO();
        int option;

        do {
            System.out.println("\n===== CRUD MENU =====");
            System.out.println("1 - List");
            System.out.println("2 - Insert");
            System.out.println("3 - Delete");
            System.out.println("4 - Update");
            System.out.println("5 - Exit");
            System.out.print("Choose an option: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    List<X> list = dao.list();
                    if (list.isEmpty()) {
                        System.out.println("No records found.");
                    } else {
                        for (X x : list) {
                            System.out.println(x);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Description: ");
                    String description = sc.nextLine();

                    System.out.print("Value: ");
                    double value = sc.nextDouble();
                    sc.nextLine();

                    X newX = new X(name, description, value);
                    dao.insert(newX);
                    break;

                case 3:
                    System.out.print("Enter the ID to delete: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();
                    dao.delete(deleteId);
                    break;

                case 4:
                    System.out.print("Enter the ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New name: ");
                    String newName = sc.nextLine();

                    System.out.print("New description: ");
                    String newDescription = sc.nextLine();

                    System.out.print("New value: ");
                    double newValue = sc.nextDouble();
                    sc.nextLine();

                    X updatedX = new X(updateId, newName, newDescription, newValue);
                    dao.update(updatedX);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 5);

        sc.close();
    }
}