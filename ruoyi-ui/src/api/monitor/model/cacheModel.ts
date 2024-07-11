/**
 * 缓存信息
 */
export interface SysCache {
  /**
   * 缓存名称
   */
  cacheName?: string;

  /**
   * 缓存键名
   */
  cacheKey?: string;

  /**
   * 缓存内容
   */
  cacheValue?: string;

  /**
   * 备注
   */
  remark?: string;
}

export interface Cache {
  name?: string;
  value?: string;
}

export interface SysCacheInfo {
  info?: Record<string, any>;
  dbSize?: number;
  commandStats?: Array<Cache>;
}
