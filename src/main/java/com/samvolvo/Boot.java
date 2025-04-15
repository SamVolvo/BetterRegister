package com.samvolvo;

import com.samvolvo.tools.ConsoleUtil;
import com.samvolvo.tools.Logging;

public class Boot {
    public static void Bootscreen(ConsoleUtil consoleUtil){
        consoleUtil.clearConsole();
        Logging.ShowLogo();
        consoleUtil.wait(3000);
        consoleUtil.clearConsole();
        System.out.println("Loading");
        consoleUtil.wait(500);
        consoleUtil.clearConsole();
        System.out.println("Loading .");
        consoleUtil.wait(500);
        consoleUtil.clearConsole();
        System.out.println("Loading ..");
        consoleUtil.wait(500);
        consoleUtil.clearConsole();
        System.out.println("Loading ...");
        consoleUtil.wait(500);
        consoleUtil.clearConsole();
        System.out.println("Loading .");
        consoleUtil.wait(500);
        consoleUtil.clearConsole();
        System.out.println("Loading ..");
        consoleUtil.wait(500);
        consoleUtil.clearConsole();
        System.out.println("Loading ...");
    }
}
