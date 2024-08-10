import type { R, TableDataInfo } from '@/api/model/resultModel';
import type { ArticleForm, ArticleQuery, ArticleVo } from '@/api/system/model/articleModel';
import { request } from '@/utils/request';

// 查询文章列表
export function listArticle(query?: ArticleQuery) {
  return request.get<TableDataInfo<ArticleVo>>({
    url: '/article/list',
    params: query,
  });
}

// 查询文章详细
export function getArticle(id: number) {
  return request.get<R<ArticleVo>>({
    url: `/article/${id}`,
  });
}

// 新增文章
export function addArticle(data: ArticleForm) {
  return request.post<R<void>>({
    url: '/article',
    data,
  });
}

// 修改文章
export function updateArticle(data: ArticleForm) {
  return request.put<R<void>>({
    url: '/article',
    data,
  });
}

// 删除文章
export function delArticle(ids: number | Array<number>) {
  return request.delete<R<void>>({
    url: `/article/${ids}`,
  });
}
