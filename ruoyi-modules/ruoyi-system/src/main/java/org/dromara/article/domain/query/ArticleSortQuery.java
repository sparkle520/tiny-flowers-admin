package org.dromara.article.domain.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import org.dromara.common.mybatis.core.domain.BasePageQuery;

/**
 * 【请填写功能名称】查询对象 article_sort
 *
 * @author yixiacoco
 * @date 2024-07-14
 */
@Data
public class ArticleSortQuery  {

    /**
     * 标签名称
     */
    private String sortName;

    /**
     * 上一级父类id
     */
    private Long parentId;

}
