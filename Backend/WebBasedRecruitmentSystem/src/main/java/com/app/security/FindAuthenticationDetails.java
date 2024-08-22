package com.app.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FindAuthenticationDetails {

	public Long getUserId()
	{
		// get the authentication object from security context
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		
		if(auth!=null && auth.isAuthenticated())
		{
			Object credentials = auth.getCredentials();
			if(credentials instanceof Long)
			{
				Long userId=(Long)credentials;
				return userId;
			}
		}
		
		return null;
	}
	
}
