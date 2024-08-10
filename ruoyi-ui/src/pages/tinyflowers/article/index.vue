<template>
  <t-card>
    <t-space direction="vertical" style="width: 100%">
      <t-form v-show="showSearch" ref="queryRef" :data="queryParams" layout="inline" reset-type="initial"
        label-width="calc(4em + 12px)">
        <t-form-item label="标题" name="title">
          <t-input v-model="queryParams.title" placeholder="请输入文章标题" clearable @enter="handleQuery" />
        </t-form-item>
        <t-form-item label="简介" name="intro">
          <t-input v-model="queryParams.intro" placeholder="请输入文章简介" clearable @enter="handleQuery" />
        </t-form-item>
        <t-form-item label="审核状态" name="status">
          <t-select v-model="queryParams.status" placeholder="请选择文章审核状态" clearable>
            <t-option v-for="dict in article_status_type" :key="dict.value" :label="dict.label" :value="dict.value" />
          </t-select>
        </t-form-item>
        <t-form-item label="可见范围" name="viewScopeType">
          <t-select v-model="queryParams.viewScopeType" placeholder="请选择文章可见范围" clearable>
            <t-option v-for="dict in view_scope_type" :key="dict.value" :label="dict.label" :value="dict.value" />
          </t-select>
        </t-form-item>
        <!-- <t-form-item label="浏览量" name="viewCount">
          <t-input v-model="queryParams.viewCount" placeholder="请输入浏览量" clearable @enter="handleQuery" />
        </t-form-item>
        <t-form-item label="收藏数" name="collectCount">
          <t-input v-model="queryParams.collectCount" placeholder="请输入收藏数" clearable @enter="handleQuery" />
        </t-form-item>
        <t-form-item label="点赞数" name="thumbsUpCount">
          <t-input v-model="queryParams.thumbsUpCount" placeholder="请输入点赞数" clearable @enter="handleQuery" />
        </t-form-item>
        <t-form-item label="评论数" name="commentCount">
          <t-input v-model="queryParams.commentCount" placeholder="请输入评论数" clearable @enter="handleQuery" />
        </t-form-item>
        <t-form-item label="分类id" name="sortId">
          <t-input v-model="queryParams.sortId" placeholder="请输入分类id" clearable @enter="handleQuery" />
        </t-form-item> -->
        <t-form-item label-width="0px">
          <t-button theme="primary" @click="handleQuery">
            <template #icon> <search-icon /></template>
            搜索
          </t-button>
          <t-button theme="default" @click="resetQuery">
            <template #icon> <refresh-icon /></template>
            重置
          </t-button>
        </t-form-item>
      </t-form>

      <t-table  v-model:column-controller-visible="columnControllerVisible" :loading="loading" hover row-key="id"
        :data="articleList" :columns="columns" :selected-row-keys="ids" select-on-row-click :pagination="pagination"
        :column-controller="{
          hideTriggerButton: true,
        }" @select-change="handleSelectionChange">
        <template #topContent>
          <t-row>
            <t-col flex="auto">
              <t-button v-hasPermi="['system:article:add']" theme="primary" @click="handleAdd">
                <template #icon> <add-icon /></template>
                新增
              </t-button>
              <t-button v-hasPermi="['system:article:edit']" theme="default" variant="outline" :disabled="single"
                @click="handleUpdate()">
                <template #icon> <edit-icon /> </template>
                修改
              </t-button>
              <t-button v-hasPermi="['system:article:remove']" theme="danger" variant="outline" :disabled="multiple"
                @click="handleDelete()">
                <template #icon> <delete-icon /> </template>
                删除
              </t-button>
              <t-button v-hasPermi="['system:article:export']" theme="default" variant="outline" @click="handleExport">
                <template #icon> <download-icon /> </template>
                导出
              </t-button>
              <span class="selected-count">已选 {{ ids.length }} 项</span>
            </t-col>
            <t-col flex="none">
              <t-button theme="default" shape="square" variant="outline" @click="showSearch = !showSearch">
                <template #icon> <search-icon /> </template>
              </t-button>
              <t-button theme="default" variant="outline" @click="columnControllerVisible = true">
                <template #icon> <setting-icon /> </template>
                列配置
              </t-button>
            </t-col>
          </t-row>
        </template>
        <template #intro="{row}" >
          <span >{{ row.intro }}</span>
        </template>
       <template #img="{row}">
        <image-preview
            :src="row.img"
            width="100px"
            height="60px"
            fit="cover"
          />
       </template>
        <template #status="{ row }">
          <dict-tag :options="article_status_type" :value="row.status" />
        </template>
        <template #tags="{ row }">
          <t-tag v-for="item in row.tags" style="margin-bottom: 4px;" theme="success" variant="outline">{{ item.name
            }}</t-tag>
        </template>
        <template #viewScopeType="{ row }">
          <dict-tag :options="view_scope_type" :value="row.viewScopeType" />
        </template>
        <template #operation="{ row }">
          <t-space :size="8" break-line>
            <t-link v-hasPermi="['system:article:query']" theme="primary" hover="color" @click.stop="handleDetail(row)">
              <browse-icon />详情
            </t-link>
            <t-link v-hasPermi="['system:article:edit']" theme="primary" hover="color" @click.stop="handleUpdate(row)">
              <edit-icon />修改
            </t-link>
            <t-link v-hasPermi="['system:article:remove']" theme="danger" hover="color" @click.stop="handleDelete(row)">
              <delete-icon />删除
            </t-link>
          </t-space>
        </template>
      </t-table>
    </t-space>

    <!-- 添加或修改文章对话框 -->
    <t-dialog v-model:visible="open" :header="title" destroy-on-close :close-on-overlay-click="false" width="500px"
      attach="body" :confirm-btn="{
        loading: buttonLoading,
      }" @confirm="articleRef.submit()">
      <t-loading :loading="buttonLoading" size="small">
        <t-form ref="articleRef" label-align="right" :data="form" :rules="rules" label-width="calc(5em + 41px)"
          scroll-to-first-error="smooth" @submit="submitForm">
          <t-form-item label="文章标题" name="title">
            <t-input v-model="form.title" placeholder="请输入文章标题" clearable />
          </t-form-item>
          <t-form-item label="文章简介" name="intro">
            <t-input v-model="form.intro" placeholder="请输入文章简介" clearable />
          </t-form-item>
          <t-form-item label="文章标签" name="tags">
            <t-tag-input v-model="tempTags" :max="4" placeholder="添加新标签" @enter="onEnter"
              :tag-props="{ theme: 'primary' }">

            </t-tag-input>
          </t-form-item>
          <t-form-item label="图片url路径" name="img">
            <t-input v-model="form.img" placeholder="请输入图片url路径" clearable />
          </t-form-item>
          <t-form-item label="oss 路径" name="content">
            <t-input v-model="form.content" placeholder="请输入对象存储 oss 路径" clearable />
          </t-form-item>
          <t-form-item label="文章内容" name="contentMd">
            <t-textarea v-model="form.contentMd" placeholder="请输入文章内容"
            autofocus />
          </t-form-item>
          <t-form-item label="文章可见范围" name="viewScopeType">
            <t-select v-model="form.viewScopeType" placeholder="请选择文章可见范围" clearable>
              <t-option v-for="dict in view_scope_type" :key="dict.value" :label="dict.label"
                :value="parseInt(dict.value)" />
            </t-select>
          </t-form-item>

          <t-form-item label="分类" name="sortId">
            <t-tree-select v-model="form.sortId" :data="articleSortList" :tree-props="{
              keys: { value: 'id', label: 'sortName', children: 'children' },
              checkStrictly: true,
            }" placeholder="选择上级菜单" />
          </t-form-item>
        </t-form>
      </t-loading>
    </t-dialog>

    <!-- 文章详情 -->
    <t-dialog v-model:visible="openView" header="文章详情" placement="center" width="700px" attach="body" :footer="false">
      <my-descriptions :loading="openViewLoading">
        <t-descriptions-item label="文章标题" :span="2">{{ form.title }}</t-descriptions-item>
        <t-descriptions-item label="文章简介" :span="2">{{ form.intro }}</t-descriptions-item>
        <t-descriptions-item label="标签" :span="2">
          <t-tag v-for="item in form.tags" style="margin-right: 4px;" theme="success" variant="outline">{{ item.name
            }}</t-tag>
        </t-descriptions-item>
        <t-descriptions-item label="图片" :span="2">
          <image-preview
            :src="form.img"
            width="100%"
            height="100px"
            fit="cover"
          />
        </t-descriptions-item>
        <t-descriptions-item label="对象存储 oss 路径" :span="2">
          <editor-preview :html-text="form.content" />
        </t-descriptions-item>
        <t-descriptions-item label="作者名称(id)">{{ form.authorName }}</t-descriptions-item>
        <!-- <t-descriptions-item label="逻辑">{{ form.delFlag }}</t-descriptions-item> -->
        <t-descriptions-item label="文章审核状态">
          <dict-tag :options="article_status_type" :value="form.status" />
        </t-descriptions-item>
        <t-descriptions-item label="文章可见范围">
          <dict-tag :options="view_scope_type" :value="form.viewScopeType" />
        </t-descriptions-item>
        <t-descriptions-item label="浏览量">{{ form.viewCount }}</t-descriptions-item>
        <t-descriptions-item label="收藏数">{{ form.collectCount }}</t-descriptions-item>
        <t-descriptions-item label="点赞数">{{ form.thumbsUpCount }}</t-descriptions-item>
        <t-descriptions-item label="评论数">{{ form.commentCount }}</t-descriptions-item>
        <t-descriptions-item label="分类">{{ form.sortName }}</t-descriptions-item>
        <t-descriptions-item label="创建时间">{{ parseTime(form.createTime) }}</t-descriptions-item>
        <t-descriptions-item label="更新时间">{{ parseTime(form.updateTime) }}</t-descriptions-item>
      </my-descriptions>
    </t-dialog>
  </t-card>
