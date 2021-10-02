package ir.maktab.service.comment;

import ir.maktab.dtos.CommentDto;
import ir.maktab.dtos.CustomerDto;
import ir.maktab.dtos.OrderDto;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.exceptions.NotFoundCommentException;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface CommentService {

    void saveComment(CommentDto commentDto);
    void updateScoreComment(Integer id, Integer score);
    void updateDescriptionComment(Integer id, String description);
    void deleteComment(CommentDto commentDto);
    Set<CommentDto> getCommentsByCustomer(CustomerDto customerDto);
    CommentDto getCommentByOrder(OrderDto orderDto) throws NotFoundCommentException;
    Set<CommentDto> getCommentsBySpecialist(SpecialistDto specialistDto);
}
