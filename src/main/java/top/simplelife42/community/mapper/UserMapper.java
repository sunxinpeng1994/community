package top.simplelife42.community.mapper;

import org.apache.ibatis.annotations.*;
import top.simplelife42.community.model.User;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified, avatar_url, bio) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified},#{avatarUrl},#{bio})")
    void insert(User user);
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token")String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name=#{name}, token=#{token}, gmt_modified=#{gmtModified},avatar_url=#{avatarUrl},bio=#{bio} where id=#{id}")
    void update(User user);
}
