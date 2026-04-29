/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.dao.IProductoDAO;
import com.mycompany.dao.ProductoDAO;
import com.mycompany.entity.Producto;
import excepciones.ControllerException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;

/**
 *
 * @author LABORATORIOS
 */
public class ProductoController implements IProductoController{
    private IProductoDAO pDAO;

    public ProductoController() {
        pDAO = new ProductoDAO();
    }
    
    private boolean camposVacios(Producto producto){
        return producto.getNombre().isBlank() || producto.getCategoria() == null || producto.getCategoria().isEmpty() || producto.getCantidad() == null || producto.getPrecio() == null || producto.getDirecciones() == null || producto.getDirecciones().isEmpty() || producto.getProveedores() == null || producto.getProveedores().isEmpty();
    }

    @Override
    public void agregarProducto(Producto producto) throws ControllerException{
        if(producto.getNombre().isEmpty()){
            throw new ControllerException("El nombre no debe estar vacio");
        }
        if(producto.getPrecio() <= 0){
            throw new ControllerException("El producto debe tener un precio positivo");
        }
        if(camposVacios(producto)){
            throw new ControllerException("Hay campos requeridos que estan vacios, debe llenarlos");
        }
        pDAO.guardarProducto(producto);
    }

    @Override
    public void actualizarProducto(Producto producto) throws ControllerException{
        if(producto.getId() == null){
            throw new ControllerException("Se necesita el id del producto que se quiere actualizar");
        }
        if(producto.getNombre().isEmpty()){
            throw new ControllerException("El nombre no debe estar vacio");
        }
        if(producto.getPrecio() <= 0){
            throw new ControllerException("El producto debe tener un precio positivo");
        }
        if(camposVacios(producto)){
            throw new ControllerException("Hay campos requeridos que estan vacios, debe llenarlos");
        }
        pDAO.editar(producto);
    }

    @Override
    public void eliminarProducto(ObjectId _id) throws ControllerException{
        pDAO.eliminar(_id);
    }

    @Override
    public DefaultTableModel obtenerTabla() {
        String[] columnas = {"ID", "NOMBRE", "CANTIDAD", "PRECIO"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Producto> lista = pDAO.listar();
        lista.forEach(p -> modelo.addRow(new Object[]{p.getId(), p.getNombre(), p.getCantidad(), p.getPrecio()}));
        return modelo;
    }
    
    @Override
    public List<Producto> buscarTodos() {
        return pDAO.listar();
    }

    @Override
    public Producto buscarProducto(String nombre) throws ControllerException {
        return pDAO.buscarPorNombre(nombre);
    }
}