</template>
<script lang="ts" setup>
defineOptions({
  name: 'Article',
});
import {
  AddIcon,
  BrowseIcon,
  DeleteIcon,
  DownloadIcon,
  EditIcon,
  RefreshIcon,
  SearchIcon,
  SettingIcon,
} from 'tdesign-icons-vue-next';
import type { FormInstanceFunctions, FormRule, PageInfo, PrimaryTableCol, SubmitContext, } from 'tdesign-vue-next';
import { computed, getCurrentInstance, ref } from 'vue';
import { ArrayOps } from '@/utils/array';
import type { ArticleForm, ArticleQuery, ArticleVo } from '@/api/tinyflowers/model/articleModel';
import { listArticle, getArticle, delArticle, addArticle, updateArticle } from '@/api/tinyflowers/article';
import Editor from '@/components/editor/index.vue';
import EditorPreview from '@/components/editor-preview/index.vue';
import { getUserProfile } from '@/api/system/profile';
import type { ArticleSortForm, ArticleSort, ArticleSortQuery, ArticleSortVo } from '@/api/tinyflowers/model/articlesortModel';
import { listSort, getSort, delSort, addSort, updateSort } from '@/api/tinyflowers/articleSort';
const { proxy } = getCurrentInstance();
const { article_status_type, view_scope_type } = proxy.useDict('article_status_type', 'view_scope_type');

