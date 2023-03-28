package com.web.SpringWeb.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.web.SpringWeb.models.Administrador;

public interface AdministradoresRepo extends CrudRepository<Administrador, Integer> {
	@Query(value="SELECT CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END from administradores where id=:id", nativeQuery=true)
	public boolean exist(int id);
	
	@Query(value="SELECT *  from administradores where email=:email and senha=:senha", nativeQuery=true)
	public Administrador login(String email,String senha);
	
}
