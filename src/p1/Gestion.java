/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author virgi
 */
public class Gestion {
    
    private final Escuelas HOGWARTS = new Escuelas(1,"Hogwarts");
    private final Casas G = new Casas(1,"Gryffindor");
    private final Casas S = new Casas(2,"Slytherin");
    private final Casas R = new Casas(4,"Ravenclaw");
    private final Casas H = new Casas(3,"Hufflepuff");
    
    
    private final Libros l1 = new Libros(1);
    private final Libros l2 = new Libros(2);
    private final Libros l3 = new Libros(3);
    private final Libros l4 = new Libros(4);
    private final Libros l5 = new Libros(5);
    private final Libros l6 = new Libros(6);
    private final Libros l7 = new Libros(7);
     
   
    
    // INSERTA UN PERSONAJE NUEVO
    public String insertar(SessionFactory sf){
        String mensaje="";
        //SessionFactory creadorSesiones = SessionFactorySingletonHP.getSessionFactory();
        Session sesion = sf.openSession();
       // BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Vas a agregar un nuevo personaje a la Base de Datos. A continuación añade los campos necesarios.");
        int id_nuevoPer=0;
        boolean id_num=false;
        
        do{       
            
            try{
                System.out.println("Introduce su id:");
                id_nuevoPer = Integer.parseInt(sc.nextLine());
                id_num=true;
                System.out.println("El id introducido es:" + id_nuevoPer);
            
            }catch(Exception e){
                System.out.println("Por favor introduce un id. numerico para poder insertar un personaje");
            }
        }while(id_num==false);
        
            
            
        
        
        
 
        System.out.println("Introduce su nombre:");
        String nom_nuevoPer = sc.next();
        System.out.println("El nombre introducido es:" + nom_nuevoPer);
        
        System.out.println("Introduce su apellido:");
        String ape_nuevoPer = sc.next();
        System.out.println("El apellido introducido es:" + ape_nuevoPer);
        
        System.out.println("Introduce su sexo:");
        String sex_nuevoPer = sc.next();
        System.out.println("El sexo introducido es:" + sex_nuevoPer);
        
        System.out.println("Introduce su edad:");
        int edad_nuevoPer = sc.nextInt();
        System.out.println("La edad introducida es:" + edad_nuevoPer);
        
        System.out.println("Introduce su escuela de magia:");
        String esc_nuevoPer = sc.next();
        System.out.println("La escuela introducida es:" + esc_nuevoPer);
        
        System.out.println("Introduce su casa:");
        String casa_nuevoPer = sc.next();
        System.out.println("La casa introducida es:" + casa_nuevoPer);
        
        System.out.println("Introduce el libro en el que el personaje aparece por primera vez:");
        int libro_nuevoPer = sc.nextInt();
        System.out.println("El libro introducido es:" + libro_nuevoPer);
        
        System.out.println("Introduce una breve descripción sobre el personaje:");
        String des_nuevoPer = sc.next();
        System.out.println("La descripción dada al personaje es:" + des_nuevoPer);
        
        
        // Obj auxiliares
        Escuelas esc = null;
        Casas casa = null;
        Libros libro = null;
        
        // asociar escuela, casa y libro
        switch(esc_nuevoPer.trim()){
            case "Hogwarts":
                esc= HOGWARTS;
                break;
            default:
                System.out.println("Ninguna escuela introducida es correcta.");
        }
        switch(casa_nuevoPer.trim()){
            case "Gryffindor":
                casa = G;
                break;
            case "Slytherin":
                casa = S;
                break;
            case "Ravenclaw":
                casa = R;
                break;
            case "Hufflepuff":
                casa = H;
                break;
            default:
                System.out.println("Ninguna casa introducida es correcta");
                
        }
        switch(libro_nuevoPer){
            
            case 1:
                libro = l1;
                break;
            case 2:
                libro = l2;
                break;
            case 3:
                libro = l3;
                break;
            case 4:
                libro = l4;
                break;
            case 5:
                libro = l5;
                break;
            case 6:
                libro = l6;
                break;
            case 7:
                libro = l7;
                break;
            default:
                System.out.println("El libro introducido no es correcto. Introduce del 1 al 7.");
                
                
        }
        //Creamos una transaccion sobre la sesión (necesaria para que los datos se almacenen)
        Transaction trans = sesion.beginTransaction();  //A partir de este momento está abierta la transacción
        try{
            
            
            //SE HAN CREADO LAS INSTANCIAS CON LAS ENTIDADES DE LAS CASAS, ESCUELAS Y LIBROS(SON VALORES FIJOS)PARA DARSELOS AL PERSONAJE
            
            
            //Creamos un objeto con unos datos
            Personajes p = new Personajes(id_nuevoPer,casa, esc, libro, nom_nuevoPer,ape_nuevoPer, sex_nuevoPer, edad_nuevoPer,des_nuevoPer);
            
            //Guardamos el objeto completo en la BD
            sesion.save(p);
            
            //Hacemos permanentes los cambios
            trans.commit();
            
            mensaje = "Personaje insertado con éxito";
            
        }catch(Exception e){
            trans.rollback();
            mensaje="No se ha podido insertar el personaje.";
            
        }
        finally{
            sesion.close();
            System.out.println("La sesión ha sido cerrada");
        }
       return mensaje;
    }
    