const openView = ref(false);
const openViewLoading = ref(false);
const articleRef = ref<FormInstanceFunctions>();
const open = ref(false);
const buttonLoading = ref(false);
const title = ref('');
const articleList = ref<ArticleVo[]>([]);
const articleSortList = ref<ArticleSort[]>([]);
const loading = ref(false);
const columnControllerVisible = ref(false);
const showSearch = ref(true);
const total = ref(0);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const tempTags = ref<Tag[]>([])
onMounted(() => {
  listSort(queryParams2)
    .then((response) => {      
      articleSortList.value = response.data;
    })
})
// 校验规则
const rules = ref<Record<string, Array<FormRule>>>({
  id: [{ required: true, message: '文章id不能为空' }],
  title: [{ required: true, message: '文章标题不能为空' }, { max: 255, message: '文章标题不能超过255个字符}' }],
  intro: [{ required: true, message: '文章简介不能为空' }, { max: 255, message: '文章简介不能超过255个字符}' }],
  img: [{ max: 255, message: '图片url路径不能超过255个字符}' }],
  content: [{ required: true, message: '对象存储 oss 路径不能为空' }, { max: 255, message: '对象存储 oss 路径不能超过255个字符}' }],
  contentMd: [{ required: true, message: '文章内容不能为空' }],
  // delFlag: [{ required: true, message: '逻辑删除不能为空' }],
  status: [{ required: true, message: '文章审核状态不能为空' }],
  viewScopeType: [{ required: true, message: '文章可见范围不能为空' }],
  sortId: [{ required: true, message: '分类不能为空' }],
});

// 列显隐信息
const columns = ref<Array<PrimaryTableCol>>([
  { title: `选择列`, colKey: 'row-select', type: 'multiple', width: 50, align: 'center' },
  { title: `文章标题`, colKey: 'title', align: 'center',width: 130,ellipsis: true },
  { title: `文章简介`, colKey: 'intro', align: 'center', width: 130,ellipsis: true},
  { title: `图片`, colKey: 'img', align: 'center',width:132 },
  { title: `对象存储 oss 路径`, colKey: 'content', align: 'center',width: 130,ellipsis: true},
  { title: `标签`, colKey: 'tags', align: 'center' },
  { title: `审核状态`, colKey: 'status', align: 'center' },
  { title: `可见范围`, colKey: 'viewScopeType', align: 'center' },
  // { title: `浏览量`, colKey: 'viewCount', align: 'center' },
  // { title: `收藏数`, colKey: 'collectCount', align: 'center' },
  // { title: `点赞数`, colKey: 'thumbsUpCount', align: 'center' },
  // { title: `评论数`, colKey: 'commentCount', align: 'center' },
  { title: `分类`, colKey: 'sortName', align: 'center' },
  { title: `操作`, colKey: 'operation', align: 'center', width: 180 },
]);
// 提交表单对象
const form = ref<ArticleVo & ArticleForm>({});
// 查询对象
const queryParams = ref<ArticleQuery>({
  pageNum: 1,
  pageSize: 10,
  title: undefined,
  intro: undefined,
  img: undefined,
  content: undefined,
  author: undefined,
  delFlag: undefined,
  status: undefined,
  viewScopeType: undefined,
  viewCount: undefined,
  collectCount: undefined,
  thumbsUpCount: undefined,
  commentCount: undefined,
  sortId: undefined,
});
const queryParams2 = ref<ArticleSortQuery>({
  sortName: undefined,
  /** 上一级父类id */
  parentId: undefined,

});
const onEnter = (value, { inputValue }) => {
  if (value.length >= 4 && inputValue) {
    MessagePlugin.warning('最多只能输入 4 个标签!');
  }
};

