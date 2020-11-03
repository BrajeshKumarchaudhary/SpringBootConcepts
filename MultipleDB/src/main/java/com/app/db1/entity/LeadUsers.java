package com.app.db1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "lead_users")
public class LeadUsers {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	    private int id;
	    @Column(name = "role", nullable = true)
	    private String role;
	    @Column(name = "organization_id", nullable = true)
	    private long orgId;
	    
	    @Column(name = "is_head_user", nullable = true)
	    private int isheadUser;
	    
	    @Column(name = "user_name", nullable = true)
	    private String userName;
	    
	    @Column(name = "name", nullable = true)
	    private String name;
	    
	    @Column(name = "middle_name", nullable = true)
	    private String middleName;
	    
	    @Column(name = "last_name", nullable = true)
	    private String lastName;
	    
	    @Column(name = "email", nullable = true)
	    private String email;
	    
	    @Column(name = "domain_id", nullable = true)
	    private Long domainId;
	    
	    @Column(name = "mobile", nullable = true)
	    private String mobile;
	    
	    @Column(name = "alternate_mobile_number", nullable = true)
	    private long altMobile;
	    
	    @Column(name = "country_id", nullable = true)
	    private int countryId;
	    
	    @Column(name = "state_id", nullable = true)
	    private int stateId;
	    
	    @Column(name = "status", nullable = true)
	    private String status;
	    
	    @Column(name = "is_user_validation_required", nullable = true)
	    private int isValidaionRequired;
	    
	    @Column(name = "domain_url", nullable = true)
	    private String domainUrl;
	    
	    @Column(name = "project_name", nullable = true)
	    private String projectName;
	    
	    @Column(name = "project_id", nullable = true)
	    private long projectId;
	    
	    @Column(name = "project_info", nullable = true)
	    private String projectInfo;
	    
	    @Column(name = "project_url", nullable = true)
	    private String projectUlr;
	    
	    @Column(name = "project_lat", nullable = true)
	    private float projectLat;
	    
	    @Column(name = "project_long", nullable = true)
	    private float projectLong;

	    public static final String STATUS_ACTIVE = "ACTIVE";
	    public static final String STATUS_INACTIVE = "INACTIVE";
	    
	    public static final String ROLE_SELF = "SELF";
	    public static final String ROLE_ADMIN = "ADMIN";
	    public static final String ROLE_USER = "USER";
}
