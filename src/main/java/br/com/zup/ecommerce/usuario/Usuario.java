package br.com.zup.ecommerce.usuario;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDateTime criadoEm = LocalDateTime.now();

	@Email
	@NotNull
	@NotBlank
	private String email;

	@NotBlank
	@NotNull
	@Length(min = 6)
	private String password;

	public Usuario() {
	}

	public Usuario(@Email @NotNull @NotBlank String email,
		@Valid @NotNull ConverterSenha senhaConverter) {

		Assert.isTrue(StringUtils.hasLength(email), "email não pode ser em branco");
		Assert.notNull(senhaConverter, "O Objeto tipo Senha Converter não pode ser nulo");

		this.email = email;
		this.password = senhaConverter.hash();
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id =" + id + ", email=" + email + ", password=" + password + "]";
	}

}
