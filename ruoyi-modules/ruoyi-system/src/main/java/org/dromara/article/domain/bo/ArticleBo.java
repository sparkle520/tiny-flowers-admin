package org.dromara.article.domain.bo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import org.dromara.article.domain.Article;
import org.dromara.article.domain.vo.TagVo;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.hibernate.validator.constraints.Length;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 文章业务对象 article
 *
 * @author yixiacoco
 * @date 2024-07-13
 */
@Data
@AutoMapper(target = Article.class, reverseConvertGenerate = false)
public class ArticleBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    private Long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章简介
     */
    private String intro;
    /**
     * 图片url路径
     */
    private String img;
    /**
     * 对象存储 oss 路径
     */
    private String content;
    /**
     * 作者名称(id)
     */
    private Long author;

    private String contentMd;
    /**
     * 逻辑删除 默认 0 ( 1 删除 0 未删除 ) 数据字典 回收站保存7天
     */
    @TableLogic
    private Integer delFlag;
    private List<TagVo> tags;
    /**
     * 文章审核状态 默认 0 ( 0 待审核 1 通过  2 未通过 ) 数据字典
     */
    private Integer status;
    /**
     * 文章可见范围 默认 0 ( 0 公开 1 私人 ) 数据字典
     */
    private Integer viewScopeType;
    /**
     * 浏览量
     */
    private Long viewCount;
    /**
     * 收藏数
     */
    private Long collectCount;
    /**
     * 点赞数
     */
    private Long thumbsUpCount;
    /**
     * 评论数
     */
    private Long commentCount;
    /**
     * 分类id
     */
    private Integer sortId;
}
