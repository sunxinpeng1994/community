package top.simplelife42.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.simplelife42.community.enums.CommentTypeEnum;
import top.simplelife42.community.exception.CustomizeErrorCode;
import top.simplelife42.community.exception.CustomizeException;
import top.simplelife42.community.mapper.CommentMapper;
import top.simplelife42.community.mapper.QuestionExtMapper;
import top.simplelife42.community.mapper.QuestionMapper;
import top.simplelife42.community.model.Comment;
import top.simplelife42.community.model.Question;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/12/2020 1:41 PM
 **/
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_NOT_FOUND);
        }
        if(comment.getType() == null || !CommentTypeEnum.exist(comment.getType())){
            throw new CustomizeException((CustomizeErrorCode.TYPE_PARAM_WRONG));
        }
        if(comment.getType() == CommentTypeEnum.COMMENT.getType()) {//reply to comment
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            } else {
                commentMapper.insert(comment);
            }
        } else {//reply to question
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);


        }


    }
}
