import type { R, TableDataInfo } from '@/api/model/resultModel';
import type { ArticleSortForm, ArticleSortQuery, ArticleSortVo } from '@/api/system/model/sortModel';
import { request } from '@/utils/request';

// 查询【请填写功能名称】列表
export function listSort(query?: ArticleSortQuery) {
  return request.get<TableDataInfo<ArticleSortVo>>({
    url: '/articleSort/list',
    params: query,
  });
}

// 查询【请填写功能名称】详细
export function getSort(id: number) {
  return request.get<R<ArticleSortVo>>({
    url: `/articleSort/${id}`,
  });
}

// 新增【请填写功能名称】
export function addSort(data: ArticleSortForm) {
  return request.post<R<void>>({
    url: '/articleSort',
    data,
  });
}

// 修改【请填写功能名称】
export function updateSort(data: ArticleSortForm) {
  return request.put<R<void>>({
    url: '/articleSort',
    data,
  });
}

// 删除【请填写功能名称】
export function delSort(ids: number | Array<number>) {
  return request.delete<R<void>>({
    url: `/articleSort/${ids}`,
  });
}