// 分页
const pagination = computed(() => {
  return {
    current: queryParams.value.pageNum,
    pageSize: queryParams.value.pageSize,
    total: total.value,
    showJumper: true,
    onChange: (pageInfo: PageInfo) => {
      queryParams.value.pageNum = pageInfo.current;
      queryParams.value.pageSize = pageInfo.pageSize;
      getList();
    },
  };
});

/** 查询文章列表 */
function getList() {
  loading.value = true;


  listArticle(queryParams.value)
    .then((response) => {
      articleList.value = response.rows;
      total.value = response.total;
    })
    .finally(() => (loading.value = false));
}

// 表单重置
function reset() {
  form.value = {};
  tempTags.value = []
  proxy.resetForm('articleRef');
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm('queryRef');
  handleQuery();
}

/** 多选框选中数据 */
function handleSelectionChange(selection: Array<string | number>) {
  ids.value = selection;
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = '添加文章';
}

/** 详情按钮操作 */
function handleDetail(row: ArticleVo) {
  reset();
  openView.value = true;
  openViewLoading.value = true;
  const id = row.id;
  getArticle(id).then((response) => {

    form.value = response.data;
    openViewLoading.value = false;
  });
}

/** 修改按钮操作 */
function handleUpdate(row?: ArticleVo) {
  buttonLoading.value = true;
  reset();
  open.value = true;
  title.value = '修改文章';
  const id = row?.id || ids.value.at(0);
  getArticle(id).then((response) => {
    buttonLoading.value = false;
    form.value = response.data;
    form.value.tags.forEach(element => {
      tempTags.value.push(element.name)
    });
  });
}


interface tag {
  id?: number;
  name?: string;
}

/** 提交表单 */
function submitForm({ validateResult, firstError }: SubmitContext) {
  if (validateResult === true) {
    buttonLoading.value = true;
    const msgLoading = proxy.$modal.msgLoading('提交中...');
    //记得删除  
    // delete form.value['author']


    if (form.value.id) {

      form.value.tags = <Tag[]>[];
      if (tempTags.value != null) {
        for (let index = 0; index < tempTags.value.length; index++) {
          const element = tempTags.value[index];
          form.value.tags.push({
            id: undefined,
            name: element,
          })
        }

      } else {
        form.value.tags = undefined
      }



      updateArticle(form.value)
        .then(() => {
          proxy.$modal.msgSuccess('修改成功');
          open.value = false;
          getList();
        })
        .finally(() => {
          buttonLoading.value = false;
          proxy.$modal.msgClose(msgLoading);
        });
    } else {
      form.value.tags = <Tag[]>[];

      for (let index = 0; index < tempTags.value.length; index++) {
        const element = tempTags.value[index];
        form.value.tags.push({
          id: undefined,
          name: element,
        })
      }


      addArticle(form.value)
        .then(() => {
          proxy.$modal.msgSuccess('新增成功');
          open.value = false;
          getList();
        })
        .finally(() => {
          buttonLoading.value = false;
          proxy.$modal.msgClose(msgLoading);
        });
    }
  } else {
    proxy.$modal.msgError(firstError);
  }
}

/** 删除按钮操作 */
function handleDelete(row?: ArticleVo) {
  const $ids = row?.id || ids.value;
  proxy.$modal.confirm(`是否确认删除文章编号为${$ids}的数据项？`, () => {
    const msgLoading = proxy.$modal.msgLoading('正在删除中...');
    return delArticle($ids)
      .then(() => {
        ids.value = ArrayOps.fastDeleteElement(ids.value, $ids);
        getList();
        proxy.$modal.msgSuccess('删除成功');
      })
      .finally(() => {
        proxy.$modal.msgClose(msgLoading);
      });
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download(
    'system/article/export',
    {
      ...queryParams.value,
    },
    `article_${new Date().getTime()}.xlsx`,
  );
}

getList();
</script>
