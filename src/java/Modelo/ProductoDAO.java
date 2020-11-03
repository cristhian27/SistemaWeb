/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class ProductoDAO {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public Producto buscar(int id){
        Producto p=new Producto();
        String sql="select * from producto where idproducto="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setPrec(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
        }
        return p;
    }
    
    public int actualizarstock(int id, int stock){
        String sql="update producto set Stock=? where idproducto=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return resp;
    }
    
      
    //Operaciones CRUD
    public List listar(){
        String sql="select * from producto";
        List<Producto> lista=new ArrayList<>();
        try {
            con= cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                Producto prod=new Producto();
                prod.setId(rs.getInt(1));
                prod.setNom(rs.getString(2));
                prod.setPrec(rs.getDouble(3));
                prod.setStock(rs.getInt(4));
                prod.setEstado(rs.getString(5));
                lista.add(prod);
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public int agregar(Producto prod){
        String sql="insert into producto(Nombres, Precio, Stock, Estado)values(?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, prod.getNom());
            ps.setDouble(2, prod.getPrec());
            ps.setInt(3, prod.getStock());
            ps.setString(4, prod.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return resp;
    }
    
    public Producto listarId(int id){
        Producto prod= new Producto();
        String sql="select * from producto where IdProducto="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                prod.setId(rs.getInt(1));
                prod.setNom(rs.getString(2));
                prod.setPrec(rs.getDouble(3));
                prod.setStock(rs.getInt(4));
                prod.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
        }
        return prod;
    }
    
    public int actualizar(Producto prod){
        String sql="update producto set Nombres=?, Precio=?, Stock=?, Estado=? where IdProducto=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, prod.getNom());
            ps.setDouble(2, prod.getPrec());
            ps.setInt(3, prod.getStock());
            ps.setString(4, prod.getEstado());
            ps.setInt(5, prod.getId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return resp;
    }
    
    public void delete(int id){
        String sql="delete from producto where IdProducto="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
            
}
