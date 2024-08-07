package org.dromara.article.service;

import org.dromara.article.domain.Tag;
import org.dromara.article.domain.query.TagQuery;
import org.dromara.article.domain.vo.TagVo;
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
public interface ITagService extends IService<Tag> {

    /**
     * 查询【请填写功能名称】
     *
     * @param id 主键
     * @return TagVo
     */
    TagVo queryById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param query 查询对象
     * @return TagVo
     */
    TableDataInfo<TagVo> queryPageList(TagVo query);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param query 查询对象
     * @return TagVo
     */
    List<TagVo> queryList(TagVo query);

    /**
     * 新增【请填写功能名称】
     *
     * @param bo 【请填写功能名称】新增业务对象
     * @return Boolean
     */
    Boolean insertByBo(TagVo bo);

    /**
     * 修改【请填写功能名称】
     *
     * @param bo 【请填写功能名称】编辑业务对象
     * @return Boolean
     */
    Boolean updateByBo(TagVo bo);

    /**
     * 校验并批量删除【请填写功能名称】信息
     *
     * @param ids 主键集合
     * @return Boolean
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);

    List<TagVo> queryListByArticleId(Long articleId);

    TableDataInfo<TagVo> queryPageTagVoList(TagQuery query);
}
