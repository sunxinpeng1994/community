package top.simplelife42.community.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import top.simplelife42.community.model.Question;
import top.simplelife42.community.model.QuestionExample;

import java.util.List;

public interface QuestionExtMapper {
   int incView(Question record);
}