package org.ultimoAlijo.model;

public class Producto {
    private String id;
    private String codigo;
    private String imagen;
    private String nombre;
    private int stock;
    private String descripcion;
    private double precio;
    private int descuento;
    private double calificacion;

    public Producto() {
    }

    public Producto(String codigo, String imagen, String nombre,int stock, String descripcion, double precio, int descuento, double calificacion) {

        this.codigo = codigo;
        this.imagen = imagen;
        this.nombre = nombre;
        this.stock = stock;
        this.descripcion = descripcion;
        this.precio = precio;
        this.descuento = descuento;
        this.calificacion = calificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Producto {"+"\n{" +
                "Codigo= " + codigo + '\n' +
                "Imagen= " + imagen +".png"+ '\n' +
                "Nombre= " + nombre + '\n' +
                "Stock= " + stock +"\n"+
                "Descripcion= " + descripcion + '\n' +
                "Precio= $" + precio + '\n' +
                "Descuento= " + descuento + '%'+'\n' +
                "Calificacion= " + calificacion + '\n' +
                '}'+'\n';
    }
}
