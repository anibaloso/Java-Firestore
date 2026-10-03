package org.example;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try{
            //Ruta al archivo JSON
            FileInputStream serviceAccount= new FileInputStream("ultimoalijo-firebase-adminsdk-fbsvc-7790c9fec1.json");

            //Configurar credenciales
            FirebaseOptions options= FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            //iniciar firebase
            FirebaseApp.initializeApp(options);
            System.out.println("¡Firebase inicializado correctamente!");

            //Obtener la instancia de Firestore
            Firestore db= FirestoreClient.getFirestore();

            //1.- Crear datos de prueba para guardar
            Map<String, Object> data= new HashMap<>();
            data.put("nombre","Juan Pérez");
            data.put("rol", "Desarrollador");
            data.put("edad",30);

            Map<String, Object> data1= new HashMap<>();

            data1.put("nombre","Maria Martinez");
            data1.put("rol", "Diseñadora");
            data1.put("edad",38);

            System.out.println("Intentando Guardar datos en Firestore");

            //Se intentara guardar en la coleccion usuarios si no hay la creara
            //Las operaciones en Firestore devuelven en ApiFuture(Son asincronas)
            ApiFuture<DocumentReference> addedDocRef= db.collection("usuarios").add(data1);

            //.get() bloquea el hilo hasta que la operacion termina
            //Esto es necesario en un programa de consola simple,sino el programa terminaria antes de guardar.
            System.out.println("Documento guardado con el ID: "+ addedDocRef.get().getId());



            //2.- Buscando usuarios que sean desarrolladores
            ApiFuture<QuerySnapshot> query =db.collection("usuarios")
                    .whereEqualTo("rol","Desarrollador")
                    .get();

            QuerySnapshot querySnapshot=query.get();
            List<QueryDocumentSnapshot> documents= querySnapshot.getDocuments();

            System.out.println("==== LISTADO COMPLETO DE 'DESARROLLADOR' ====");
            for (QueryDocumentSnapshot document : documents) {
                System.out.println("ID del documento: "+document.getId());
                System.out.println("Nombre: "+document.getString("nombre"));
                System.out.println("Edad: "+document.getLong("edad"));
                System.out.println("Datos completos: "+document.getData());
                System.out.println("============");
            }

            //3.-Actualizando un campo especifico sabiendo el ID

            DocumentReference docRef= db.collection("usuarios").document("nZkFAVqB1ybiRcbRa1J4");

            //Se le cambiara el rol y la edad
            ApiFuture<WriteResult>futureUpdate= docRef.update(
                    "rol","Maquillador",
                    "edad", 24
            );

            System.out.println("Documento actualizado a las: "+futureUpdate.get().getUpdateTime());

            //3.-Actualizando un campo obteniendo el ID
            ApiFuture<QuerySnapshot> futureQuery =db.collection("usuarios")
                    .whereEqualTo("nombre","Juan Pérez")
                    .get();
            List<QueryDocumentSnapshot> documentsList= futureQuery.get().getDocuments();

            if(documentsList.isEmpty()){
                System.out.println("No se encontró ningun usuario.");
            }else {
                for (QueryDocumentSnapshot queryDocumentSnapshot : documentsList) {
                    ApiFuture<WriteResult> futureDelete=queryDocumentSnapshot.getReference().delete();

                    WriteResult result=futureDelete.get();

                    System.out.println("Se ah borrado el usuario. su usuario real era: "+queryDocumentSnapshot.getId());
                }
            }



        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }




    }
}