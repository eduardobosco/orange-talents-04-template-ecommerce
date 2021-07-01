package br.com.zup.ecommerce.security;

import java.util.Date;

import br.com.zup.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	 @Value("${zup.jwt.expiration}")
	    private String expiration;

	    @Value("${zup.jwt.secret}")
	    private String secret;

	    public String gerarToken(Authentication authenticate) {
	        //Buscamos o usuario autenticado
	        Usuario logado = (Usuario) authenticate.getPrincipal();
	        Date hoje = new Date();
	        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
	        return Jwts.builder()
	                .setIssuer("API DO Mercado Livre Orange Talents")
	                //passamos a id do usuario logado como string
	                .setSubject(logado.getId().toString())
	                //passamos a data
	                .setIssuedAt(hoje)
	                //a data de expiração
	                .setExpiration(dataExpiracao)
	                //algoritimo de encriptação
	                .signWith(SignatureAlgorithm.HS256,secret).compact();
	    }
	    public boolean isTokenValido(String token) {
	        try {
	            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public Long getIdUsuario(String token) {
	        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
	        return Long.parseLong(claims.getSubject());
	    }
	
}
