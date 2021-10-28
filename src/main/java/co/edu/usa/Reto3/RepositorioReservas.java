/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.Reto3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jhon Alejandro
 */
@Repository
public class RepositorioReservas {
    @Autowired
    private InterfaceReservas crud4;
    
    public List<Reservas> getAll(){
        return (List<Reservas>) crud4.findAll();
    }
    
    public Optional<Reservas> getReservas(int id){
        return crud4.findById(id);
    }
    
    public Reservas save(Reservas reservation){
        return crud4.save(reservation);
    }
    
    public void delete(Reservas reservation){
        crud4.delete(reservation);
    }
     public List<Reservas> ReservacionStatus (String status){
         return crud4.findAllByStatus(status);
     }
     
     public List<Reservas> ReservacionTiempo (Date a, Date b){
         return crud4.findAllByStartDateAfterAndStartDateBefore(a, b);
     }
   
     public List<ContadorClientes> getTopClientes(){
         List<ContadorClientes> res=new ArrayList<>();
         List<Object[]>report = crud4.countTotalReservationsByClient();
         for(int i=0; i<report.size();i++){
             res.add(new ContadorClientes((Long)report.get(i)[1],(Cliente) report.get(i)[0]));
         
         }
         return res;
     }
}