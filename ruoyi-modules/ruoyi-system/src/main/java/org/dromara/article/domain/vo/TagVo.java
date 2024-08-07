package org.dromara.article.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import org.dromara.article.domain.Tag;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.mybatis.core.domain.BasePageQuery;

import java.util.Date;
import java.io.Serial;
import java.io.Serializable;

/**
 * 【请填写功能名称】视图对象 tag
 *
 * @author yixiacoco
 * @date 2024-07-14
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Tag.class)
public class TagVo implements Serializable {

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
    private String name;

    /**
     * 标签所含文章个数
     */
    @TableField(exist = false)
    private Integer articleCount;

}
