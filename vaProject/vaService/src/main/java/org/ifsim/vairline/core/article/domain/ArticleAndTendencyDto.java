package org.ifsim.vairline.core.article.domain;

import java.io.Serializable;

/**
 * @Description: 文章/评论和趋势
 * @author shentong
 * @date 2018年1月16日 下午3:46:22
 * @version V1.0
 */
public class ArticleAndTendencyDto implements Serializable{

	/**
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;

	private ArticleTendency articleTendency;

	private Article article;

	private ArticleComment articleComment;

	public ArticleComment getArticleComment() {
		return articleComment;
	}

	public void setArticleComment(ArticleComment articleComment) {
		this.articleComment = articleComment;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public ArticleTendency getArticleTendency() {
		return articleTendency;
	}

	public void setArticleTendency(ArticleTendency articleTendency) {
		this.articleTendency = articleTendency;
	}

	@Override
	public String toString() {
		return "ArticleAndTendencyDto [article=" + article + ", articleTendency=" + articleTendency + "]";
	}

}
