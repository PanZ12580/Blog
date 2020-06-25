package com.hzhang.controller;

import com.hzhang.pojo.Archives;
import com.hzhang.pojo.Result;
import com.hzhang.service.BlogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/25 11:10
 * @description：归档控制器类
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/archives")
public class ArchivesController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/findArchivesBlogList")
    @ApiOperation("获取归档页面博客时间分组映射")
    public Result findArchivesBlogList() {
        List<Archives> archivesList = blogService.findArchivesBlogList();
        Integer count = 0;
        for(Archives archive: archivesList) {
            count += archive.getBlogList().size();
        }
        Map<String, Object> realMap = new HashMap<>(2);
        realMap.put("list", archivesList);
        realMap.put("total", count);
        return Result.builder()
                .statusCode(HttpStatus.OK.value())
                .flag(true)
                .data(realMap)
                .build();
    }
}
