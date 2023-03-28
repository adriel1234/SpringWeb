package com.web.SpringWeb.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.SpringWeb.models.Administrador;
import com.web.SpringWeb.repositorio.AdministradoresRepo;
import com.web.SpringWeb.servico.CookieService;


@Controller
public class LoginController {
	
	@Autowired
	private AdministradoresRepo repo;
	
	@GetMapping("/login")
	public String index() {
		return "login/index";
	}
	
	@PostMapping("/logar")
	public String logar(Model model,String email,String senha,String lembrar,HttpServletResponse response) {
		 
		Administrador adm = this.repo.login(email,senha);
		
		if(adm != null) {
			CookieService.setCookie(response,"usuarioId",String.valueOf(adm.getId()),4);
			return "redirect:/";
		}
		model.addAttribute("erro","Úsuario ou senha inválidos");
		return "login/index";
	}
}
