package med.voll.medapi.domains.user;

public record UpdatedUsersResponse(
        Long id,
        String username
) {
    public UpdatedUsersResponse(User data) {
        this(data.getId(), data.getUsername());
    }
}
