package TierraMedia;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ClienteTierraMedia {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		int opcion;
		System.out.println("�Bienvenido/a a Tierra Media!");
		System.out.println("Ingresar nombre completo:");
    	String nombre =sc.nextLine();
        System.out.println("Ingresar presupuesto:");
        int presupuesto =sc.nextInt();
        System.out.println("Ingresar tiempo disponible:");
        double tiempoDisponible =sc.nextDouble();
        System.out.println("Ingresar preferencia:");
        System.out.println("1."+TipoAtraccionEnum.AVENTURA);
        System.out.println("2."+TipoAtraccionEnum.DEGUSTACION);
        System.out.println("3."+TipoAtraccionEnum.PAISAJE);
        TipoAtraccionEnum tipoAtraccion=TipoAtraccionEnum.AVENTURA;
            switch(sc.nextInt()) {
            case 1:
            	 tipoAtraccion= TipoAtraccionEnum.AVENTURA;
            	break;
            case 2:
            	tipoAtraccion = TipoAtraccionEnum.DEGUSTACION;
            	break;
            case 3:
            	tipoAtraccion = TipoAtraccionEnum.PAISAJE;
            	break;
        	default:
        		sc.close();
            }
            
        Usuario usuario = new Usuario(nombre,presupuesto,tiempoDisponible,tipoAtraccion);
        System.out.println(usuario);
		
		
		
		
		
//		while (!salir) {
//			 
//            System.out.println("1. Cargar datos de Usuario");
//            System.out.println("2. Promociones");
//            System.out.println("3. Sugerencias");
//            System.out.println("4. Salir");
// 
//            try {
//                System.out.println("Escribe una de las opciones");
//                opcion = sc.nextInt();
//                
//                switch (opcion) {
//                    case 1:
//                    	System.out.println("Ingresar nombre completo");
//                    	String nombre =sc.nextLine();
//                    	String nombre2 =sc.nextLine();
//                        System.out.println("Ingresar presupuesto");
//                        int presupuesto =sc.nextInt();
//                        System.out.println("Ingresar tiempo disponible");
//                        double tiempoDisponible =sc.nextDouble();
//                        System.out.println("Ingresar preferencia");
//                        System.out.println("1."+TipoAtraccionEnum.AVENTURA);
//                        System.out.println("2."+TipoAtraccionEnum.DEGUSTACION);
//                        System.out.println("3."+TipoAtraccionEnum.PAISAJE);
//                        TipoAtraccionEnum tipoAtraccion=TipoAtraccionEnum.AVENTURA;
//	                        switch(sc.nextInt()) {
//	                        case 1:
//	                        	 tipoAtraccion= TipoAtraccionEnum.AVENTURA;
//	                        	break;
//	                        case 2:
//	                        	tipoAtraccion = TipoAtraccionEnum.DEGUSTACION;
//	                        	break;
//	                        case 3:
//	                        	tipoAtraccion = TipoAtraccionEnum.PAISAJE;
//	                        	break;
//	                        }
//                        Usuario usuario = new Usuario(nombre2,presupuesto,tiempoDisponible,tipoAtraccion);
//                        System.out.println(usuario);
//                        break;
//                    case 2:
//                    	System.out.println("Has seleccionado la opcion 2");
//                        break;
//                    case 3:
//                        System.out.println("Has seleccionado la opcion 3");
//                        break;
//                    case 4:
//                        salir = true;
//                        sc.close();
//                        break;
//                    default:
//                        System.out.println("Solo n�meros entre 1 y 4");
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Debes insertar un n�mero");
//                sc.next();
//            }
//		}
		
		
		
		
//		String nombre =sc.nextLine();
//		System.out.println("ingresar costo de visita");
//		double costoDeVisita = sc.nextDouble();
//		System.out.println("ingresar tiempo");
//		double tiempo = sc.nextDouble();
//		System.out.println("ingresar Tipo atraccion");
//		String tipoAtraccion = sc.nextLine();
//		System.out.println("ingresar otra atraccion");
//		String nombre =sc.nextLine();
//		System.out.println(nombre);
//		Atraccion moria = new Atraccion(nombre, 0, 0, 0, null);
		
		
		
		//Tanto las atracciones como los usuarios va a haber que cargarlos por Scanner
		//Atracciones
//		Atraccion moria = new Atraccion("Moria",10, 2.0,6, TipoAtraccionEnum.AVENTURA);
//		Atraccion minas = new Atraccion("Minas Tirith",5, 2.5,25, TipoAtraccionEnum.PAISAJE);
//		Atraccion comarca = new Atraccion("La Comarca",3, 6.5,150, TipoAtraccionEnum.DEGUSTACION);
//		Atraccion mordor = new Atraccion("Mordor",25, 3,4, TipoAtraccionEnum.AVENTURA);
//		Atraccion abismo = new Atraccion("Abismo de Helm",5, 2.0,15, TipoAtraccionEnum.PAISAJE);
		
//		System.out.println(moria.toString());
//		System.out.println("");
		
		//Usuarios
//		Usuario eowyn= new Usuario("Eowyn",10,8,TipoAtraccionEnum.AVENTURA);
//		Usuario gandalf= new Usuario("Gandalf",100,5,TipoAtraccionEnum.PAISAJE);
//		Usuario sam= new Usuario("Sam",36,8,TipoAtraccionEnum.DEGUSTACION);
		//Usuario galadriel= new Usuario("Galadriel",120,1,TipoAtraccionEnum.PAISAJE);
//		System.out.println(sam.toString());
	}

}