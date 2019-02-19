package com.yf.poi;

import lombok.Data;

import java.util.List;

/**
 * 文档实体类
 * @author ouyangyufeng
 * @date 2018/9/25
 */

@Data
public class ExcelData {

    private static final Long serialVersionUID = 4444017239100620999L;

    /**
     * 表头
     */
    private List<String> titles;

    /**
     * 数据
     */
    private List<List<Object>> rows;

    /**
     * 页签名称
     */
    private String name;

}