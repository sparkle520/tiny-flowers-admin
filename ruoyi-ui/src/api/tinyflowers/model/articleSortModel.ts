import type { BaseEntity } from '@/api/model/resultModel';

/**
 * 【请填写功能名称】查询对象
 */
export interface ArticleSortQuery extends BaseEntity {
  /** 标签名称 */
  sortName?: string;
  /** 上一级父类id */
  parentId?: number;
}
/**
 * 【请填写功能名称】业务对象
 */
export interface ArticleSortForm {
  /** 主键 */
  id?: number;
  /** 标签名称 */
  sortName?: string;
  /** 上一级父类id */
  parentId?: number;
}
/**
 * 【请填写功能名称】视图对象
 */
export interface ArticleSortVo {
  /** 主键 */
  id?: number;
  /** 标签名称 */
  sortName?: string;
  /** 上一级父类id */
  parentId?: number;
}
