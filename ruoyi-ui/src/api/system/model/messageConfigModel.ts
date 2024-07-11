import type { BaseEntity } from '@/api/model/resultModel';

/**
 * 消息配置查询对象
 */
export interface SysMessageConfigQuery extends BaseEntity {
  /** 标题 */
  title?: string;
  /** 消息类型 */
  messageType?: string;
  /** 支持平台标识 */
  supplierType?: string;
  /** 状态（1正常 0停用） */
  status?: number;
}
/**
 * 消息配置业务对象
 */
export interface SysMessageConfigForm {
  /** 消息设置id */
  messageConfigId?: number;
  /** 标题 */
  title?: string;
  /** 消息类型 */
  messageType?: string;
  /** 支持平台标识 */
  supplierType?: string;
  /** 配置json */
  configJson?: string | object;
  /** 状态（1正常 0停用） */
  status?: number;
  /** 备注 */
  remark?: string;
}
/**
 * 消息配置视图对象
 */
export interface SysMessageConfigVo {
  /** 消息设置id */
  messageConfigId?: number;
  /** 标题 */
  title?: string;
  /** 消息类型 */
  messageType?: string;
  /** 支持平台标识 */
  supplierType?: string;
  /** 配置json */
  configJson?: string | object;
  /** 状态（1正常 0停用） */
  status?: number;
  /** 备注 */
  remark?: string;
  /** 更新时间 */
  updateTime?: any;
  /** 创建时间 */
  createTime?: any;
}
