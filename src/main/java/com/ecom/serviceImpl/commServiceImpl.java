package com.ecom.serviceImpl;

import java.net.http.HttpRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ecom.service.commonService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class commServiceImpl implements commonService{

	@Override
	public void removeSessionMsg() {
		
		HttpServletRequest request = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
	    HttpSession ses=request.getSession();
	    ses.removeAttribute("succMsg");
	    ses.removeAttribute("errorMsg");
	}

}

