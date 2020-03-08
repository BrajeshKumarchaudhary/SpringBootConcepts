package com.java.dao;

import org.springframework.data.repository.CrudRepository;

import com.java.model.Ticket;

public interface TicketDao extends CrudRepository<Ticket,Integer> {

}
