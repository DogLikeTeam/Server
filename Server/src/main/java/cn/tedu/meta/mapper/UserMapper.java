package cn.tedu.meta.mapper;



import cn.tedu.meta.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {
    public List<User> findUserList();
}
