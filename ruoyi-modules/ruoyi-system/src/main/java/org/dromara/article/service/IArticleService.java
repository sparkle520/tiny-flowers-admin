package org.dromara.article.service;

import org.dromara.article.domain.Article;
import org.dromara.article.domain.bo.ArticleBo;
import org.dromara.article.domain.query.ArticleQuery;
import org.dromara.article.domain.vo.ArticleVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dromara.article.domain.vo.ArticlesVo;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 文章Service接口
 *
 * @author yixiacoco
 * @date 2024-07-13
 */
public interface IArticleService extends IService<Article> {

    /**
     * 查询文章
     *
     * @param id 主键
     * @return ArticleVo
     */
    ArticleVo queryById(Long id);

    /**
     * 查询文章列表
     *
     * @param query 查询对象
     * @return ArticleVo
     */
    TableDataInfo<ArticleVo> queryPageList(ArticleQuery query);

    /**
     * 查询文章列表
     *
     * @param query 查询对象
     * @return ArticleVo
     */
    List<ArticleVo> queryList(ArticleQuery query);

    /**
     * 新增文章
     *
     * @param bo 文章新增业务对象
     * @return Boolean
     */
    Boolean insertByBo(ArticleBo bo);

    /**
     * 修改文章
     *
     * @param bo 文章编辑业务对象
     * @return Boolean
     */
    Boolean updateByBo(ArticleBo bo);

    /**
     * 校验并批量删除文章信息
     *
     * @param ids 主键集合
     * @return Boolean
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);

    ArticlesVo queryArticlesById(Long id);

    TableDataInfo<ArticleVo> queryPageListByString(String query);

    TableDataInfo<ArticleVo> getNewArticleList();

    Integer getArticleCount();

    String getArticleLastUpdate();
}
