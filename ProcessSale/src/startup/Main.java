package startup;

import controller.Controller;
import integration.ExternalAccSys;
import view.View;

public class Main {

    public static void main(String[] args) {
        ExternalAccSys externalAccSys = new ExternalAccSys();
        Controller contr = new Controller(externalAccSys);
        View view = new View(contr);


        view.sampleExecution();
    }
}