package stud.oiv.migration.view;

import stud.oiv.migration.controller.Command;
import stud.oiv.migration.controller.implementation.MigrateToBD;
import stud.oiv.migration.controller.implementation.ValidateXml;

import java.util.Scanner;

public class ConsoleView {
    public void Show()
    {
        boolean exitFlag = false;
        while (!exitFlag){
            System.out.println("Choose what you want:");
            System.out.println("1. Migrate data from xml to bd;");
            System.out.println("2. Only validate your xml with xsd;");
            System.out.println("3. Exit.");
            Scanner scanner = new Scanner(System.in);
            String res = scanner.nextLine();
            Command command = null;
            switch (res)
            {
                case "1": {
                    command = new MigrateToBD();
                    command.execute();
                    break;
                }
                case "2": {
                    command = new ValidateXml();
                    System.out.println("Enter xsd path:");
                    String xsdPath = scanner.nextLine();
                    System.out.println("Enter xml path:");
                    String xmlPath = scanner.nextLine();
                    command.execute(xsdPath,xmlPath);
                    break;
                }
                case "3": {
                    exitFlag = true;
                    break;
                }
                default: {
                    System.out.println("Incorrect command");
                    break;
                }

            }
        }
    }
}
