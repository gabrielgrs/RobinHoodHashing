package main;

import model.Route;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Seeder {

    public static void main(String[] args) {
				
        try {
                FileReader routes = new FileReader("C:\\Users\\Gabriel\\Documents\\NetBeansProjects\\RobinHoodHashing\\src\\data\\routes.txt");
                Scanner input = new Scanner(routes).useDelimiter("[,\n]");
                input.nextLine(); //Pula o cabecalho do arquivo (da tabela)

                while(input.hasNext()){
                        Route route = new Route(input.next());
                        input.next(); // pula agency_id
                        route.setShortName(input.next());
                        route.setLongName(input.next());
                        input.next(); // pula desc
                        route.setType(input.nextInt());
                        input.next(); // pula url
                        route.setColor(input.next());
                        route.setTextColor(input.next());
                        // insert(route.getLongName);
                        System.out.println(route.getLongName());
                }


        } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
		
		           
    }

}
