package com.web.SpringWeb.servico;


import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieService {
	public static void setCookie(HttpServletResponse response,String key,String valor,int segundos) {
		Cookie cookie = new Cookie(key, valor);
		cookie.setMaxAge(segundos);
		
		response.addCookie(cookie);
	}
	
	public static String getCookie(HttpServletRequest request,String key) {
		
		return Optional.ofNullable(request.getCookies())
				.flatMap(cookies -> Arrays.stream(cookies)
				.filter(cookie -> key.equals(cookie.getName()))
				.findAny()).map(e -> e.getValue()).orElse(null);
	}

}
