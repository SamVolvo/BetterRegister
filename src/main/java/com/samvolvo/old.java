package com.samvolvo;

import com.samvolvo.enums.Gebruiker;
import com.samvolvo.enums.Item;
import com.samvolvo.tools.Colors;
import com.samvolvo.tools.ConsoleUtil;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class old {
    private ConsoleUtil consoleUtil;
    public old(){
        while (true){
            consoleUtil = new ConsoleUtil();
            Scanner scanner = new Scanner(System.in);
            consoleUtil.clearConsole();
            System.out.println("Lidl KassaSysteem");
            System.out.print("Gebruiker: ");
            int user = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Pincode: ");
            int code = scanner.nextInt();
            scanner.nextLine();

            Gebruiker gebruiker = Gebruiker.getGebruikerDoorId(user);

            if (code == gebruiker.getCode()){
                NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
                boolean loggedIn = true;
                while (loggedIn){
                    boolean keepRunning = true;
                    double totaal = 0.0;
                    consoleUtil.clearConsole();
                    List<Integer> rekening = new ArrayList<>();
                    System.out.println("Welkom, " + gebruiker.name().toLowerCase() + "!\n U kunt uw rekeningen beginnen.");
                    while (keepRunning){
                        boolean removed = false;
                        System.out.print("Voer plu code in: ");
                        String input = scanner.nextLine();

                        if (input.equalsIgnoreCase("betaal")){
                            System.out.println("Het totaal is " + formatter.format(totaal));
                            boolean betalingNietOK = true;
                            while (betalingNietOK) {
                                System.out.print("Ontvangen: ");
                                String ontvangenInput = scanner.nextLine();

                                ontvangenInput = ontvangenInput.replace(",", "."); // Vervang komma door punt
                                double ontvangen = Double.parseDouble(ontvangenInput);

                                if (ontvangen >= totaal) {
                                    double terug = ontvangen - totaal;
                                    System.out.println(Colors.BLAUW + "Betaling OK" + Colors.RESET);
                                    betalingNietOK = false;
                                    rekening.clear();
                                    totaal = 0.00;
                                    System.out.println(Colors.GEEL + "Terug: " + formatter.format(terug) + Colors.RESET);
                                } else {
                                    double nogTeBetalen = totaal - ontvangen;
                                    totaal = nogTeBetalen;
                                    System.out.println(Colors.GEEL + "Nog te betalen: " + formatter.format(nogTeBetalen) + Colors.RESET);
                                }
                            }

                            try {
                                Thread.sleep(5000); //
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        }else if (input.equalsIgnoreCase("loguit")){
                            if (totaal != 0.0){
                                System.out.println("Maak deze rekening eerst af!");
                                break;
                            }
                            keepRunning = false;
                            loggedIn = false;
                            consoleUtil.clearConsole();
                            System.out.println("Aan het uitloggen!");
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            consoleUtil.clearConsole();
                            System.out.println("Laden ...");
                            break;
                        }else if (input.equalsIgnoreCase("annuleer")){
                            if (totaal == 0.0){
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            System.out.println(Colors.ROOD + "Geannulleerd!" + Colors.RESET);
                            rekening.clear();
                            totaal = 0.0;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        } else if (input.equalsIgnoreCase("verwijder")) {
                            boolean verwijderModus = false;
                            while (verwijderModus) {
                                System.out.print("Welk item wilt u weghalen: ");
                                int plu2 = scanner.nextInt();
                                scanner.nextLine();
                                if (!rekening.contains(plu2)){
                                    System.out.println(Colors.GEEL + "Dit item staat niet op de rekening." + Colors.RESET);
                                }else{
                                    Item item = Item.getItemByPlu(plu2);
                                    if (item != null){
                                        totaal -= item.getPrice();
                                        System.out.println(Colors.ROOD + "- " + item.name().toLowerCase() + " - $" + item.getPrice() + Colors.RESET);
                                        System.out.print("Voer plu code in: ");
                                        input = scanner.nextLine();
                                        if (input.equalsIgnoreCase("verwijder")){
                                            removed = true;
                                            verwijderModus = true;
                                        }
                                    }else{
                                        System.out.println(Colors.ROOD + "PLU niet gevonden." + Colors.RESET);
                                        removed = true;
                                    }
                                }
                            }
                        }
                        if (!removed){
                            try{
                                int plu = Integer.parseInt(input);
                                Item item = Item.getItemByPlu(plu);
                                if (item != null){
                                    totaal += item.getPrice();
                                    rekening.add(plu);
                                    System.out.println(Colors.GROEN + item.name().toLowerCase() + " - $" + item.getPrice() + Colors.RESET);
                                }else{
                                    System.out.println(Colors.ROOD + "PLU niet gevonden." + Colors.RESET);
                                }
                            }catch (NumberFormatException e){
                                System.out.println(Colors.ROOD + "Onbekende PLU code." + Colors.RESET);
                            }
                        }

                    }
                }
            }else{
                System.out.println("Ongeldige inlog.");
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
