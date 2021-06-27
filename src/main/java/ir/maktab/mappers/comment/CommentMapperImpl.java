package ir.maktab.mappers.comment;

import ir.maktab.data.domain.Comment;
import ir.maktab.dtos.CommentDto;
import ir.maktab.mappers.customer.CustomerMapper;
import ir.maktab.mappers.order.OrderMapper;
import ir.maktab.mappers.specialist.SpecialistMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class CommentMapperImpl implements CommentMapper {
    private final CustomerMapper customerMapper;
    private final SpecialistMapper specialistMapper;
    private final OrderMapper orderMapper;

    @Lazy
    public CommentMapperImpl(CustomerMapper customerMapper, SpecialistMapper specialistMapper, OrderMapper orderMapper) {
        this.customerMapper = customerMapper;
        this.specialistMapper = specialistMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public Comment toComment(CommentDto commentDto) {
        if (commentDto != null) {
            return new Comment()
                    .setId(commentDto.getId())
                    .setScore(commentDto.getScore())
                    .setDescription(commentDto.getDescription())
                    .setCustomer(customerMapper.toCustomer(commentDto.getCustomerDto()))
                    .setSpecialist(specialistMapper.toSpecialist(commentDto.getSpecialistDto()))
                    .setOrder(orderMapper.toOrder(commentDto.getOrderDto()));
        }
        return null;
    }

    @Override
    public CommentDto toCommentDto(Comment comment) {
        if (comment != null) {
            return new CommentDto()
                    .setId(comment.getId())
                    .setScore(comment.getScore())
                    .setDescription(comment.getDescription())
                    .setCustomerDto(customerMapper.toCustomerDto(comment.getCustomer()))
                    .setSpecialistDto(specialistMapper.toSpecialistDto(comment.getSpecialist()))
                    .setOrderDto(orderMapper.toOrderDto(comment.getOrder()));
        }
        return null;
    }
}
