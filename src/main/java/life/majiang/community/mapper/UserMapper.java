package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified});")
    void insert(User user);

    @Select("select * from user where token = #{token};")
    @ResultType(User.class)
    User findByToken(@Param("token") String token);
}
