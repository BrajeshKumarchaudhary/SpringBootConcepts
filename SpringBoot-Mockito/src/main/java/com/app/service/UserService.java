package com.app.service;

import java.util.Arrays;
import java.util.LinkedList;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	
	public LinkedList<Integer> getUserId(){
		LinkedList<Integer> l=new LinkedList<>();
		l.add(1);
		return l;
	}
}
