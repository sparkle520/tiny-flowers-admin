import type { TabValue } from 'tdesign-vue-next';
import type { LocationQueryRaw, RouteMeta, RouteRecordName, RouteRecordRaw } from 'vue-router';

export interface MenuRoute {
  path: string;
  title?: string;
  name?: string;
  icon?:
    | string
    | {
        render: () => void;
      };
  redirect?: string;
  children: MenuRoute[];
  meta: Record<string, any>;
  query: any;
}

export type ComplexRoute = Partial<MenuRoute | (RouteRecordRaw & { query: any })>;

export type ModeType = 'dark' | 'light';

export interface UserInfo {
  name: string;
  roles: string[];
}

export interface NotificationItem {
  id: string;
  content: string;
  type: string;
  status: boolean;
  collected: boolean;
  date: string;
  quality: string;
}

export interface NoticeItem {
  title?: string;
  read: boolean;
  message: string;
  time: string;
}

export interface TRouterInfo {
  path: string;
  query?: LocationQueryRaw;
  routeIdx?: number;
  title?: string;
  name?: RouteRecordName;
  componentName?: string;
  isAlive?: boolean;
  isHome?: boolean;
  meta?: RouteMeta;
}

export interface TTabRouterType {
  isRefreshing: boolean;
  tabRouterList: Array<TRouterInfo>;
}

export interface TTabRemoveOptions {
  value: TabValue;
  index: number;
  e: MouseEvent;
}
