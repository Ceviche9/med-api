package med.voll.medapi.infra.exceptions;

public class AppError extends RuntimeException {
    public AppError(String message) {
        super(message);
    }
}
