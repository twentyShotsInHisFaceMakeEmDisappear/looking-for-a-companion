package by.grsu.lookingforacompanion.exception.security;

public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException(String s) {
        super(s);
    }
}
