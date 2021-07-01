package br.com.zup.ecommerce.autenticacao;

import br.com.zup.ecommerce.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private TokenService tokenManager;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm login){
        try{
        //Converto os dados que vem do request em um UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken dadosLogin = login.converter();
        //Verifico se o usuario corresponde com algum do banco e se a senha esta encriptada
        Authentication autenticate = auth.authenticate(dadosLogin);
        //gero o token
        String token = tokenManager.gerarToken(autenticate);
        return ResponseEntity.ok(new TokenDTO(token, "Bearer "));
        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }
}