package ir.maktab.mappers.comment;

import ir.maktab.data.domain.Comment;
import ir.maktab.dtos.CommentDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface CommentMapper {

    Comment toComment(CommentDto commentDto);
    CommentDto toCommentDto(Comment comment);
}
