import type { BaseEntity } from '@/api/model/resultModel';

/**
 * 文章查询对象
 */
export interface ArticleQuery extends BaseEntity {
  /** 文章标题 */
  title?: string;
  /** 文章简介 */
  intro?: string;
  /** 图片url路径 */
  img?: string;
  /** 对象存储 oss 路径 */
  content?: string;
  /** 作者名称(id) */
  author?: number;
  /** 逻辑删除 默认 0 ( 1 删除 0 未删除 ) 数据字典 回收站保存7天 */
  delFlag?: number;
  /** 文章审核状态 默认 0 ( 0 待审核 1 通过  2 未通过 ) 数据字典 */
  status?: number;
  /** 文章可见范围 默认 0 ( 0 公开 1 私人 ) 数据字典 */
  viewScopeType?: number;
  /** 浏览量 */
  viewCount?: number;
  /** 收藏数 */
  collectCount?: number;
  /** 点赞数 */
  thumbsUpCount?: number;
  /** 评论数 */
  commentCount?: number;
  /** 分类id */
  sortId?: number;
}
/**
 * 文章业务对象
 */
export interface ArticleForm {
  /** 文章id */
  id?: number;
  /** 文章标题 */
  title?: string;
  /** 文章简介 */
  intro?: string;
  /** 图片url路径 */
  img?: string;
  /** 对象存储 oss 路径 */
  content?: string;
  /** 文章内容 */
  contentMd?: string;
  /** 文章标签 */
  tags?: any;
  /** 作者名称(id) */
  author?: number;
  /** 逻辑删除 默认 0 ( 1 删除 0 未删除 ) 数据字典 回收站保存7天 */
  delFlag?: number;
  /** 文章审核状态 默认 0 ( 0 待审核 1 通过  2 未通过 ) 数据字典 */
  status?: number;
  /** 文章可见范围 默认 0 ( 0 公开 1 私人 ) 数据字典 */
  viewScopeType?: number;
  /** 浏览量 */
  viewCount?: number;
  /** 收藏数 */
  collectCount?: number;
  /** 点赞数 */
  thumbsUpCount?: number;
  /** 评论数 */
  commentCount?: number;
  /** 分类id */
  sortId?: number;
}
/**
 * 文章视图对象
 */
export interface ArticleVo {
  /** 文章id */
  id?: number;
  /** 文章标题 */
  title?: string;
  /** 文章简介 */
  intro?: string;
  /** 图片url路径 */
  img?: string;
  /** 对象存储 oss 路径 */
  content?: string;
  /** 作者名称(id) */
  author?: number;
  /** 作者名称  */
  authorName?: string;
  /** 分类名称 */
  sortName?: string;
  /** 逻辑删除 默认 0 ( 1 删除 0 未删除 ) 数据字典 回收站保存7天 */
  delFlag?: number;
  /** 标签列表 */
  tags?: any;
  /** 文章审核状态 默认 0 ( 0 待审核 1 通过  2 未通过 ) 数据字典 */
  status?: number;
  /** 文章可见范围 默认 0 ( 0 公开 1 私人 ) 数据字典 */
  viewScopeType?: number;
  /** 浏览量 */
  viewCount?: number;
  /** 收藏数 */
  collectCount?: number;
  /** 点赞数 */
  thumbsUpCount?: number;
  /** 评论数 */
  commentCount?: number;
  /** 分类id */
  sortId?: number;
  /** 创建时间 */
  createTime?: any;
  /** 更新时间 */
  updateTime?: any;
}
