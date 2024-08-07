package org.dromara.article.controller;

import java.util.List;

import cn.dev33.satoken.annotation.SaIgnore;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import org.dromara.article.domain.query.TagQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.article.domain.vo.TagVo;
import org.dromara.article.service.ITagService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 【请填写功能名称】
 *
 * @author yixiacoco
 * @date 2024-07-14
 */
@Validated
@RestController
@RequestMapping("/tag")
public class TagController extends BaseController {

    @Autowired
    private ITagService tagService;

    /**
     * 查询【请填写功能名称】列表
     */
//    @SaCheckPermission("system:tag:list")
//    @GetMapping("/list")
//    public TableDataInfo<TagVo> list(TagVo query) {
//        return tagService.queryPageList(query);
//    }
    @GetMapping("/list")
    @SaIgnore
    public TableDataInfo<TagVo> list( TagQuery query) {
        return tagService.queryPageTagVoList(query);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @SaCheckPermission("system:tag:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TagVo query, HttpServletResponse response) {
        List<TagVo> list = tagService.queryList(query);
        ExcelUtil.exportExcel(list, "【请填写功能名称】", TagVo.class, response);
    }
    @SaCheckPermission("system:tag:list")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/article/{articleId}")
    public List<TagVo> listTag(@NotNull(message="文章主键不能为空") @PathVariable Long articleId ) {
        return  tagService.queryListByArticleId(articleId);
    }

    /**
     * 获取【请填写功能名称】详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission(value = {"system:tag:query", "system:tag:edit"}, mode = SaMode.OR)
    @GetMapping("/{id}")
    public R<TagVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long id) {
        return R.ok(tagService.queryById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @SaCheckPermission("system:tag:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TagVo bo) {
        return toAjax(tagService.insertByBo(bo));
    }

    /**
     * 修改【请填写功能名称】
     */
    @SaCheckPermission("system:tag:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TagVo bo) {
        return toAjax(tagService.updateByBo(bo));
    }

    /**
     * 删除【请填写功能名称】
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:tag:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(tagService.deleteWithValidByIds(List.of(ids)));
    }
}
