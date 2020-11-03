/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import config.GenerarSerie;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asus
 */
public class Controlador extends HttpServlet {

    //instanciar empleado y empleadoDAO
    Empleado em = new Empleado();
    EmpleadoDAO edao= new EmpleadoDAO();
    //instanciar cliente y clienteDAO
    Cliente cli = new Cliente();
    ClienteDAO clidao= new ClienteDAO();
    //instanciar producto y productoDAO
    Producto prod = new Producto();
    ProductoDAO prodao= new ProductoDAO();
    int idemp; //variable para editar el empleado
    int idcli; 
    int idprod;
   
    //Instanciar venta 
    Venta ven=new Venta();
    List<Venta>lista = new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;
    
    String numeroserie;
    VentaDAO vdao=new VentaDAO();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String menu= request.getParameter("menu");
            String accion=request.getParameter("accion");
            if (menu.equals("Principal")) {
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
            }
            if (menu.equals("Empleado")) {
                switch(accion){
                    case "Listar":
                        List lista=edao.listar();
                        request.setAttribute("empleados", lista);
                        break;
                    case "Agregar":
                        String dni=request.getParameter("txtDni");
                        String nom=request.getParameter("txtNombres");
                        String tel=request.getParameter("txtTel");
                        String est=request.getParameter("txtEstado");
                        String user=request.getParameter("txtUsuario");
                        em.setDni(dni);
                        em.setNom(nom);
                        em.setTel(tel);
                        em.setEstado(est);
                        em.setUser(user);
                        edao.agregar(em);
                        //Actualizar nuestra tabla empleados
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "Editar":
                        idemp=Integer.parseInt(request.getParameter("id"));
                        Empleado e=edao.listarId(idemp);
                        request.setAttribute("empleado", e);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String dni1=request.getParameter("txtDni");
                        String nom1=request.getParameter("txtNombres");
                        String tel1=request.getParameter("txtTel");
                        String est1=request.getParameter("txtEstado");
                        String user1=request.getParameter("txtUsuario");
                        em.setDni(dni1);
                        em.setNom(nom1);
                        em.setTel(tel1);
                        em.setEstado(est1);
                        em.setUser(user1);
                        em.setId(idemp);//enviar el id del empleado para actualizar
                        edao.actualizar(em);//para actualizar 
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);//mostrar la tabla con los datos actualizados
                        break;
                    case "Delete":
                        idemp=Integer.parseInt(request.getParameter("id"));
                        edao.delete(idemp);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);//mostrar la tabla con los datos actualizados
                        break;
                    default:
                        throw new AssertionError();
                }
                request.getRequestDispatcher("Empleado.jsp").forward(request, response);
            }    
            if (menu.equals("Cliente")) {
                switch(accion){
                    case "Listar":
                        List lista=clidao.listar();
                        request.setAttribute("clientes", lista);
                        break;
                     case "Agregar":
                        String dni=request.getParameter("txtDni");
                        String nom=request.getParameter("txtNombres");
                        String direc=request.getParameter("txtDire");
                        String est=request.getParameter("txtEstado");              
                        cli.setDni(dni);
                        cli.setNom(nom);
                        cli.setDirec(direc);
                        cli.setEstado(est);
                        clidao.agregar(cli);
                        //Actualizar nuestra tabla clientes
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                        break;
                    case "Editar":
                        idcli=Integer.parseInt(request.getParameter("id"));
                        Cliente cl=clidao.listarId(idcli);
                        request.setAttribute("cliente", cl);
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String dni1=request.getParameter("txtDni");
                        String nom1=request.getParameter("txtNombres");
                        String direc1=request.getParameter("txtDire");
                        String est1=request.getParameter("txtEstado");
                        cli.setDni(dni1);
                        cli.setNom(nom1);
                        cli.setDirec(direc1);
                        cli.setEstado(est1);
                        cli.setId(idcli);//enviar el id del empleado para actualizar
                        clidao.actualizar(cli);
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);//mostrar la tabla con los datos actualizados
                        break;
                    case "Delete":
                        idcli=Integer.parseInt(request.getParameter("id"));
                        clidao.delete(idcli);
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);//mostrar la tabla con los datos actualizados
                        break;
                    default:
                        throw new AssertionError();
                }
               request.getRequestDispatcher("Clientes.jsp").forward(request, response);              
            }
            if (menu.equals("Producto")) {
                switch(accion){
                    case "Listar":
                        List lista=prodao.listar();
                        request.setAttribute("productos", lista);
                        break;
                    case "Agregar":
                        String nom=request.getParameter("txtNombres");          
                        double prec=Double.parseDouble(request.getParameter("txtPrecio"));
                        int stock=Integer.parseInt(request.getParameter("txtStock"));
                        String est=request.getParameter("txtEstado");              
                        prod.setNom(nom);
                        prod.setPrec(prec);
                        prod.setStock(stock);
                        prod.setEstado(est);
                        prodao.agregar(prod);
                        //Actualizar nuestra tabla productos
                         request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    case "Editar":
                        idprod=Integer.parseInt(request.getParameter("id"));
                        Producto pro=prodao.listarId(idprod);
                        request.setAttribute("producto", pro);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String nom1=request.getParameter("txtNombres");
                        double prec1=Double.parseDouble(request.getParameter("txtPrecio"));
                        int stock1=Integer.parseInt(request.getParameter("txtStock"));
                        String est1=request.getParameter("txtEstado");
                        prod.setNom(nom1);
                        prod.setPrec(prec1);
                        prod.setStock(stock1);
                        prod.setEstado(est1);
                        prod.setId(idprod);//enviar el id del empleado para actualizar
                        prodao.actualizar(prod);//para actualizar 
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);//mostrar la tabla con los datos actualizados
                        break;
                    case "Delete":
                        idprod=Integer.parseInt(request.getParameter("id"));
                        prodao.delete(idprod);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);//mostrar la tabla con los datos actualizados
                        break;
                    default:
                        throw new AssertionError();
                }
               request.getRequestDispatcher("Producto.jsp").forward(request, response);
            }
            if (menu.equals("NuevaVenta")) {
                switch(accion){
                    case "BuscarCliente":
                        //Recibir de la caja de texto codigoCliente(dni)
                        String dni=request.getParameter("codigocliente");
                        cli.setDni(dni);
                        cli=clidao.buscar(dni);
                        request.setAttribute("c", cli);//los datos del cliente al formulario
                        break;
                    case "BuscarProducto":
                        int id=Integer.parseInt(request.getParameter("codigoproducto"));
                        prod=prodao.listarId(id);
                        request.setAttribute("c", cli);//los datos del cliente al formulario
                        request.setAttribute("producto", prod);
                        request.setAttribute("lista", lista);
                        request.setAttribute("totalPagar", totalPagar);
                        break;
                    case "Agregar":
                        request.setAttribute("c", cli);//los datos del cliente al formulario
                        totalPagar=0.0;
                        item = item+1;
                        cod=prod.getId();
                        descripcion=request.getParameter("nombreproducto");
                        precio=Double.parseDouble(request.getParameter("precio"));
                        cant=Integer.parseInt(request.getParameter("cantidad"));
                        subtotal=precio*cant;
                        //Captura todos los valores que ingresa por el formulario
                        ven=new Venta();
                        ven.setItem(item);
                        ven.setIdproducto(cod);
                        ven.setDescripcionP(descripcion);
                        ven.setPrecio(precio);
                        ven.setCantidad(cant);
                        ven.setSubtotal(subtotal);
                        //
                        lista.add(ven);
                        for (int i = 0; i < lista.size(); i++) {
                            totalPagar=totalPagar+lista.get(i).getSubtotal();
                        }
                        request.setAttribute("totalPagar", totalPagar);
                        request.setAttribute("lista", lista);
                        break;
                    case "GenerarVenta":
                        //Actualización del stock
                        for (int i = 0; i < lista.size(); i++) {
                            Producto pr=new Producto();
                            int cantidad=lista.get(i).getCantidad();
                            int idproducto=lista.get(i).getIdproducto();
                            ProductoDAO aO=new ProductoDAO();
                            pr=aO.buscar(idproducto);
                            int sact=pr.getStock()-cantidad;
                            aO.actualizarstock(idproducto, sact);
                        }
                        //Guardar Venta
                        ven.setIdcliente(cli.getId());
                        ven.setIdempleado(2);
                        ven.setNumSerie(numeroserie);
                        ven.setFecha("2020-10-31");
                        ven.setMonto(totalPagar);
                        ven.setEstado("1");
                        vdao.guardarVenta(ven);
                        //Guardar Detalle ventas
                        int idv=Integer.parseInt(vdao.IdVentas());
                        for (int i = 0; i < lista.size(); i++) {
                            ven=new Venta();
                            ven.setId(idv);
                            ven.setIdproducto(lista.get(i).getIdproducto());
                            ven.setCantidad(lista.get(i).getCantidad());
                            ven.setPrecio(lista.get(i).getPrecio());
                            vdao.guardarDetalleVentas(ven);
                        }
                        break; 
                    default:
                        numeroserie =vdao.GenerarSerie();
                        if (numeroserie==null) {
                            numeroserie="00000001";
                            request.setAttribute("nserie", numeroserie);
                        }
                        else{
                            int incrementar=Integer.parseInt(numeroserie);//convertir el int en string
                            GenerarSerie gser=new GenerarSerie();
                            numeroserie=gser.NumeroSerie(incrementar);
                            request.setAttribute("nserie", numeroserie);
                        }
                        request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                }
               request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
