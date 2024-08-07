package org.dromara.article.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.dromara.article.domain.ArticleSort;
import org.dromara.article.domain.bo.ArticleSortBo;
import org.dromara.article.domain.query.ArticleSortQuery;
import org.dromara.article.domain.vo.ArticleSortVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author yixiacoco
 * @date 2024-07-14
 */

public interface ArticleSortMapper extends BaseMapperPlus<ArticleSort, ArticleSort> {

    /**
     * 查询列表
     *
     * @param query 查询对象
     * @return {@link ArticleSortVo}
     */
    @InterceptorIgnore(tenantLine = "true")
    List<ArticleSort> queryList(ArticleSort query);
}
