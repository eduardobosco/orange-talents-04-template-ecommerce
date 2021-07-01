package br.com.zup.ecommerce.categoria;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping
	private void cadastrar(@RequestBody @Valid CategoriaRequest request) {
		Categoria categoria = request.toModel(categoriaRepository);
		categoriaRepository.save(categoria);

	}

}
