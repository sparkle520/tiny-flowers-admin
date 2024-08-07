package org.dromara.article.domain.bo;

import org.dromara.article.domain.ArticleSort;
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

/**
 * 【请填写功能名称】业务对象 article_sort
 *
 * @author yixiacoco
 * @date 2024-07-14
 */
@Data
@AutoMapper(target = ArticleSort.class, reverseConvertGenerate = false)
public class ArticleSortBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */


    @NotNull(message = "主键不能为空", groups = {EditGroup.class})
    private Long id;
    /**
     * 标签名称
     */
    @NotBlank(message = "标签名称不能为空", groups = {AddGroup.class, EditGroup.class})
    @Length(max = 50, message = "标签名称不能大于{max}个字符", groups = {AddGroup.class, EditGroup.class})
    private String sortName;
    /**
     * 上一级父类id
     */
    private Long parentId;
}
