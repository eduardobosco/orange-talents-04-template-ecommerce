package br.com.zup.ecommerce.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zup.ecommerce.compartilhado.UniqueValue;



public class UsuarioRequest {
	
	@NotBlank
	@Email
	@UniqueValue(domainClass=Usuario.class, fieldName="email", message="Esta email ja esta cadastrado")
	private String email;
	
	@NotBlank
	@Length(min=6)
	private String password;
	
	public UsuarioRequest() {}

	public UsuarioRequest(@NotBlank @Email String email,
			@NotBlank @Length(min=6) String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Usuario converter() {
		return new Usuario(email, new ConverterSenha(password));
	}
}
