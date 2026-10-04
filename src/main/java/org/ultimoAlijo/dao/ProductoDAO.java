package org.ultimoAlijo.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.ultimoAlijo.config.ConexionFirebase;
import org.ultimoAlijo.model.Producto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProductoDAO {
    private final Firestore db=ConexionFirebase.getInstancia();
    private final String COLECCION="productos";


    public ProductoDAO() {
    }

    //Crear el producto(Guardar el producto)
    public String guardarProducto(Producto producto)throws ExecutionException,InterruptedException{
        ApiFuture <DocumentReference> futuro=db.collection(COLECCION).add(producto);
        return futuro.get().getId();
    }

    //Leer (Obtener todos los productos)
    public List<Producto> obtenerTodos() throws ExecutionException, InterruptedException{
        List<Producto> lista= new ArrayList<>();
        ApiFuture<QuerySnapshot> futuro =db.collection(COLECCION).get();

        for (QueryDocumentSnapshot doc : futuro.get().getDocuments()) {
            Producto prod=doc.toObject(Producto.class);
            prod.setCodigo(doc.getId());
            lista.add(prod);
        }
        return lista;
    }

    //
    public String generarCodigoTotal(String tipoProducto) throws ExecutionException, InterruptedException{
        //se extraen las 3 primeras letras del tipoProducto
        String preCodigo=tipoProducto.trim().substring(0,3).toUpperCase();

        //Obtener total de cantidad de productos
        ApiFuture<QuerySnapshot> futuro= db.collection(COLECCION).get();
        int cantidadTotal=futuro.get().getDocuments().size();

        //El ultimo numero sera el total + 1
        cantidadTotal++;

        return preCodigo+"-"+cantidadTotal;

    }

    //Eliminar
    public boolean eliminarProducto(String codigo) throws ExecutionException, InterruptedException{
        DocumentReference docRef = db.collection(COLECCION).document(codigo);
        ApiFuture<WriteResult> futuro=docRef.delete();
        return futuro.get() != null;
    }

}
