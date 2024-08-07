package org.dromara.article.domain.vo;


import org.dromara.article.domain.ArticleSort;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import java.util.Date;
import java.io.Serial;
import java.io.Serializable;

/**
 * 【请填写功能名称】视图对象 article_sort
 *
 * @author yixiacoco
 * @date 2024-07-14
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ArticleSort.class)
public class ArticleSortVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 标签名称
     */
    @ExcelProperty(value = "标签名称")
    private String sortName;

    /**
     * 上一级父类id
     */
    @ExcelProperty(value = "上一级父类id")
    private Long parentId;

}

