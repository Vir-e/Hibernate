/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author virgi
 */
public class P1 {
    
    
    private boolean salida = false;
    
    public void salir(boolean salida){
        this.salida = salida;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
    Scanner sc = new Scanner(System.in);
    boolean salir=false;
             
    SessionFactory creadorSesiones = SessionFactorySingletonHP.getSessionFactory();

    Gestion g = new Gestion();
    
    //System.out.println(g.consultaNombre(creadorSesiones,"Ron"));
    
    while(!salir){
    
        System.out.println("Elige que operación quieres realizar: \n CONSULTAR_TODOS | INSERTAR | MODIFICAR | BORRAR | CONSULTAR_CON_FILTRO | SALIR");
        String opcionElegida = sc.next();
        switch(opcionElegida){
            case "CONSULTAR_TODOS":
                g.consultarTodos(creadorSesiones);
                break;
            case "INSERTAR":
                g.insertar(creadorSesiones);
                break;
            case "MODIFICAR":
                g.actualizar(creadorSesiones);
                break;
            case "BORRAR":
                g.borrar(creadorSesiones);
            case "CONSULTAR_CON_FILTRO":
                System.out.println("Opciones (NOMBRE | APELLIDO | ESCUELA | CASA | GENERO | LIBRO). Escribe el nombre del campo por el que quieres filtrar los personajes:");
                String opc = sc.next();
                if(opc.equalsIgnoreCase("NOMBRE")){ 
                    System.out.println("Introduce el nombre por el que quieres filtrar:");
                    String n = sc.next();
                    g.consultaNombre(creadorSesiones, n);           
                }
                else if(opc.equalsIgnoreCase("APELLIDO")){
                    System.out.println("Introduce el apellido por el que quieres filtrar:");
                    String a = sc.next();
                    g.consultaApellido(creadorSesiones, a); 
                }
                else if(opc.equalsIgnoreCase("ESCUELA")){
                    System.out.println("Introduce la escuela por la que quieres filtrar:");
                    String esc = sc.next();
                    int n=0;
                    if(esc.equalsIgnoreCase("Hogwarts")){ n =1;}
                    g.consultaEscuela(creadorSesiones, n); 
                }
                else if(opc.equalsIgnoreCase("CASA")){
                    System.out.println("Introduce la casa por la que quieres filtrar:");
                    String casa = sc.next();
                    int n=0;
                    if(casa.equalsIgnoreCase("Gryffindor")){ n =1;}
                    else if(casa.equalsIgnoreCase("Slytherin")){ n =2;}
                    else if(casa.equalsIgnoreCase("Hufflepuff")){ n =3;}
                    else if(casa.equalsIgnoreCase("Rawenclaw")){ n =4;}
                    else{System.out.println("Introducido nombre casa no válido");}
                    g.consultaCasa(creadorSesiones, n); 
                }
                else if(opc.equalsIgnoreCase("GENERO")){
                    System.out.println("Introduce a filtrar : HOMBRE | MUJER");
                    String genero = sc.next();
                    g.consultaSexo(creadorSesiones, genero);
                }
                else if(opc.equalsIgnoreCase("LIBRO")){
                    System.out.println("Introduce el número del libro por el que quieres filtrar:");
                    int libro = sc.nextInt();
                    g.consultaLibro(creadorSesiones, libro);
          
                }
                else{ System.out.println("Opción no válida");}
                break;
            case "SALIR":
                salir=true;
                break;
            default:
                System.out.println("Opción no válida");
        }
        
        
    }
    
    
   
    /*
    // Abrimos una sesión (conexión a la BD)
    Session sesion = creadorSesiones.openSession();
    /*
    //Creamos una transaccion sobre la sesión (necesaria para que los datos se almacenen)
    Transaction trans = sesion.beginTransaction();  //A partir de este momento está abierta la transacción
    
    try{
    //Creamos un objeto con unos datos
    Personajes p = new Personajes(26,"Arthur","Weasley","Hombre", 47, 1,1,"Padre de Ron Weasley. Aficcionado por lo objetos muggles.",2);
    
    //Guardamos el objeto completo en la BD   
    sesion.save(p);
    
    //Hacemos permanentes los cambios
    trans.commit();

    
    
    }catch(Exception e){ trans.rollback();}
    
    
    
    
    // Obtenemos resultado de una consulta
    Query q = sesion.createQuery("from Personajes");
    // Guardamos ese resultado en una lista
    List<Personajes> arts = q.list();

    
    
       System.out.println("Tabla de Personajes de la BD 'Harry Potter'");
        
      Iterator<Personajes> iterador= arts.iterator();
        
        // Creamos objeto auxiliar
        Personajes pAux= null;
        
        while(iterador.hasNext()){
            // Vamos guardando en ese auxiliar cada iteracion del listado de la consulta y lo imprimimos
            pAux = iterador.next();
            
            System.out.println("Id. Personaje:"+pAux.getIdPersonaje()+
                    "***"+"Nombre:"+pAux.getNombre()+
                    "***"+"Apellido:"+pAux.getApellido()+
                    "***"+"Sexo:"+pAux.getSexo()+
                    "***"+"Edad:"+pAux.getEdad()+
                    "***"+"Escuela:"+pAux.getEscuelas().getNombre()+
                    "***"+"Casa:"+pAux.getCasas().getNombre()+
                    "***"+"Descripción:"+pAux.getDescripcion()+
                    "***"+"Libro de primera aparición:"+pAux.getLibros().getNombre()
                    );
        }
    
        // Siempre se cierra primero la sesion y luego el creador.
        // El creador es lo último que se cierra, deberá dejarse abierto si hay otras sesiones
        
        sesion.close();
        
        */
        creadorSesiones.close();
        System.out.println("Las conexiones a la BD han sido cerradas");
    
    }
    
}
