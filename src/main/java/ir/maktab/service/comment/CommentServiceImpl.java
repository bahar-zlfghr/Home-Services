package ir.maktab.service.comment;

import ir.maktab.data.domain.Comment;
import ir.maktab.data.repository.comment.CommentRepository;
import ir.maktab.dtos.CommentDto;
import ir.maktab.dtos.CustomerDto;
import ir.maktab.dtos.OrderDto;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.exceptions.NotFoundCommentException;
import ir.maktab.mappers.comment.CommentMapper;
import ir.maktab.mappers.customer.CustomerMapper;
import ir.maktab.mappers.order.OrderMapper;
import ir.maktab.mappers.specialist.SpecialistMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final CustomerMapper customerMapper;
    private final OrderMapper orderMapper;
    private final SpecialistMapper specialistMapper;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, CustomerMapper customerMapper, OrderMapper orderMapper, SpecialistMapper specialistMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.customerMapper = customerMapper;
        this.orderMapper = orderMapper;
        this.specialistMapper = specialistMapper;
    }

    @Override
    public void saveComment(CommentDto commentDto) {
        commentRepository.save(commentMapper.toComment(commentDto));
    }

    @Override
    public void updateScoreComment(Integer id, Integer score) {
        commentRepository.updateScoreComment(id, score);
    }

    @Override
    public void updateDescriptionComment(Integer id, String description) {
        commentRepository.updateDescriptionComment(id, description);
    }

    @Override
    public void deleteComment(CommentDto commentDto) {
        commentRepository.delete(commentMapper.toComment(commentDto));
    }

    @Override
    public Set<CommentDto> getCommentsByCustomer(CustomerDto customerDto) {
        return commentRepository.getCommentsByCustomer(customerMapper.toCustomer(customerDto))
                .stream().map(commentMapper::toCommentDto).collect(Collectors.toSet());
    }

    @Override
    public CommentDto getCommentByOrder(OrderDto orderDto) throws NotFoundCommentException {
        Optional<Comment> commentByOrder = commentRepository.getCommentByOrder(orderMapper.toOrder(orderDto));
        if (commentByOrder.isPresent()) {
            return commentMapper.toCommentDto(commentByOrder.get());
        }
        throw new NotFoundCommentException("comment.not.found");
    }

    @Override
    public Set<CommentDto> getCommentsBySpecialist(SpecialistDto specialistDto) {
        return commentRepository.getCommentsBySpecialist(specialistMapper.toSpecialist(specialistDto))
                .stream().map(commentMapper::toCommentDto).collect(Collectors.toSet());
    }
}
