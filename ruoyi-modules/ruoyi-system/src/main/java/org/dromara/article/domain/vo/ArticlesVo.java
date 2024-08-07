package org.dromara.article.domain.vo;

import lombok.Data;

@Data
public class ArticlesVo {
    private ArticleShort next;
    private ArticleVo cur;
    private ArticleShort pre;
}
