package med.voll.medapi.domains.user;

public record CreateUsersResponse(
        Long id,
        String name,
        String username
) {
    public CreateUsersResponse(User data) {
        this(data.getId(), data.getUsername(), data.getName());
    }
}
