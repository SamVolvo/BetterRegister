package com.samvolvo;

import com.samvolvo.terminalMode.Login;
import com.samvolvo.tools.ConsoleUtil;
import com.samvolvo.tools.Logging;

import java.util.Scanner;

public class Register {
    private ConsoleUtil consoleUtil;
    private Scanner scanner;

    public Register(){
        consoleUtil = new ConsoleUtil();
        Boot.Bootscreen(consoleUtil);
        while (true){
            scanner = new Scanner(System.in);
            consoleUtil.clearConsole();
            System.out.println("KassaOs Is klaar!");
            consoleUtil.wait(50);
            System.out.println("Kies uw boot option.");
            consoleUtil.wait(50);
            System.out.println("Terminal Mode: 1");
            consoleUtil.wait(50);
            System.out.println("Gui Mode: 2");
            consoleUtil.wait(50);
            System.out.print("Option: ");
            int option = scanner.nextInt();

            if (option == 1){
                consoleUtil.clearConsole();
                System.out.println("Loading terminal Mode.");
                consoleUtil.wait(10000);
                new Login(this);
            } else if (option == 2) {
                System.out.println("Not supported yet!");
                consoleUtil.wait(2000);
                consoleUtil.clearConsole();
            }else{
                System.out.println("Invalid option Try again!");
                consoleUtil.wait(2000);
                consoleUtil.clearConsole();
            }
            scanner.close();
        }
    }

    public ConsoleUtil getConsoleUtil() {
        return consoleUtil;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public static void main(String[] args) {
        new Register();
    }
}