    // MODIFICA UN PERSONAJE POR SU ID
    public String actualizar(SessionFactory sf){
          String mensaje="";
            //SessionFactory creadorSesiones = SessionFactorySingletonHP.getSessionFactory();
            Session sesion = sf.openSession();
            
            //Creamos una transaccion sobre la sesión (necesaria para que los datos se almacenen)
            Transaction trans = sesion.beginTransaction();  //A partir de este momento está abierta la transacción
            System.out.println("Introduce el id. del personaje que deseas modificar");
            //BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter("\n");
            int id_pers_selec = sc.nextInt();
            Query q = sesion.createQuery("from Personajes where id_personaje = "+id_pers_selec);
            List<Personajes> pers = q.list();
            Personajes p = pers.get(0);
            System.out.println("¿Que campo deseas modificar su valor?");
            
            System.out.println("Nombre | Apellido | Escuela | Casa | Edad | Sexo | Descripcion | Libro");
            String campo_a_modificar = sc.next();
            switch (campo_a_modificar.trim()){
                
                case "Nombre":
                    System.out.println("Introduce el nuevo valor para el nombre:");
                    String nombreNuevo = sc.next();
                    p.setNombre(nombreNuevo);
                    sesion.update(p);
                    break;
                case "Apellido":
                    System.out.println("Introduce el nuevo valor para el apellido:");
                    String apellidoNuevo = sc.next();
                    p.setApellido(apellidoNuevo);
                    sesion.update(p);
                    break;
                case "Escuela":
                    System.out.println("Introduce el nuevo valor para la escuela:");
                    String escuelaNueva = sc.next();
                    if(escuelaNueva.equalsIgnoreCase(HOGWARTS.getNombre())){
                        p.setEscuelas(HOGWARTS);
                    }                   
                    sesion.update(p);
                    break;
                case "Casa":
                    System.out.println("Introduce el nuevo valor para la casa:");
                    String casaNueva = sc.next();
                    if(casaNueva.equalsIgnoreCase(G.getNombre())){
                        p.setCasas(G);
                    }
                    else if(casaNueva.equalsIgnoreCase(S.getNombre())){
                        p.setCasas(S);
                    }
                    else if(casaNueva.equalsIgnoreCase(R.getNombre())){
                        p.setCasas(R);
                    }
                    else if(casaNueva.equalsIgnoreCase(H.getNombre())){
                        p.setCasas(H);
                    }
                    else{
                        System.out.println("Casa introducida no válida");
                    }
                    sesion.update(p);
                    break;
                case "Edad":
                    System.out.println("Introduce el nuevo valor para la edad:");
                    int edadNueva = sc.nextInt();
                    p.setEdad(edadNueva);
                    sesion.update(p);
                    break;
                case "Sexo":
                    System.out.println("Introduce el nuevo valor para el sexo:");
                    String sexoNuevo = sc.next();
                    p.setSexo(sexoNuevo);
                    sesion.update(p);
                    break;
                case "Descripcion":
                    System.out.println("Introduce el nuevo valor para la descripción:");
                    String descNueva = sc.next();
                    p.setDescripcion(descNueva);
                    sesion.update(p);
                    break;
                case "Libro":
                    System.out.println("Introduce el nuevo valor para el libro:");
                    String libroNuevo = sc.next();
                    if(libroNuevo.equalsIgnoreCase("1")){
                        p.setLibros(l1);
                    }
                    else if(libroNuevo.equalsIgnoreCase("2")){
                        p.setLibros(l2);
                    }
                    else if(libroNuevo.equalsIgnoreCase("3")){
                        p.setLibros(l3);
                    }
                    else if(libroNuevo.equalsIgnoreCase("4")){
                        p.setLibros(l4);
                    }
                    else if(libroNuevo.equalsIgnoreCase("5")){
                        p.setLibros(l5);
                    }
                    else if(libroNuevo.equalsIgnoreCase("6")){
                        p.setLibros(l6);
                    }
                    else if(libroNuevo.equalsIgnoreCase("7")){
                        p.setLibros(l7);
                    }
                    else{
                        System.out.println("Libro introducido no válido. Introduzca número del 1 al 7.");
                    }                  
                    sesion.update(p);
                    break;
                default:
                    System.out.println("El campo introducido no es válido");
            }
            trans.commit();
            mensaje ="Personaje actualizado con éxito.";
            sesion.close();
            System.out.println("La sesión ha sido cerrada");
            return mensaje; 
    }
    
