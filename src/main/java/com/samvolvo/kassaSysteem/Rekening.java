package com.samvolvo.kassaSysteem;

import com.samvolvo.Register;
import com.samvolvo.enums.Item;
import com.samvolvo.tools.Colors;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rekening {
    private Register main;
    private List<Integer> rekening = new ArrayList<>();
    private double totaal = 0.0;

    public Rekening(Register main){
        this.main = main;
    }

    public void addProduct(String input){
        try{
            int plu = Integer.parseInt(input);
            Item item = Item.getItemByPlu(plu);
            if (item != null){
                totaal += item.getPrice();
                rekening.add(plu);
                System.out.println(Colors.GROEN + item.name().toLowerCase() + " - " + item.getPrice() + Colors.RESET);
            }else{
                System.out.println(Colors.ROOD + "Onbekende PLU nummer" + Colors.RESET);
            }
        }catch (NumberFormatException e){
            System.out.println(Colors.ROOD + "Onbekende PLU nummer" + Colors.RESET);
        }
    }

    public void removeProduct(){
        System.out.print("Welk product wilt u verwijderen: ");
        int plu = main.getScanner().nextInt();
        Item item = Item.getItemByPlu(plu);
        main.getScanner().nextLine();
        if (item == null){
            System.out.println(Colors.ROOD + "Onbekende PLU nummer" + Colors.RESET);
            return;
        }else{
            if (!rekening.contains(plu)) {
                System.out.println(Colors.GEEL + "Dit item staat niet op de rekening." + Colors.RESET);
                return;
            }

            totaal -= item.getPrice();
            rekening.remove(plu);
            System.out.println(Colors.ROOD + "- " + item.name().toLowerCase() + " - $" + item.getPrice() + Colors.RESET);
            return;
        }
    }
}
