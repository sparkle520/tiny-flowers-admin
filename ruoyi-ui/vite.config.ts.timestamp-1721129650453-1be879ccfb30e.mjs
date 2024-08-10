// vite.config.ts
import path from "node:path";
import vue from "file:///D:/application_pro/java/tiny-flowers-admin/ruoyi-ui/node_modules/@vitejs/plugin-vue/dist/index.mjs";
import vueJsx from "file:///D:/application_pro/java/tiny-flowers-admin/ruoyi-ui/node_modules/@vitejs/plugin-vue-jsx/dist/index.mjs";
import copy from "file:///D:/application_pro/java/tiny-flowers-admin/ruoyi-ui/node_modules/rollup-plugin-copy/dist/index.commonjs.js";
import AutoImport from "file:///D:/application_pro/java/tiny-flowers-admin/ruoyi-ui/node_modules/unplugin-auto-import/dist/vite.js";
import { TDesignResolver } from "file:///D:/application_pro/java/tiny-flowers-admin/ruoyi-ui/node_modules/unplugin-vue-components/dist/resolvers.js";
import Components from "file:///D:/application_pro/java/tiny-flowers-admin/ruoyi-ui/node_modules/unplugin-vue-components/dist/vite.js";
import { loadEnv } from "file:///D:/application_pro/java/tiny-flowers-admin/ruoyi-ui/node_modules/vite/dist/node/index.js";
import viteCompression from "file:///D:/application_pro/java/tiny-flowers-admin/ruoyi-ui/node_modules/vite-plugin-compression/dist/index.mjs";
import { viteMockServe } from "file:///D:/application_pro/java/tiny-flowers-admin/ruoyi-ui/node_modules/vite-plugin-mock/dist/index.mjs";
import prismjs from "file:///D:/application_pro/java/tiny-flowers-admin/ruoyi-ui/node_modules/vite-plugin-prismjs/dist/index.js";
import svgLoader from "file:///D:/application_pro/java/tiny-flowers-admin/ruoyi-ui/node_modules/vite-svg-loader/index.js";
var __vite_injected_original_dirname = "D:\\application_pro\\java\\tiny-flowers-admin\\ruoyi-ui";
var CWD = process.cwd();
var vite_config_default = ({ mode }) => {
  const { VITE_APP_CONTEXT_PATH, VITE_BUILD_COMPRESS } = loadEnv(mode, CWD);
  return {
    base: VITE_APP_CONTEXT_PATH,
    resolve: {
      alias: {
        // 设置路径
        "~": path.resolve(__vite_injected_original_dirname, "./"),
        // 设置别名
        "@": path.resolve(__vite_injected_original_dirname, "./src")
      },
      // https://cn.vitejs.dev/config/#resolve-extensions
      extensions: [".mjs", ".js", ".ts", ".jsx", ".tsx", ".json", ".vue"]
    },
    css: {
      preprocessorOptions: {
        less: {
          modifyVars: {
            hack: `true; @import (reference) "${path.resolve("src/style/variables.less")}";`
          },
          math: "strict",
          javascriptEnabled: true
        }
      }
    },
    plugins: [
      copy({
        targets: [
          {
            src: "node_modules/tinymce/skins/ui/oxide/*.css",
            dest: "public/tinymce/skins/ui/oxide/"
            // 目标public目录下的子目录
          },
          {
            src: "node_modules/tinymce/skins/ui/oxide-dark/*.css",
            dest: "public/tinymce/skins/ui/oxide-dark/"
            // 目标public目录下的子目录
          }
        ],
        copyOnce: true,
        verbose: true
      }),
      prismjs({
        // 放置常用的代码
        languages: [
          "markup",
          "bash",
          "javascript",
          "java",
          "json",
          "css",
          "c",
          "csharp",
          "cpp",
          "diff",
          "dart",
          "php",
          "python",
          "r",
          "rust",
          "ruby",
          "go",
          "graphql",
          "ini",
          "less",
          "lua",
          "kotlin",
          "objectivec",
          "scss",
          "shell",
          "sql",
          "swift",
          "yaml",
          "typescript"
        ],
        // languages: 'all',
        plugins: ["line-numbers"],
        // 配置显示行号插件
        theme: "default",
        // 主题名称
        css: true
      }),
      {
        name: "singleHMR",
        handleHotUpdate({ modules }) {
          modules.forEach((m) => {
            m.importedModules?.clear();
            m.importers = /* @__PURE__ */ new Set();
          });
          return modules;
        }
      },
      vue({
        script: {
          defineModel: true
        }
      }),
      vueJsx(),
      viteMockServe({
        mockPath: "mock",
        enable: false
      }),
      AutoImport({
        resolvers: [
          TDesignResolver({
            library: "vue-next"
          })
        ],
        imports: ["vue-router", "vue", "pinia"]
      }),
      Components({
        resolvers: [
          TDesignResolver({
            library: "vue-next"
          })
        ]
      }),
      svgLoader(),
      viteCompression({
        // threshold: 1024000, // 对大于 1mb 的文件进行压缩
        algorithm: VITE_BUILD_COMPRESS === "brotli" ? "brotliCompress" : "gzip",
        ext: VITE_BUILD_COMPRESS === "brotli" ? ".br" : ".gz",
        deleteOriginFile: false,
        compressionOptions: {
          level: 9,
          memLevel: 9
        }
      })
    ],
    server: {
      port: 3002,
      host: "0.0.0.0",
      proxy: {
        "/api": "http://127.0.0.1:3000/",
        // https://cn.vitejs.dev/config/#server-proxy
        "/dev-api": {
          target: "http://localhost:8080",
          changeOrigin: true,
          ws: true,
          rewrite: (p) => p.replace(/^\/dev-api/, "")
        }
      },
      open: true
    }
  };
};
export {
  vite_config_default as default
};
//# sourceMappingURL=data:application/json;base64,ewogICJ2ZXJzaW9uIjogMywKICAic291cmNlcyI6IFsidml0ZS5jb25maWcudHMiXSwKICAic291cmNlc0NvbnRlbnQiOiBbImNvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9kaXJuYW1lID0gXCJEOlxcXFxhcHBsaWNhdGlvbl9wcm9cXFxcamF2YVxcXFx0aW55LWZsb3dlcnMtYWRtaW5cXFxccnVveWktdWlcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfZmlsZW5hbWUgPSBcIkQ6XFxcXGFwcGxpY2F0aW9uX3Byb1xcXFxqYXZhXFxcXHRpbnktZmxvd2Vycy1hZG1pblxcXFxydW95aS11aVxcXFx2aXRlLmNvbmZpZy50c1wiO2NvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9pbXBvcnRfbWV0YV91cmwgPSBcImZpbGU6Ly8vRDovYXBwbGljYXRpb25fcHJvL2phdmEvdGlueS1mbG93ZXJzLWFkbWluL3J1b3lpLXVpL3ZpdGUuY29uZmlnLnRzXCI7aW1wb3J0IHBhdGggZnJvbSAnbm9kZTpwYXRoJztcblxuaW1wb3J0IHZ1ZSBmcm9tICdAdml0ZWpzL3BsdWdpbi12dWUnO1xuaW1wb3J0IHZ1ZUpzeCBmcm9tICdAdml0ZWpzL3BsdWdpbi12dWUtanN4JztcbmltcG9ydCBjb3B5IGZyb20gJ3JvbGx1cC1wbHVnaW4tY29weSc7XG5pbXBvcnQgQXV0b0ltcG9ydCBmcm9tICd1bnBsdWdpbi1hdXRvLWltcG9ydC92aXRlJztcbmltcG9ydCB7IFREZXNpZ25SZXNvbHZlciB9IGZyb20gJ3VucGx1Z2luLXZ1ZS1jb21wb25lbnRzL3Jlc29sdmVycyc7XG5pbXBvcnQgQ29tcG9uZW50cyBmcm9tICd1bnBsdWdpbi12dWUtY29tcG9uZW50cy92aXRlJztcbmltcG9ydCB0eXBlIHsgQ29uZmlnRW52LCBVc2VyQ29uZmlnIH0gZnJvbSAndml0ZSc7XG5pbXBvcnQgeyBsb2FkRW52IH0gZnJvbSAndml0ZSc7XG5pbXBvcnQgdml0ZUNvbXByZXNzaW9uIGZyb20gJ3ZpdGUtcGx1Z2luLWNvbXByZXNzaW9uJztcbmltcG9ydCB7IHZpdGVNb2NrU2VydmUgfSBmcm9tICd2aXRlLXBsdWdpbi1tb2NrJztcbmltcG9ydCBwcmlzbWpzIGZyb20gJ3ZpdGUtcGx1Z2luLXByaXNtanMnO1xuaW1wb3J0IHN2Z0xvYWRlciBmcm9tICd2aXRlLXN2Zy1sb2FkZXInO1xuXG5jb25zdCBDV0QgPSBwcm9jZXNzLmN3ZCgpO1xuXG4vLyBodHRwczovL3ZpdGVqcy5kZXYvY29uZmlnL1xuZXhwb3J0IGRlZmF1bHQgKHsgbW9kZSB9OiBDb25maWdFbnYpOiBVc2VyQ29uZmlnID0+IHtcbiAgY29uc3QgeyBWSVRFX0FQUF9DT05URVhUX1BBVEgsIFZJVEVfQlVJTERfQ09NUFJFU1MgfSA9IGxvYWRFbnYobW9kZSwgQ1dEKTtcbiAgcmV0dXJuIHtcbiAgICBiYXNlOiBWSVRFX0FQUF9DT05URVhUX1BBVEgsXG4gICAgcmVzb2x2ZToge1xuICAgICAgYWxpYXM6IHtcbiAgICAgICAgLy8gXHU4QkJFXHU3RjZFXHU4REVGXHU1Rjg0XG4gICAgICAgICd+JzogcGF0aC5yZXNvbHZlKF9fZGlybmFtZSwgJy4vJyksXG4gICAgICAgIC8vIFx1OEJCRVx1N0Y2RVx1NTIyQlx1NTQwRFxuICAgICAgICAnQCc6IHBhdGgucmVzb2x2ZShfX2Rpcm5hbWUsICcuL3NyYycpLFxuICAgICAgfSxcbiAgICAgIC8vIGh0dHBzOi8vY24udml0ZWpzLmRldi9jb25maWcvI3Jlc29sdmUtZXh0ZW5zaW9uc1xuICAgICAgZXh0ZW5zaW9uczogWycubWpzJywgJy5qcycsICcudHMnLCAnLmpzeCcsICcudHN4JywgJy5qc29uJywgJy52dWUnXSxcbiAgICB9LFxuXG4gICAgY3NzOiB7XG4gICAgICBwcmVwcm9jZXNzb3JPcHRpb25zOiB7XG4gICAgICAgIGxlc3M6IHtcbiAgICAgICAgICBtb2RpZnlWYXJzOiB7XG4gICAgICAgICAgICBoYWNrOiBgdHJ1ZTsgQGltcG9ydCAocmVmZXJlbmNlKSBcIiR7cGF0aC5yZXNvbHZlKCdzcmMvc3R5bGUvdmFyaWFibGVzLmxlc3MnKX1cIjtgLFxuICAgICAgICAgIH0sXG4gICAgICAgICAgbWF0aDogJ3N0cmljdCcsXG4gICAgICAgICAgamF2YXNjcmlwdEVuYWJsZWQ6IHRydWUsXG4gICAgICAgIH0sXG4gICAgICB9LFxuICAgIH0sXG5cbiAgICBwbHVnaW5zOiBbXG4gICAgICBjb3B5KHtcbiAgICAgICAgdGFyZ2V0czogW1xuICAgICAgICAgIHtcbiAgICAgICAgICAgIHNyYzogJ25vZGVfbW9kdWxlcy90aW55bWNlL3NraW5zL3VpL294aWRlLyouY3NzJyxcbiAgICAgICAgICAgIGRlc3Q6ICdwdWJsaWMvdGlueW1jZS9za2lucy91aS9veGlkZS8nLCAvLyBcdTc2RUVcdTY4MDdwdWJsaWNcdTc2RUVcdTVGNTVcdTRFMEJcdTc2ODRcdTVCNTBcdTc2RUVcdTVGNTVcbiAgICAgICAgICB9LFxuICAgICAgICAgIHtcbiAgICAgICAgICAgIHNyYzogJ25vZGVfbW9kdWxlcy90aW55bWNlL3NraW5zL3VpL294aWRlLWRhcmsvKi5jc3MnLFxuICAgICAgICAgICAgZGVzdDogJ3B1YmxpYy90aW55bWNlL3NraW5zL3VpL294aWRlLWRhcmsvJywgLy8gXHU3NkVFXHU2ODA3cHVibGljXHU3NkVFXHU1RjU1XHU0RTBCXHU3Njg0XHU1QjUwXHU3NkVFXHU1RjU1XG4gICAgICAgICAgfSxcbiAgICAgICAgXSxcbiAgICAgICAgY29weU9uY2U6IHRydWUsXG4gICAgICAgIHZlcmJvc2U6IHRydWUsXG4gICAgICB9KSxcbiAgICAgIHByaXNtanMoe1xuICAgICAgICAvLyBcdTY1M0VcdTdGNkVcdTVFMzhcdTc1MjhcdTc2ODRcdTRFRTNcdTc4MDFcbiAgICAgICAgbGFuZ3VhZ2VzOiBbXG4gICAgICAgICAgJ21hcmt1cCcsXG4gICAgICAgICAgJ2Jhc2gnLFxuICAgICAgICAgICdqYXZhc2NyaXB0JyxcbiAgICAgICAgICAnamF2YScsXG4gICAgICAgICAgJ2pzb24nLFxuICAgICAgICAgICdjc3MnLFxuICAgICAgICAgICdjJyxcbiAgICAgICAgICAnY3NoYXJwJyxcbiAgICAgICAgICAnY3BwJyxcbiAgICAgICAgICAnZGlmZicsXG4gICAgICAgICAgJ2RhcnQnLFxuICAgICAgICAgICdwaHAnLFxuICAgICAgICAgICdweXRob24nLFxuICAgICAgICAgICdyJyxcbiAgICAgICAgICAncnVzdCcsXG4gICAgICAgICAgJ3J1YnknLFxuICAgICAgICAgICdnbycsXG4gICAgICAgICAgJ2dyYXBocWwnLFxuICAgICAgICAgICdpbmknLFxuICAgICAgICAgICdsZXNzJyxcbiAgICAgICAgICAnbHVhJyxcbiAgICAgICAgICAna290bGluJyxcbiAgICAgICAgICAnb2JqZWN0aXZlYycsXG4gICAgICAgICAgJ3Njc3MnLFxuICAgICAgICAgICdzaGVsbCcsXG4gICAgICAgICAgJ3NxbCcsXG4gICAgICAgICAgJ3N3aWZ0JyxcbiAgICAgICAgICAneWFtbCcsXG4gICAgICAgICAgJ3R5cGVzY3JpcHQnLFxuICAgICAgICBdLFxuICAgICAgICAvLyBsYW5ndWFnZXM6ICdhbGwnLFxuICAgICAgICBwbHVnaW5zOiBbJ2xpbmUtbnVtYmVycyddLCAvLyBcdTkxNERcdTdGNkVcdTY2M0VcdTc5M0FcdTg4NENcdTUzRjdcdTYzRDJcdTRFRjZcbiAgICAgICAgdGhlbWU6ICdkZWZhdWx0JywgLy8gXHU0RTNCXHU5ODk4XHU1NDBEXHU3OUYwXG4gICAgICAgIGNzczogdHJ1ZSxcbiAgICAgIH0pLFxuICAgICAge1xuICAgICAgICBuYW1lOiAnc2luZ2xlSE1SJyxcbiAgICAgICAgaGFuZGxlSG90VXBkYXRlKHsgbW9kdWxlcyB9KSB7XG4gICAgICAgICAgbW9kdWxlcy5mb3JFYWNoKChtKSA9PiB7XG4gICAgICAgICAgICBtLmltcG9ydGVkTW9kdWxlcz8uY2xlYXIoKTtcbiAgICAgICAgICAgIG0uaW1wb3J0ZXJzID0gbmV3IFNldCgpO1xuICAgICAgICAgIH0pO1xuICAgICAgICAgIHJldHVybiBtb2R1bGVzO1xuICAgICAgICB9LFxuICAgICAgfSxcbiAgICAgIHZ1ZSh7XG4gICAgICAgIHNjcmlwdDoge1xuICAgICAgICAgIGRlZmluZU1vZGVsOiB0cnVlLFxuICAgICAgICB9LFxuICAgICAgfSksXG4gICAgICB2dWVKc3goKSxcbiAgICAgIHZpdGVNb2NrU2VydmUoe1xuICAgICAgICBtb2NrUGF0aDogJ21vY2snLFxuICAgICAgICBlbmFibGU6IGZhbHNlLFxuICAgICAgfSksXG4gICAgICBBdXRvSW1wb3J0KHtcbiAgICAgICAgcmVzb2x2ZXJzOiBbXG4gICAgICAgICAgVERlc2lnblJlc29sdmVyKHtcbiAgICAgICAgICAgIGxpYnJhcnk6ICd2dWUtbmV4dCcsXG4gICAgICAgICAgfSksXG4gICAgICAgIF0sXG4gICAgICAgIGltcG9ydHM6IFsndnVlLXJvdXRlcicsICd2dWUnLCAncGluaWEnXSxcbiAgICAgIH0pLFxuICAgICAgQ29tcG9uZW50cyh7XG4gICAgICAgIHJlc29sdmVyczogW1xuICAgICAgICAgIFREZXNpZ25SZXNvbHZlcih7XG4gICAgICAgICAgICBsaWJyYXJ5OiAndnVlLW5leHQnLFxuICAgICAgICAgIH0pLFxuICAgICAgICBdLFxuICAgICAgfSksXG4gICAgICBzdmdMb2FkZXIoKSxcbiAgICAgIHZpdGVDb21wcmVzc2lvbih7XG4gICAgICAgIC8vIHRocmVzaG9sZDogMTAyNDAwMCwgLy8gXHU1QkY5XHU1OTI3XHU0RThFIDFtYiBcdTc2ODRcdTY1ODdcdTRFRjZcdThGREJcdTg4NENcdTUzOEJcdTdGMjlcbiAgICAgICAgYWxnb3JpdGhtOiBWSVRFX0JVSUxEX0NPTVBSRVNTID09PSAnYnJvdGxpJyA/ICdicm90bGlDb21wcmVzcycgOiAnZ3ppcCcsXG4gICAgICAgIGV4dDogVklURV9CVUlMRF9DT01QUkVTUyA9PT0gJ2Jyb3RsaScgPyAnLmJyJyA6ICcuZ3onLFxuICAgICAgICBkZWxldGVPcmlnaW5GaWxlOiBmYWxzZSxcbiAgICAgICAgY29tcHJlc3Npb25PcHRpb25zOiB7XG4gICAgICAgICAgbGV2ZWw6IDksXG4gICAgICAgICAgbWVtTGV2ZWw6IDksXG4gICAgICAgIH0sXG4gICAgICB9KSxcbiAgICBdLFxuXG4gICAgc2VydmVyOiB7XG4gICAgICBwb3J0OiAzMDAyLFxuICAgICAgaG9zdDogJzAuMC4wLjAnLFxuICAgICAgcHJveHk6IHtcbiAgICAgICAgJy9hcGknOiAnaHR0cDovLzEyNy4wLjAuMTozMDAwLycsXG4gICAgICAgIC8vIGh0dHBzOi8vY24udml0ZWpzLmRldi9jb25maWcvI3NlcnZlci1wcm94eVxuICAgICAgICAnL2Rldi1hcGknOiB7XG4gICAgICAgICAgdGFyZ2V0OiAnaHR0cDovL2xvY2FsaG9zdDo4MDgwJyxcbiAgICAgICAgICBjaGFuZ2VPcmlnaW46IHRydWUsXG4gICAgICAgICAgd3M6IHRydWUsXG4gICAgICAgICAgcmV3cml0ZTogKHApID0+IHAucmVwbGFjZSgvXlxcL2Rldi1hcGkvLCAnJyksXG4gICAgICAgIH0sXG4gICAgICB9LFxuICAgICAgb3BlbjogdHJ1ZSxcbiAgICB9LFxuICB9O1xufTtcbiJdLAogICJtYXBwaW5ncyI6ICI7QUFBcVYsT0FBTyxVQUFVO0FBRXRXLE9BQU8sU0FBUztBQUNoQixPQUFPLFlBQVk7QUFDbkIsT0FBTyxVQUFVO0FBQ2pCLE9BQU8sZ0JBQWdCO0FBQ3ZCLFNBQVMsdUJBQXVCO0FBQ2hDLE9BQU8sZ0JBQWdCO0FBRXZCLFNBQVMsZUFBZTtBQUN4QixPQUFPLHFCQUFxQjtBQUM1QixTQUFTLHFCQUFxQjtBQUM5QixPQUFPLGFBQWE7QUFDcEIsT0FBTyxlQUFlO0FBYnRCLElBQU0sbUNBQW1DO0FBZXpDLElBQU0sTUFBTSxRQUFRLElBQUk7QUFHeEIsSUFBTyxzQkFBUSxDQUFDLEVBQUUsS0FBSyxNQUE2QjtBQUNsRCxRQUFNLEVBQUUsdUJBQXVCLG9CQUFvQixJQUFJLFFBQVEsTUFBTSxHQUFHO0FBQ3hFLFNBQU87QUFBQSxJQUNMLE1BQU07QUFBQSxJQUNOLFNBQVM7QUFBQSxNQUNQLE9BQU87QUFBQTtBQUFBLFFBRUwsS0FBSyxLQUFLLFFBQVEsa0NBQVcsSUFBSTtBQUFBO0FBQUEsUUFFakMsS0FBSyxLQUFLLFFBQVEsa0NBQVcsT0FBTztBQUFBLE1BQ3RDO0FBQUE7QUFBQSxNQUVBLFlBQVksQ0FBQyxRQUFRLE9BQU8sT0FBTyxRQUFRLFFBQVEsU0FBUyxNQUFNO0FBQUEsSUFDcEU7QUFBQSxJQUVBLEtBQUs7QUFBQSxNQUNILHFCQUFxQjtBQUFBLFFBQ25CLE1BQU07QUFBQSxVQUNKLFlBQVk7QUFBQSxZQUNWLE1BQU0sOEJBQThCLEtBQUssUUFBUSwwQkFBMEIsQ0FBQztBQUFBLFVBQzlFO0FBQUEsVUFDQSxNQUFNO0FBQUEsVUFDTixtQkFBbUI7QUFBQSxRQUNyQjtBQUFBLE1BQ0Y7QUFBQSxJQUNGO0FBQUEsSUFFQSxTQUFTO0FBQUEsTUFDUCxLQUFLO0FBQUEsUUFDSCxTQUFTO0FBQUEsVUFDUDtBQUFBLFlBQ0UsS0FBSztBQUFBLFlBQ0wsTUFBTTtBQUFBO0FBQUEsVUFDUjtBQUFBLFVBQ0E7QUFBQSxZQUNFLEtBQUs7QUFBQSxZQUNMLE1BQU07QUFBQTtBQUFBLFVBQ1I7QUFBQSxRQUNGO0FBQUEsUUFDQSxVQUFVO0FBQUEsUUFDVixTQUFTO0FBQUEsTUFDWCxDQUFDO0FBQUEsTUFDRCxRQUFRO0FBQUE7QUFBQSxRQUVOLFdBQVc7QUFBQSxVQUNUO0FBQUEsVUFDQTtBQUFBLFVBQ0E7QUFBQSxVQUNBO0FBQUEsVUFDQTtBQUFBLFVBQ0E7QUFBQSxVQUNBO0FBQUEsVUFDQTtBQUFBLFVBQ0E7QUFBQSxVQUNBO0FBQUEsVUFDQTtBQUFBLFVBQ0E7QUFBQSxVQUNBO0FBQUEsVUFDQTtBQUFBLFVBQ0E7QUFBQSxVQUNBO0FBQUEsVUFDQTtBQUFBLFVBQ0E7QUFBQSxVQUNBO0FBQUEsVUFDQTtBQUFBLFVBQ0E7QUFBQSxVQUNBO0FBQUEsVUFDQTtBQUFBLFVBQ0E7QUFBQSxVQUNBO0FBQUEsVUFDQTtBQUFBLFVBQ0E7QUFBQSxVQUNBO0FBQUEsVUFDQTtBQUFBLFFBQ0Y7QUFBQTtBQUFBLFFBRUEsU0FBUyxDQUFDLGNBQWM7QUFBQTtBQUFBLFFBQ3hCLE9BQU87QUFBQTtBQUFBLFFBQ1AsS0FBSztBQUFBLE1BQ1AsQ0FBQztBQUFBLE1BQ0Q7QUFBQSxRQUNFLE1BQU07QUFBQSxRQUNOLGdCQUFnQixFQUFFLFFBQVEsR0FBRztBQUMzQixrQkFBUSxRQUFRLENBQUMsTUFBTTtBQUNyQixjQUFFLGlCQUFpQixNQUFNO0FBQ3pCLGNBQUUsWUFBWSxvQkFBSSxJQUFJO0FBQUEsVUFDeEIsQ0FBQztBQUNELGlCQUFPO0FBQUEsUUFDVDtBQUFBLE1BQ0Y7QUFBQSxNQUNBLElBQUk7QUFBQSxRQUNGLFFBQVE7QUFBQSxVQUNOLGFBQWE7QUFBQSxRQUNmO0FBQUEsTUFDRixDQUFDO0FBQUEsTUFDRCxPQUFPO0FBQUEsTUFDUCxjQUFjO0FBQUEsUUFDWixVQUFVO0FBQUEsUUFDVixRQUFRO0FBQUEsTUFDVixDQUFDO0FBQUEsTUFDRCxXQUFXO0FBQUEsUUFDVCxXQUFXO0FBQUEsVUFDVCxnQkFBZ0I7QUFBQSxZQUNkLFNBQVM7QUFBQSxVQUNYLENBQUM7QUFBQSxRQUNIO0FBQUEsUUFDQSxTQUFTLENBQUMsY0FBYyxPQUFPLE9BQU87QUFBQSxNQUN4QyxDQUFDO0FBQUEsTUFDRCxXQUFXO0FBQUEsUUFDVCxXQUFXO0FBQUEsVUFDVCxnQkFBZ0I7QUFBQSxZQUNkLFNBQVM7QUFBQSxVQUNYLENBQUM7QUFBQSxRQUNIO0FBQUEsTUFDRixDQUFDO0FBQUEsTUFDRCxVQUFVO0FBQUEsTUFDVixnQkFBZ0I7QUFBQTtBQUFBLFFBRWQsV0FBVyx3QkFBd0IsV0FBVyxtQkFBbUI7QUFBQSxRQUNqRSxLQUFLLHdCQUF3QixXQUFXLFFBQVE7QUFBQSxRQUNoRCxrQkFBa0I7QUFBQSxRQUNsQixvQkFBb0I7QUFBQSxVQUNsQixPQUFPO0FBQUEsVUFDUCxVQUFVO0FBQUEsUUFDWjtBQUFBLE1BQ0YsQ0FBQztBQUFBLElBQ0g7QUFBQSxJQUVBLFFBQVE7QUFBQSxNQUNOLE1BQU07QUFBQSxNQUNOLE1BQU07QUFBQSxNQUNOLE9BQU87QUFBQSxRQUNMLFFBQVE7QUFBQTtBQUFBLFFBRVIsWUFBWTtBQUFBLFVBQ1YsUUFBUTtBQUFBLFVBQ1IsY0FBYztBQUFBLFVBQ2QsSUFBSTtBQUFBLFVBQ0osU0FBUyxDQUFDLE1BQU0sRUFBRSxRQUFRLGNBQWMsRUFBRTtBQUFBLFFBQzVDO0FBQUEsTUFDRjtBQUFBLE1BQ0EsTUFBTTtBQUFBLElBQ1I7QUFBQSxFQUNGO0FBQ0Y7IiwKICAibmFtZXMiOiBbXQp9Cg==
