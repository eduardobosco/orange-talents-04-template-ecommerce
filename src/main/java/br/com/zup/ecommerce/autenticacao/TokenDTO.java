package br.com.zup.ecommerce.autenticacao;

public class TokenDTO {

    private String token;
    private String bearer;

    public TokenDTO(String token, String bearer) {
        this.token = token;
        this.bearer = bearer;
    }

    public String getToken() {
        return token;
    }

    public String getBearer() {
        return bearer;
    }
}