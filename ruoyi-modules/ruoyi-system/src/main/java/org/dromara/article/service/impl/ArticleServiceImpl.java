package org.dromara.article.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.dromara.article.domain.Tag;
import org.dromara.article.domain.vo.ArticlesVo;
import org.dromara.article.domain.vo.TagVo;
import org.dromara.article.mapper.ArticleSortMapper;
import org.dromara.article.mapper.TagMapper;
import org.dromara.common.core.utils.MapstructUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.dromara.article.domain.Article;
import org.dromara.article.domain.bo.ArticleBo;
import org.dromara.article.domain.query.ArticleQuery;
import org.dromara.article.domain.vo.ArticleVo;
import org.dromara.article.mapper.ArticleMapper;
import org.dromara.article.service.IArticleService;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

/**
 * 文章Service业务层处理
 *
 * @author yixiacoco
 * @date 2024-07-13
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    /**
     * 查询文章
     *
     * @param id 主键
     * @return ArticleVo
     */
    @Resource
    TagMapper tagMapper;

    @Resource
    ArticleSortMapper articleSortMapper;
    @Resource
    ArticleMapper articleMapper;

    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public ArticleVo queryById(Long id) {
        ArticleVo articleVo = baseMapper.selectVoById(id);
        articleVo.setTags(tagMapper.selectTagListByArticleId(id));
        articleVo.setSortName(articleSortMapper.selectById(articleVo.getSortId()).getSortName());
        articleVo.setAuthorName(sysUserMapper.selectVoById(articleVo.getAuthor()).getNickName());
        return articleVo;
    }

    /**
     * 查询文章列表
     *
     * @param query 查询对象
     * @return ArticleVo
     */
    @Override
    public TableDataInfo<ArticleVo> queryPageList(ArticleQuery query) {
        TableDataInfo<ArticleVo> info = PageQuery.of(() -> baseMapper.queryList(query));
        info.getRows().forEach(articleVo ->
        {
            articleVo.setTags(tagMapper.selectTagListByArticleId(articleVo.getId()));
        });
        return info;
    }

    /**
     * 查询文章列表
     *
     * @param query 查询对象
     * @return ArticleVo
     */
    @Override
    public List<ArticleVo> queryList(ArticleQuery query) {
        return baseMapper.queryList(query);
    }

    /**
     * 根据新增业务对象插入文章
     *
     * @param bo 文章新增业务对象
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertByBo(ArticleBo bo) {
        try {
            Article add = MapstructUtils.convert(bo, Article.class);
            add.setAuthor(LoginHelper.getUserId());
            add.setDelFlag(0);
            add.setStatus(1);
            save(add);
            bo.setId(add.getId());
            insertArticleTags(bo);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("新增文章失败:" + e.getMessage());
        }

    }

    private void insertArticleTags(ArticleBo bo) {
        bo.getTags().forEach(e -> {
            QueryWrapper<Tag> wrapper = new QueryWrapper<Tag>();
            wrapper.eq("name", e.getName());
            if (tagMapper.selectList(wrapper) == null || tagMapper.selectList(wrapper).isEmpty()) {
                Tag tag = new Tag();
                tag.setName(e.getName());
                tagMapper.insert(tag);
            }
            tagMapper.insertTagArticle(e.getName(), bo.getId());
        });
    }

    /**
     * 根据编辑业务对象修改文章
     *
     * @param bo 文章编辑业务对象
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByBo(ArticleBo bo) {
        try {
            Article update = MapstructUtils.convert(bo, Article.class);
            tagMapper.deleteTagsByArticleId(bo.getId());
            insertArticleTags(bo);
            return updateById(update);

        } catch (Exception e) {
            throw new RuntimeException("修改文章失败:" + e.getMessage());
        }
    }

    /**
     * 校验并批量删除文章信息
     *
     * @param ids 主键集合
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        try {
               return  removeByIds(ids);
        } catch (Exception e) {
            throw new RuntimeException("删除文章失败:" + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticlesVo queryArticlesById(Long id) {
        try {
            ArticlesVo articlesVo = new ArticlesVo();
            articlesVo.setPre(articleMapper.queryArticlePre(id));
            articlesVo.setCur(articleMapper.queryArticleCur(id));
            articlesVo.setNext(articleMapper.queryArticleNext(id));
            if (articlesVo.getCur() != null) {
                articlesVo.getCur().setTags(tagMapper.selectTagListByArticleId(id));
                articlesVo.getCur().setSortName(articleSortMapper.selectById(articlesVo.getCur().getSortId()).getSortName());

            }
            return articlesVo;
        } catch (Exception e) {
            throw new RuntimeException("查询文章失败:" + e.getMessage());
        }
    }

    @Override
    public TableDataInfo<ArticleVo> queryPageListByString(String query) {
        TableDataInfo<ArticleVo> info = PageQuery.of(() -> baseMapper.queryPageListByString(query));
        info.getRows().forEach(articleVo ->
        {
            articleVo.setTags(tagMapper.selectTagListByArticleId(articleVo.getId()));
        });
        return info;
    }

    @Override
    public TableDataInfo<ArticleVo> getNewArticleList() {
        PageQuery pageQuery = PageQuery.of(1,4);
        pageQuery.setPageNum(1);
        pageQuery.setPageSize(4);
        TableDataInfo<ArticleVo> info = pageQuery.execute(()-> baseMapper.queryList(null));
        info.getRows().forEach(articleVo ->
        {
            articleVo.setTags(tagMapper.selectTagListByArticleId(articleVo.getId()));
        });
        return info;
    }

    @Override
    public Integer getArticleCount() {
        return baseMapper.queryList(null).size();
    }

    @Override
    public String getArticleLastUpdate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(baseMapper.queryList(null).get(0).getCreateTime());
    }


}
