package br.com.zup.ecommerce.usuario;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ConverterSenha {
	
	private String senha;

	public ConverterSenha(@NotBlank @Length(min = 6)String senha) {
		super();
		this.senha = senha;
	}
	
	
	public String hash() {
		return new BCryptPasswordEncoder().encode(senha);
	}
	
	

}
