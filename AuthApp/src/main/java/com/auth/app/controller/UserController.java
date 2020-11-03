package com.auth.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.app.annotation.CurrentUser;
import com.auth.app.event.OnUserAccountChangeEvent;
import com.auth.app.exception.UpdatePasswordException;
import com.auth.app.model.CustomUserDetails;
import com.auth.app.model.Role;
import com.auth.app.model.User;
import com.auth.app.model.payload.ApiResponse;
import com.auth.app.model.payload.LogOutRequest;
import com.auth.app.model.payload.UpdatePasswordRequest;
import com.auth.app.response.CommonUserResponse;
import com.auth.app.service.AuthService;
import com.auth.app.service.CustomUserDetailsService;
import com.auth.app.service.UserService;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Api(tags = "User Rest API")

public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    private final AuthService authService;

    private final UserService userService;

    private final ApplicationEventPublisher applicationEventPublisher;
    private final CustomUserDetailsService customuserService;

    @Autowired
    public UserController(AuthService authService, UserService userService, ApplicationEventPublisher applicationEventPublisher,CustomUserDetailsService userservice) {
        this.authService = authService;
        this.userService = userService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.customuserService=userservice;
    }
    
    /**
     * Updates the password of the current logged in user
     */
    @PostMapping("/password/update")
    @ApiOperation(value = "Allows the user to change his password once logged in by supplying the correct current " +
            "password")
    public ResponseEntity updateUserPassword(@RequestHeader(value = "Authorization",required = true) String token,
    		@ApiParam(value = "The UpdatePasswordRequest payload") @Valid @RequestBody UpdatePasswordRequest updatePasswordRequest) {

        return authService.updatePassword(updatePasswordRequest)
                .map(updatedUser -> {
                    OnUserAccountChangeEvent onUserPasswordChangeEvent = new OnUserAccountChangeEvent(updatedUser, "Update Password", "Change successful");
                    applicationEventPublisher.publishEvent(onUserPasswordChangeEvent);
                    return ResponseEntity.ok(new ApiResponse(true, "Password changed successfully"));
                })
                .orElseThrow(() -> new UpdatePasswordException("--Empty--", "No such user present."));
    }

    /**
     * Log the user out from the app/device. Release the refresh token associated with the
     * user device.
     */
    @PostMapping("/logout")
    @ApiOperation(value = "Logs the specified user device and clears the refresh tokens associated with it")
    public ResponseEntity logoutUser(@ApiParam(value = "The LogOutRequest payload") @Valid @RequestBody(required = true) LogOutRequest logOutRequest,
    		@RequestHeader(value = "Authorization",required = true) String token) {
        userService.logoutUser(logOutRequest);
        return ResponseEntity.ok(new ApiResponse(true, "Log out successful"));
    }
    @GetMapping("/getuserinfo")
    @ApiOperation(value = "Returns the current user profile data")
    public ResponseEntity getUserProfileData(@RequestHeader(value = "Authorization",required = true) String token,@RequestParam(value = "useremail",required = true) String email) {
    	Optional<User> user= customuserService.loadUserByEmail(email);
    	      if(user.get().equals(null)||user.get().getId()==0) {
    	    	  return ResponseEntity.ok(new ApiResponse(false, "No Such user Exist"));
    	      }
    	      return ResponseEntity.ok(new ApiResponse(true, this.prepareUserData(user.get())));
    }
    
    private CommonUserResponse prepareUserData(User user) {
    	CommonUserResponse userdata=new CommonUserResponse();
    	userdata.setActive(user.getActive());
    	userdata.setEmailId(user.getEmail());
    	userdata.setEmailVerified(user.getEmailVerified());
    	userdata.setFirstName(user.getFirstName());
    	userdata.setLastName(user.getLastName());
    	userdata.setUserId(user.getId());
    	userdata.setUserName(user.getUsername());
    	Set<Role> role=user.getRoles();
    	userdata.setRole(role);
    	return userdata;
    }
    
}
