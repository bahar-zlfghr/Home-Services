package ir.maktab.dtos;

/**
 * @author : Bahar Zolfaghari
 **/

public class CommentDto {
    private Integer id;
    private Integer score;
    private String description;
    private CustomerDto customerDto;
    private SpecialistDto specialistDto;
    private OrderDto orderDto;

    public Integer getId() {
        return id;
    }

    public CommentDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public CommentDto setScore(Integer score) {
        this.score = score;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CommentDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public CommentDto setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
        return this;
    }

    public SpecialistDto getSpecialistDto() {
        return specialistDto;
    }

    public CommentDto setSpecialistDto(SpecialistDto specialistDto) {
        this.specialistDto = specialistDto;
        return this;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public CommentDto setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
        return this;
    }
}
