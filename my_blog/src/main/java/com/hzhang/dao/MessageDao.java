package com.hzhang.dao;

import com.hzhang.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/26 11:35
 * @description：留言Dao层
 * @modified By：
 * @version: $
 */
@Mapper
@Repository
public interface MessageDao {

    /**
     * 查询留言列表
     * @return
     */
    List<Message> findMessageList();

    /**
     * 保存留言信息
     * @param message
     * @return
     */
    int saveMessage(Message message);

}
