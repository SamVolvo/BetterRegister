package com.samvolvo.terminalMode;

import com.samvolvo.Register;
import com.samvolvo.enums.Gebruiker;
import com.samvolvo.tools.Colors;
import com.samvolvo.tools.ConsoleUtil;

import java.util.Scanner;

public class Login {
    private Register main;

    public Login(Register main){
        this.main = main;
        loginScreen();
    }

    public void loginScreen(){
        ConsoleUtil consoleUtil = main.getConsoleUtil();
        while (true){
            consoleUtil.clearConsole();
            Scanner scanner = main.getScanner();
            main.getConsoleUtil().clearConsole();
            System.out.println("Welcome to KassaOs.");
            System.out.print("User: ");
            int userid = scanner.nextInt();
            System.out.print("PinCode: ");
            int pin = scanner.nextInt();
            scanner.nextLine();

            Gebruiker gebruiker = Gebruiker.getGebruikerDoorId(userid);

            if (gebruiker != null && pin == gebruiker.getCode()){
                consoleUtil.clearConsole();
                new LoggedIn(main, gebruiker);
            }else{
                consoleUtil.clearConsole();
                System.out.println(Colors.ROOD + "Onjuiste gegevens! Probeer Opnieuw!" + Colors.RESET);
                consoleUtil.wait(2000);
            }
        }
    }
}
