package org.ultimoAlijo;

import org.ultimoAlijo.dao.ProductoDAO;
import org.ultimoAlijo.model.Producto;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ProductoDAO productoDAO= new ProductoDAO();
        Scanner scan= new Scanner(System.in);
        System.out.println("==== SISTEMA GESTION DE TIENDA ====");

//      ProductoDAO productoDAO= new ProductoDAO();
        System.out.println("Cual es el tipo del producto nuevo");
        String nom= scan.nextLine();
        String codigo=productoDAO.generarCodigoTotal(nom);
        System.out.println("Cual es el nombre del producto nuevo");
        String nombre= scan.nextLine();
        System.out.println("Cual es el stock del producto nuevo");
        int stock= scan.nextInt();
        scan.nextLine();
        System.out.println("Descripcion del producto nuevo");
        String descripcion= scan.nextLine();
        System.out.println("Cual es el valor del producto nuevo");
        int precio = scan.nextInt();
        scan.nextLine();
        System.out.println(codigo);
        try{
            Producto productoNuevo= new Producto(
                    codigo,
                    "image/"+codigo.toLowerCase(),
                    nombre,
                    stock,
                    descripcion,
                    precio,
                    0,
                    0);
            productoDAO.guardarProducto(productoNuevo);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}