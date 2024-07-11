import 'nprogress/nprogress.css'; // progress bar style

import NProgress from 'nprogress'; // progress bar

import router from '@/router';
import { usePermissionStoreHook, useUserStore } from '@/store';
import { useTitle } from '@/utils/doc';
import { isRelogin } from '@/utils/request';
import { isHttp } from '@/utils/validate';

let errorRetry = 0;

NProgress.configure({ showSpinner: false });

router.beforeEach(async (to, from, next) => {
  NProgress.start();

  const permissionStore = usePermissionStoreHook();
  const { whiteListRouters } = permissionStore;

  const userStore = useUserStore();
  const { token } = userStore;
  if (token) {
    if (to.meta.title) {
      useTitle(to.meta.title as string);
    }

    // 错误重试超出限制
    if (errorRetry >= 3) {
      errorRetry = 0;
      next({
        path: '/500',
        query: { redirect: encodeURIComponent(to.fullPath) },
      });
      return;
    }

    if (whiteListRouters.indexOf(to.path) !== -1) {
      next();
      return;
    }

    const { roles } = userStore;

    if (roles && roles.length > 0) {
      next();
    } else {
      isRelogin.show = true;
      try {
        await userStore.getUserInfo();

        isRelogin.show = false;

        const accessRoutes = await permissionStore.generateRoutes();
        // 根据roles权限生成可访问的路由表
        accessRoutes.forEach((route) => {
          if (!isHttp(route.path)) {
            router.addRoute(route); // 动态添加可访问路由表
          }
        });
        next({ ...to, replace: true }); // hack方法 确保addRoutes已完成
        errorRetry = 0;
      } catch (error) {
        errorRetry++;
        // await userStore.logout();
        next({
          path: '/login',
          query: { redirect: encodeURIComponent(to.fullPath) },
        });
        NProgress.done();
      }
    }
  } else {
    /* white list router */
    if (whiteListRouters.indexOf(to.path) !== -1) {
      next();
    } else {
      next({
        path: '/login',
        query: { redirect: encodeURIComponent(to.fullPath) },
      });
    }
    NProgress.done();
  }
});

router.afterEach(async (to) => {
  if (to.path === '/login') {
    const userStore = useUserStore();
    const { token } = userStore;
    const isLogin = await userStore.isLogin();
    if (isLogin) {
      const redirect = to.query.redirect as string;
      const redirectUrl = redirect ? decodeURIComponent(redirect) : '/';
      await router.push(redirectUrl);
    } else if (token) {
      await userStore.logout();
    }
  }
  NProgress.done();
});
