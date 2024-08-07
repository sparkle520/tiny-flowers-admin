package org.dromara.article.service;

import org.dromara.article.domain.ArticleSort;
import org.dromara.article.domain.bo.ArticleSortBo;
import org.dromara.article.domain.query.ArticleSortQuery;
import org.dromara.article.domain.vo.ArticleSortVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author yixiacoco
 * @date 2024-07-14
 */
public interface IArticleSortService {

    /**
     * 查询【请填写功能名称】
     *
     * @param id 主键
     * @return ArticleSortVo
     */



    /**
     * 查询【请填写功能名称】列表
     *
     * @param query 查询对象
     * @return ArticleSortVo
     */
    List<ArticleSort> queryList(ArticleSort query);

    /**
     * 新增【请填写功能名称】
     *
     * @param bo 【请填写功能名称】新增业务对象
     * @return Boolean
     */
//    Boolean insertByBo(ArticleSortBo bo);
//
//    /**
//     * 修改【请填写功能名称】
//     *
//     * @param bo 【请填写功能名称】编辑业务对象
//     * @return Boolean
//     */
//    Boolean updateByBo(ArticleSortBo bo);
//
//    /**
//     * 校验并批量删除【请填写功能名称】信息
//     *
//     * @param ids 主键集合
//     * @return Boolean
//     */
//    Boolean deleteWithValidByIds(Collection<Long> ids);
}
