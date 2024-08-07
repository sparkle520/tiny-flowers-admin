package org.dromara.article.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.article.domain.vo.TagVo;
import org.dromara.article.domain.Tag;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author yixiacoco
 * @date 2024-07-14
 */
public interface TagMapper extends BaseMapperPlus<Tag, TagVo> {

    /**
     * 查询列表
     *
     * @param query 查询对象
     * @return {@link TagVo}
     */
    List<TagVo>  selectTagListByArticleId(Long query);
    List<TagVo> queryList(TagVo query);
    /**
     * @Author sparkle520
     * @Description 删除关联表
     * @Param
     * @return
     **/
    int deleteTagsByArticleId(Long articleId);

    void insertTagArticle(@Param("tag") String tag,@Param("id") Long id);
}
