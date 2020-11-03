package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.versioning.Name;
import com.app.versioning.PersonV1;
import com.app.versioning.PersonV2;

@RestController
public class VersioningController {

	//by params
	@GetMapping(value="/name",params = "vesrion=1")
	public String getName() {
		return new PersonV1("Brajesh").getName();
	}
	@GetMapping(value="/name",params = "vesrion=2")
	public String getNameV2() {
		return new PersonV2(new Name("Brajesh","Kumar")).getName().toString();
	}
	
    //byHeaders

	@GetMapping(value="/name",headers = "X-api=v1")
	public PersonV1 header1() {
		return new PersonV1("Brajesh");
	}
	@GetMapping(value="/name",headers = "X-api=v2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Brajesh","Kumar"));
	}
	
	//by produces.
	
	@GetMapping(value="/name",produces = "application/X-api-v1")
	public PersonV1 produces1() {
		return new PersonV1("Brajesh");
	}
	@GetMapping(value="/name",produces = "application/X-api-v2")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Brajesh","Kumar"));
	}
}
