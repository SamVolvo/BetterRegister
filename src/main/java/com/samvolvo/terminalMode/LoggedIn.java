package com.samvolvo.terminalMode;

import com.samvolvo.Register;
import com.samvolvo.enums.Gebruiker;
import com.samvolvo.kassaSysteem.Rekening;
import com.samvolvo.tools.ConsoleUtil;

import java.util.Scanner;

public class LoggedIn {
    private Register main;
    private Gebruiker gebruiker;

    public LoggedIn(Register main, Gebruiker gebruiker){
        this.main = main;
        this.gebruiker = gebruiker;
        loggedInScreen();
    }


    public void loggedInScreen(){
        ConsoleUtil consoleUtil = main.getConsoleUtil();
        Scanner scanner = main.getScanner();
        consoleUtil.clearConsole();
        boolean loggedIn = true;
        Rekening rekening = new Rekening(main);
        System.out.println("Welkom " + gebruiker.name().toLowerCase() + ".");
        while (loggedIn){
            System.out.print("Voer PLU code in: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("verwijder")){
                rekening.removeProduct();
                break;
            }

            rekening.addProduct(input);
        }
    }
}
