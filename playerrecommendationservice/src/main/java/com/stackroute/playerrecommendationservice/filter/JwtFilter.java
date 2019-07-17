package com.stackroute.playerrecommendationservice.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilterBean{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String token = httpServletRequest.getParameter("Authorization");
		if (StringUtils.isEmpty(token) || StringUtils.equalsIgnoreCase(token, "null")) {
			throw new ServletException("Missing Authorization");
		}
		Claims claims = Jwts.parser().setSigningKey("secretSecurityKey").parseClaimsJws(token).getBody();
		httpServletRequest.setAttribute("claims", claims);
		chain.doFilter(request, response);
	}

}
