package com.java.controller;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.model.Ticket;
import com.java.service.TicketService;

@RestController
@RequestMapping(value = "/api/tickets")
public class TicketController {
	org.jboss.logging.Logger log=LoggerFactory.logger(TicketController.class);
	@Autowired
	TicketService tservcie;

	@PostMapping(value = "/create")
	public Ticket create(@RequestBody Ticket t) {
		
	   return tservcie.createTicket(t);
	}
	@GetMapping(value = "/ticket/{ticketid}")
	public Optional<Ticket> getById(@PathVariable("ticketid") Integer id) {

		return tservcie.getTicketById(id);
	}
    
	
	
	@GetMapping(value = "/ticket/all")
	public Iterable<Ticket> getAll() {

		return tservcie.getAllticket();
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public void deleteId(@PathVariable("id") Integer id) {
		 tservcie.deleteById(id);
	}
	@PutMapping(value = "/update/{id}/{emailid.+}")
	public Ticket updateTicket(@RequestBody Ticket t) {
	   return tservcie.updateTicket(t);
	}
}
        