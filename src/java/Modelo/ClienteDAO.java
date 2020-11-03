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
public class ClienteDAO {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    //metodo buscar
    public Cliente buscar(String dni){
        Cliente cli=new Cliente();
        String sql="Select * from cliente where Dni="+dni;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                cli.setId(rs.getInt(1));
                cli.setDni(rs.getString(2));
                cli.setNom(rs.getString(3));
                cli.setDirec(rs.getString(4));
                cli.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
        }
        return cli;
    }
    
    //Operaciones CRUD
    
    public List listar(){
        String sql="select * from cliente";
        List<Cliente>lista=new ArrayList<>();
        try {
            con = cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                Cliente cli=new Cliente();
                cli.setId(rs.getInt(1));
                cli.setDni(rs.getString(2));
                cli.setNom(rs.getString(3));
                cli.setDirec(rs.getString(4));
                cli.setEstado(rs.getString(5));
                lista.add(cli);
            }
        } catch (Exception e) {
        }
         return lista;
    }
    
    public int agregar(Cliente cli){
        String sql="insert into cliente(Dni, Nombres, Direccion, Estado)values(?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, cli.getDni());
            ps.setString(2, cli.getNom());
            ps.setString(3, cli.getDirec());
            ps.setString(4, cli.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return resp;
    }
    
    public Cliente listarId(int id){
        Cliente cli=new Cliente();
        String sql = "select * from cliente where IdCliente="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                cli.setDni(rs.getNString(2));
                cli.setNom(rs.getString(3));
                cli.setDirec(rs.getString(4));
                cli.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
        }
        return cli;
    }
    
    public int actualizar(Cliente cli){
        String sql="update cliente set Dni=?, Nombres=?, Direccion=?, Estado=? where IdCliente=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, cli.getDni());
            ps.setString(2, cli.getNom());
            ps.setString(3, cli.getDirec());
            ps.setString(4, cli.getEstado());
            ps.setInt(5, cli.getId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return resp;
    }
    
    public void delete(int id){
        String sql="delete from cliente where IdCliente="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    } 
}
