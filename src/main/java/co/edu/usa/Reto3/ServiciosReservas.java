/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.Reto3;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jhon Alejandro
 */
@Service
public class ServiciosReservas {
    @Autowired
    private RepositorioReservas metodosCrud;
    
    public List<Reservas> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Reservas> getReservas(int reservationId){
        return metodosCrud.getReservas(reservationId);
    }
    
    public Reservas save(Reservas reservation){
        if(reservation.getIdReservation()==null){
            return metodosCrud.save(reservation);
        }else{
            Optional<Reservas> e=metodosCrud.getReservas(reservation.getIdReservation());
            if(e.isEmpty()){
                return metodosCrud.save(reservation);
            }else{
                return reservation;
            }
        }
    }
    
    public Reservas update(Reservas reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservas> e=metodosCrud.getReservas(reservation.getIdReservation());
            if(!e.isEmpty()){
                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }
    
    public boolean deleteReservation(int reservationId){
        Boolean aBoolean = getReservas(reservationId).map(reservation ->{
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
