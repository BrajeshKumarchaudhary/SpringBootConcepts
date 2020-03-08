package com.java.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.TicketDao;
import com.java.model.Ticket;

@Service
public class TicketService {

	@Autowired
	TicketDao tdao;
	
	public Ticket createTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return tdao.save(ticket);
	}

	public Optional<Ticket> getTicketById(Integer id) {
		// TODO Auto-generated method stub
		return tdao.findById(id);
	}

	public Iterable<Ticket> getAllticket() {
		// TODO Auto-generated method stub
		return tdao.findAll();
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		tdao.deleteById(id);
	}

}
