package org.dromara.article.controller;

import java.util.List;

import cn.dev33.satoken.annotation.SaIgnore;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import org.dromara.article.domain.ArticleSort;
import org.dromara.article.domain.vo.ArticlesVo;
import org.dromara.article.service.IArticleSortService;
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
import org.dromara.article.domain.bo.ArticleBo;
import org.dromara.article.domain.query.ArticleQuery;
import org.dromara.article.domain.vo.ArticleVo;
import org.dromara.article.service.IArticleService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 文章
 *
 * @author yixiacoco
 * @date 2024-07-13
 */
@Validated
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired
    private IArticleService articleService;

    /**
     * 查询文章列表
     */
//    @SaCheckPermission("system:article:list")
    @GetMapping("/list")
    @SaIgnore
    public TableDataInfo<ArticleVo> list(ArticleQuery query) {
        return articleService.queryPageList(query);
    }
    /**
     * 查询文章列表
     */
//    @SaCheckPermission("system:article:list")
    @GetMapping("/search")
    @SaIgnore
    public TableDataInfo<ArticleVo> list( String query) {
        return articleService.queryPageListByString(query);
    }
    /**
     * 获取最新文章列表
     */
    @GetMapping("/new")
    @SaIgnore
    public TableDataInfo<ArticleVo> newList() {
        return articleService.getNewArticleList();
    }
    /**
     * 获取总文章数量
     */
    @GetMapping("/count")
    @SaIgnore
    public R<Integer> list() {
        return R.ok(articleService.getArticleCount());
    }
    /**
     * 获取最后更新时间
     */
    @GetMapping("/lastUpdate")
    @SaIgnore
    public R<String> lastUpdate() {
        return R.ok(articleService.getArticleLastUpdate());
    }


   /**
     * 导出文章列表
     */
    @SaCheckPermission("system:article:export")
    @Log(title = "文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ArticleQuery query, HttpServletResponse response) {
        List<ArticleVo> list = articleService.queryList(query);
        ExcelUtil.exportExcel(list, "文章", ArticleVo.class, response);
    }

    /**
     * 获取文章详细信息
     *
     * @param id 主键
     */
//    @SaCheckPermission(value = {"system:article:query", "system:article:edit"}, mode = SaMode.OR)
    @GetMapping("/{id}")
    public R<ArticleVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long id) {
        return R.ok(articleService.queryById(id));
    }
    /**
     * @Author sparkle520
     * @Description 返回上一篇 当前 下一篇  ArticleVo对象
     * @Param
     * @return
     **/
    @GetMapping("info/{id}")
    @SaIgnore
    public R<ArticlesVo> getInfoPCN(@NotNull(message = "主键不能为空") @PathVariable Long id) {
        return R.ok(articleService.queryArticlesById(id));
    }

    /**
     * 新增文章
     */
    @SaCheckPermission("system:article:add")
    @Log(title = "文章", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ArticleBo bo) {
        return toAjax(articleService.insertByBo(bo));
    }

    /**
     * 修改文章
     */
    @SaCheckPermission("system:article:edit")
    @Log(title = "文章", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@RequestBody ArticleBo bo) {
        return toAjax(articleService.updateByBo(bo));
    }

    /**
     * 删除文章
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:article:remove")
    @Log(title = "文章", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(articleService.deleteWithValidByIds(List.of(ids)));
    }
}
