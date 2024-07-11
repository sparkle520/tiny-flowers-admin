package org.dromara.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.system.domain.SysOssCategory;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * OSS分类视图对象 sys_oss_category
 *
 * @author hexm
 * @date 2023-08-14
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysOssCategory.class)
public class SysOssCategoryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * oss分类id
     */
    @ExcelProperty(value = "oss分类id")
    private Long ossCategoryId;

    /**
     * 分类名称
     */
    @ExcelProperty(value = "分类名称")
    private String categoryName;

    /**
     * 父级分类id
     */
    @ExcelProperty(value = "父级分类id")
    private Long parentId;

    /**
     * 父级分类名称
     */
    @ExcelProperty(value = "父级分类名称")
    private String parentCategoryName;

    /**
     * 分类路径
     */
    @ExcelProperty(value = "分类路径")
    private String categoryPath;

    /**
     * 层级
     */
    @ExcelProperty(value = "层级")
    private Integer level;

    /**
     * 显示顺序
     */
    @ExcelProperty(value = "显示顺序")
    private Integer orderNum;

    /**
     * 用户类型
     */
    @ExcelProperty(value = "用户类型")
    private String userType;

    /**
     * 上传人
     */
    @ExcelProperty(value = "上传人")
    private Long createBy;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 文件数量
     */
    @ExcelProperty(value = "文件数量")
    private Integer fileCount;

}
