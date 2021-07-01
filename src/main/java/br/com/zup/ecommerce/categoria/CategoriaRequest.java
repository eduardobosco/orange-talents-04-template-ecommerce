package br.com.zup.ecommerce.categoria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zup.ecommerce.compartilhado.ExistsId;
import br.com.zup.ecommerce.compartilhado.UniqueValue;

public class CategoriaRequest {
	
	@NotBlank
	@UniqueValue(domainClass=Categoria.class, fieldName="nome", message="Categoria ja esta cadastrado")
	private String nome;
	
	@Positive
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoriaMae;

	

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdCategoriaMae(Long idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}
	
	@Override
	public String toString() {
		return "CategoriaRequest [nome=" + nome + ", idCategoriaMae=" + idCategoriaMae + "]";
	}

	public Categoria toModel(CategoriaRepository categoriaRepository) {
		Categoria categoria = new Categoria(nome);
		if(idCategoriaMae !=null) {
			Categoria categoriaMae = categoriaRepository.getOne(idCategoriaMae);
			Assert.notNull(categoriaMae, "O ID da categoria mae prcisa ser válido");
			categoria.setCategoriaMae(categoriaMae);
		}
		return categoria;
	}
	

}
