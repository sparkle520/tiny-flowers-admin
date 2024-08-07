package org.dromara.article.service.impl;

import org.dromara.article.domain.query.TagQuery;
import org.dromara.article.domain.vo.ArticleVo;
import org.dromara.common.core.utils.MapstructUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.dromara.article.domain.Tag;
import org.dromara.article.domain.vo.TagVo;
import org.dromara.article.mapper.TagMapper;
import org.dromara.article.service.ITagService;

import java.util.Collection;
import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author yixiacoco
 * @date 2024-07-14
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    /**
     * 查询【请填写功能名称】
     *
     * @param id 主键
     * @return TagVo
     */
    @Override
    public TagVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param query 查询对象
     * @return TagVo
     */
    @Override
    public TableDataInfo<TagVo> queryPageList(TagVo query) {
        return PageQuery.of(() -> baseMapper.queryList(query));
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param query 查询对象
     * @return TagVo
     */
    @Override
    public List<TagVo> queryList(TagVo query) {
        return baseMapper.queryList(query);
    }

    /**
     * 根据新增业务对象插入【请填写功能名称】
     *
     * @param bo 【请填写功能名称】新增业务对象
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertByBo(TagVo bo) {
        Tag add = MapstructUtils.convert(bo, Tag.class);
        return save(add);
    }

    /**
     * 根据编辑业务对象修改【请填写功能名称】
     *
     * @param bo 【请填写功能名称】编辑业务对象
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByBo(TagVo bo) {
        Tag update = MapstructUtils.convert(bo, Tag.class);
        return updateById(update);

    }

    /**
     * 校验并批量删除【请填写功能名称】信息
     *
     * @param ids 主键集合
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        return removeByIds(ids);
    }

    @Override
    public List<TagVo> queryListByArticleId(Long articleId) {
        return List.of();
    }

    @Override
    public TableDataInfo<TagVo> queryPageTagVoList(TagQuery query) {
        PageQuery pageQuery = PageQuery.of(query.getPageNum(),query.getPageSize());
        return pageQuery.execute(()-> baseMapper.queryList(null));
    }
}