    // BORRA UN PERSONAJE POR SU ID
    public String borrar(SessionFactory sf){
    
        String mensaje="";
        Session sesion = sf.openSession();
        Transaction trans = sesion.beginTransaction();
        
        try{
            // CODIGO PARA BORRAR UN PERSONAJE
            System.out.println("Introduce el id. del personaje que deseas eliminar");
            //BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            Scanner sc = new Scanner(System.in);
            int id_pers_selec = sc.nextInt();

            Query q = sesion.createQuery("from Personajes where id_personaje = "+id_pers_selec);
            List<Personajes> pers = q.list();
            Personajes p = pers.get(0);
            sesion.delete(p);
            
            
            
        }catch(Exception e){
            mensaje="No se pudo eliminar el personaje.";
        }
        finally{
            trans.commit();
            mensaje="Personaje eliminado con éxito.";
            sesion.close();  
            System.out.println("La sesión ha sido cerrada");
            }
        return mensaje;
    }
    
    // CONSULTA TODOS LOS REGISTROS DE PERSONAJES
    public void consultarTodos(SessionFactory sf){
    
        // Abrimos una sesión (conexión a la BD)
        Session sesion = sf.openSession();
    
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
    
        sesion.close();
        System.out.println("La sesion ha sido cerrada");
    
    }
    
    // CONSULTAS CON FILTRO
    
    public String consultaNombre(SessionFactory sf,String nombre){

        // Abrimos una sesión (conexión a la BD)
        Session sesion = sf.openSession();
    
        // Obtenemos resultado de una consulta
        Query q = sesion.createQuery("from Personajes as Personajes where nombre = " +"'" + nombre +"'");
        
        List<Personajes> pers = q.list();
        if(pers.isEmpty()){
            System.out.println("No existen coincidencias en la Base de datos");
        }else{
            Iterator<Personajes> iterador= pers.iterator();

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
        }
    
        sesion.close();
        System.out.println("La sesion ha sido cerrada");
        


       return "";
    }
    
    
    public String consultaApellido(SessionFactory sf,String apellido){

        // Abrimos una sesión (conexión a la BD)
        Session sesion = sf.openSession();
    
        // Obtenemos resultado de una consulta
        Query q = sesion.createQuery("from Personajes as Personajes where apellido = " +"'" + apellido +"'");
        
        List<Personajes> pers = q.list();
        if(pers.isEmpty()){
            System.out.println("No existen coincidencias en la Base de datos");
        }else{
             Iterator<Personajes> iterador= pers.iterator();
        
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
  
        }
   sesion.close();
        System.out.println("La sesion ha sido cerrada");

       return "";
    }
    
    
    public String consultaEscuela(SessionFactory sf,int n){

        // Abrimos una sesión (conexión a la BD)
        Session sesion = sf.openSession();
    
        // Obtenemos resultado de una consulta
        Query q = sesion.createQuery("from Personajes as Personajes where escuela = " +"'" + n +"'");
        
        List<Personajes> pers = q.list();
        if(pers.isEmpty()){
            System.out.println("No existen coincidencias en la Base de datos");
        }else{

            Iterator<Personajes> iterador= pers.iterator();

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
        }
        sesion.close();
        System.out.println("La sesion ha sido cerrada");
        


       return "";
    }
    
    public String consultaCasa(SessionFactory sf,int n){

        // Abrimos una sesión (conexión a la BD)
        Session sesion = sf.openSession();
    
        // Obtenemos resultado de una consulta
        Query q = sesion.createQuery("from Personajes as Personajes where casas = " + n );
        
        List<Personajes> pers = q.list();
        if(pers.isEmpty()){
            System.out.println("No existen coincidencias en la Base de datos");
        }else{
            Iterator<Personajes> iterador= pers.iterator();

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
        }
    
        sesion.close();
        System.out.println("La sesion ha sido cerrada");
        


       return "";
    }
    
    public String consultaSexo(SessionFactory sf,String s){

        // Abrimos una sesión (conexión a la BD)
        Session sesion = sf.openSession();
    
        // Obtenemos resultado de una consulta
        Query q = sesion.createQuery("from Personajes as Personajes where sexo = " + "'" + s + "'" );
        
        List<Personajes> pers = q.list();
        if(pers.isEmpty()){
            System.out.println("No existen coincidencias en la Base de datos");
        }else{
            Iterator<Personajes> iterador= pers.iterator();

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
        }
    
        sesion.close();
        System.out.println("La sesion ha sido cerrada");
        


       return "";
    }
    
    
    public String consultaLibro(SessionFactory sf,int n){

        // Abrimos una sesión (conexión a la BD)
        Session sesion = sf.openSession();
    
        // Obtenemos resultado de una consulta
        Query q = sesion.createQuery("from Personajes as Personajes where libros = " + n );
        
        List<Personajes> pers = q.list();
        if(pers.isEmpty()){
            System.out.println("No existen coincidencias en la Base de datos");
        }else{
            Iterator<Personajes> iterador= pers.iterator();

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
        }
    
        sesion.close();
        System.out.println("La sesion ha sido cerrada");
        
       return "";
    }
    
  
}
