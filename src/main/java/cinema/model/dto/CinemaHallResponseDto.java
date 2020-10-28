package cinema.model.dto;

public class CinemaHallResponseDto {
    private Long id;
    private int capacity;
    private String description;

    public CinemaHallResponseDto(Long id, int capacity, String description) {
        this.id = id;
        this.capacity = capacity;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
