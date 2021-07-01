package br.com.zup.ecommerce.usuario;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid UsuarioRequest request) {
		Usuario usuario = request.converter();
				usuarioRepository.save(usuario);
		
	}
}
