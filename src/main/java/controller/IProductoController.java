/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.entity.Producto;
import excepciones.ControllerException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;

/**
 *
 * @author LABORATORIOS
 */
public interface IProductoController {
    void agregarProducto(Producto producto) throws ControllerException;
    void actualizarProducto(Producto producto) throws ControllerException;
    void eliminarProducto(ObjectId _id) throws ControllerException;
    DefaultTableModel obtenerTabla() throws ControllerException;
    Producto buscarProducto(String nombre) throws ControllerException;
    List<Producto> buscarTodos() throws ControllerException;
    
}
