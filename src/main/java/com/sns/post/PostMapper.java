package com.sns.post;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {

    public List<Map<String, Object>> selectPostListTest();
}
