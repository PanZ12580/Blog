package com.hzhang.controller;

import com.hzhang.pojo.Catalog;
import com.hzhang.pojo.Result;
import com.hzhang.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/10/8 22:07
 * @description：
 * @modified By：
 * @version: $
 */
@Slf4j
@RestController
@RequestMapping("/catalog")
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/findCatalogListByScope")
    public Result findCatalogListByScope(@RequestParam("scope") Byte scope) {
        List<Catalog> catalogList = catalogService.findCatalogListByScope(scope);
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(catalogList)
                .build();
    }
}
