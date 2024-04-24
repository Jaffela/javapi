package test;


import Service.reservationService;
import entite.reservation;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        // Create an instance of reservationService
        reservationService service = new reservationService();

        // Test the add method
        reservation newReservation = new reservation(Date.valueOf("2024-04-16"), Date.valueOf("2024-04-20"), "Service");
        service.add(newReservation);
        System.out.println("New reservation added: " + newReservation);

        // Test the getAll method
        service.getAll().forEach(System.out::println);


        // Test the update method
        reservation reservationToUpdate = service.getAll().get(0); // Assuming there is at least one reservation in the database
        reservationToUpdate.setStatut("Confirmed");
        service.update(reservationToUpdate);
        System.out.println("Updated reservation: " + reservationToUpdate);

        // Test the delete method
        reservation reservationToDelete = service.getAll().get(10); // Assuming there is at least one reservation in the database
        service.delete(reservationToDelete);
        System.out.println("Deleted reservation: " + reservationToDelete);

        // Display all reservations after delete
        service.getAll().forEach(System.out::println);

    }
}
