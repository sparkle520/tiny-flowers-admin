package org.dromara.article.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.article.domain.Article;
import org.dromara.article.domain.ArticleSort;
import org.dromara.article.domain.bo.ArticleBo;
import org.dromara.article.domain.query.ArticleQuery;
import org.dromara.article.domain.vo.ArticleShort;
import org.dromara.article.domain.vo.ArticleVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 文章Mapper接口
 *
 * @author yixiacoco
 * @date 2024-07-13
 */
public interface ArticleMapper extends BaseMapperPlus<Article, ArticleVo> {

    /**
     * 查询文章列表
     *
     * @param query 查询对象
     * @return {@link ArticleVo}
     */
    List<ArticleVo> queryList(ArticleQuery query);    /**

     * 查询文章列表
     *
     * @param query 查询对象
     * @return {@link ArticleVo}
     */
    List<ArticleVo> queryPageListByString(String query);

    ArticleShort queryArticlePre(@Param("id") Long id);
    ArticleVo queryArticleCur(@Param("id") Long id);
    ArticleShort queryArticleNext(@Param("id") Long id);
}
