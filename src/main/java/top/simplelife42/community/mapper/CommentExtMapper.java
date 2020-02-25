package top.simplelife42.community.mapper;

import top.simplelife42.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}