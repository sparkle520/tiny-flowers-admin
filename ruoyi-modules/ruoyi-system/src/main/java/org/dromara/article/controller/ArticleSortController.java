package org.dromara.article.controller;


import java.util.List;

import cn.dev33.satoken.annotation.SaIgnore;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import org.dromara.article.domain.ArticleSort;
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
import org.dromara.article.domain.bo.ArticleSortBo;
import org.dromara.article.domain.query.ArticleSortQuery;
import org.dromara.article.domain.vo.ArticleSortVo;
import org.dromara.article.service.IArticleSortService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 【请填写功能名称】
 *
 * @author yixiacoco
 * @date 2024-07-14
 */
@Validated
@RestController
@RequestMapping("/articleSort")
public class ArticleSortController extends BaseController {

    @Resource
    private IArticleSortService articleSortService;

    /**
     * 查询【请填写功能名称】列表
     */
//    @SaCheckPermission("system:sort:list")
    @GetMapping("/list")
    @SaIgnore
    public R<List<ArticleSort>> list(ArticleSort query) {
        return R.ok(articleSortService.queryList(query));
    }

    /**
     * 导出【请填写功能名称】列表
     */
//    @SaCheckPermission("system:sort:export")
//    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(ArticleSortQuery query, HttpServletResponse response) {
//        List<ArticleSortVo> list = articleSortService.queryList(query);
//        ExcelUtil.exportExcel(list, "【请填写功能名称】", ArticleSortVo.class, response);
//    }
//
//    /**
//     * 获取【请填写功能名称】详细信息
//     *
//     * @param id 主键
//     */
//    @SaCheckPermission(value = {"system:sort:query", "system:sort:edit"}, mode = SaMode.OR)
//    @GetMapping("/{id}")
//    public R<ArticleSortVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long id) {
//        return R.ok(articleSortService.queryById(id));
//    }
//
//    /**
//     * 新增【请填写功能名称】
//     */
//    @SaCheckPermission("system:sort:add")
//    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
//    @RepeatSubmit()
//    @PostMapping()
//    public R<Void> add(@Validated(AddGroup.class) @RequestBody ArticleSortBo bo) {
//        return toAjax(articleSortService.insertByBo(bo));
//    }
//
//    /**
//     * 修改【请填写功能名称】
//     */
//    @SaCheckPermission("system:sort:edit")
//    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
//    @RepeatSubmit()
//    @PutMapping()
//    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ArticleSortBo bo) {
//        return toAjax(articleSortService.updateByBo(bo));
//    }
//
//    /**
//     * 删除【请填写功能名称】
//     *
//     * @param ids 主键串
//     */
//    @SaCheckPermission("system:sort:remove")
//    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{ids}")
//    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
//        return toAjax(articleSortService.deleteWithValidByIds(List.of(ids)));
//    }
}
