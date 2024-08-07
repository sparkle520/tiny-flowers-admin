package org.dromara.article.domain.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import org.dromara.article.domain.Article;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.article.domain.Tag;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import java.util.Date;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 文章视图对象 article
 *
 * @author yixiacoco
 * @date 2024-07-13
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Article.class)
public class ArticleVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @ExcelProperty(value = "文章id")
    private Long id;

    /**
     * 文章标题
     */
    @ExcelProperty(value = "文章标题")
    private String title;

    /**
     * 文章简介
     */
    @ExcelProperty(value = "文章简介")
    private String intro;

    /**
     * 图片url路径
     */
    @ExcelProperty(value = "图片url路径")
    private String img;

    /**
     * 对象存储 oss 路径
     */
    @ExcelProperty(value = "对象存储 oss 路径")
    private String content;
    private String contentMd;

    /**
     * 作者名称(id)
     */
    @ExcelProperty(value = "作者名称(id)")
    private Long author;

    @ExcelProperty(value = "作者名称")
    private String authorName;

    @ExcelProperty(value = "文章分类名称")
    private String sortName;

    /**
     * 逻辑删除 默认 0 ( 1 删除 0 未删除 ) 数据字典 回收站保存7天
     */

    @TableLogic
    @ExcelProperty(value = "逻辑删除 默认 0 ( 1 删除 0 未删除 ) 数据字典 回收站保存7天")
    private Integer delFlag;

    /**
     * 文章审核状态 默认 0 ( 0 待审核 1 通过  2 未通过 ) 数据字典
     */
    @ExcelProperty(value = "文章审核状态 默认 0 ( 0 待审核 1 通过  2 未通过 ) 数据字典", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "article_status_type")
    private Integer status;

    private List<TagVo> tags;
    /**
     * 文章可见范围 默认 0 ( 0 公开 1 私人 ) 数据字典
     */
    @ExcelProperty(value = "文章可见范围 默认 0 ( 0 公开 1 私人 ) 数据字典", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "view_scope_type")
    private Integer viewScopeType;

    /**
     * 浏览量
     */
    @ExcelProperty(value = "浏览量")
    private Long viewCount;

    /**
     * 收藏数
     */
    @ExcelProperty(value = "收藏数")
    private Long collectCount;

    /**
     * 点赞数
     */
    @ExcelProperty(value = "点赞数")
    private Long thumbsUpCount;

    /**
     * 评论数
     */
    @ExcelProperty(value = "评论数")
    private Long commentCount;

    /**
     * 分类id
     */
    @ExcelProperty(value = "分类id")
    private Integer sortId;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

}
