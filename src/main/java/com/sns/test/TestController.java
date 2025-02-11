package com.sns.test;

import com.sns.post.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private PostMapper postMapper;

    @ResponseBody
    @GetMapping("/test1")
    public String test1() {
        return "<h3>Hello world</h3>";
    }

    @ResponseBody
    @GetMapping("/test2")
    public Map<String, Object> test2() {
        Map<String, Object> map = new HashMap<>();
        map.put("테스트1", 1);
        map.put("테스트2", 12);
        map.put("테스트3", 133);
        return map;
    }

    @GetMapping("/test3")
    public String test3() {
        return "test/test3";
    }

    @ResponseBody
    @GetMapping("/test4")
    public List<Map<String, Object>> test4() {
        return postMapper.selectPostListTest();
    }
}
