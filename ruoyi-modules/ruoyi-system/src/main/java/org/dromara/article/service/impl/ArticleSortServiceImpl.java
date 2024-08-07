package org.dromara.article.service.impl;

import jakarta.annotation.Resource;
import org.dromara.common.core.utils.MapstructUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.dromara.article.domain.ArticleSort;
import org.dromara.article.domain.bo.ArticleSortBo;
import org.dromara.article.domain.query.ArticleSortQuery;
import org.dromara.article.domain.vo.ArticleSortVo;
import org.dromara.article.mapper.ArticleSortMapper;
import org.dromara.article.service.IArticleSortService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author yixiacoco
 * @date 2024-07-14
 */
@Service
public class ArticleSortServiceImpl  implements IArticleSortService {

    /**
     * 查询【请填写功能名称】
     *
     * @param id 主键
     * @return ArticleSortVo
     */
    @Resource
    private ArticleSortMapper baseMapper;




    /**
     * 查询【请填写功能名称】列表
     *
     * @param query 查询对象
     * @return ArticleSortVo
     */


    /**
     * 查询【请填写功能名称】列表
     *
     * @param query 查询对象
     * @return ArticleSortVo
     */
//    @Override
//    public List<ArticleSort> queryList(ArticleSort query) {
//        ArticleSort articleSort = new ArticleSort();
//
//        return baseMapper.queryList(articleSort);
//
//    }
    @Override
    public List<ArticleSort> queryList(ArticleSort query) {
        List<ArticleSort> articleSorts = baseMapper.queryList(query);
        return articleSorts.stream()
            .filter(o -> o.getParentId() == null || o.getParentId() == 0)
            .peek(o -> o.setChildren(getChildren(o, articleSorts)))
            .collect(Collectors.toList());
    }

    private List<ArticleSort> getChildren(ArticleSort o, List<ArticleSort> articleSorts) {
        return articleSorts.stream()
            .filter(a -> a.getParentId() != null && a.getParentId().equals(o.getId()))
            .collect(Collectors.toList());
    }

    /**
     * 根据新增业务对象插入【请填写功能名称】
     *
     * @param bo 【请填写功能名称】新增业务对象
     * @return Boolean
     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Boolean insertByBo(ArticleSortBo bo) {
//        ArticleSort add = MapstructUtils.convert(bo, ArticleSort.class);
//        return save(add);
//    }

    /**
     * 根据编辑业务对象修改【请填写功能名称】
     *
     * @param bo 【请填写功能名称】编辑业务对象
     * @return Boolean
     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Boolean updateByBo(ArticleSortBo bo) {
//        ArticleSort update = MapstructUtils.convert(bo, ArticleSort.class);
//        return updateById(update);
//    }
//
//    /**
//     * 校验并批量删除【请填写功能名称】信息
//     *
//     * @param ids 主键集合
//     * @return Boolean
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Boolean deleteWithValidByIds(Collection<Long> ids) {
//        return removeByIds(ids);
//    }
}
