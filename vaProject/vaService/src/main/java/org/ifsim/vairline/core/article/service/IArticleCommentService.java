package org.ifsim.vairline.core.article.service;

import java.util.List;

import org.ifsim.vairline.core.article.domain.ArticleAndTendencyDto;
import org.ifsim.vairline.core.article.domain.ArticleComment;
import org.ifsim.vairline.core.article.domain.ArticleCommentGroupDto;
import org.ifsim.vairline.core.article.domain.ArticleTendency;

public interface IArticleCommentService {
	void insert(ArticleComment articleComment);

	void delete(ArticleComment articleComment);

	void update(ArticleComment articleComment);

	List<ArticleComment> get(ArticleComment articleComment);

	ArticleComment getById(ArticleComment articleComment);
	
	/** 
	* @Description: 通过文章id获取评论组 
	*/
	List<ArticleCommentGroupDto> getCommentGroup(Integer articleId);
	
	/** 
	* @Description: 通过评论和用户获取评论组 
	*/
	List<ArticleCommentGroupDto> getCommentGroupByCommentAndUser(Integer articleId,String username);
	
	/** 
	* @Description: 获取评论数 
	*/
	Integer getCount(ArticleComment articleComment);
	
	/**
	 * @Description: 通过id数组批量修改
	 */
	void updateByIdRange(ArticleComment articleComment,Integer[] ids);
	
	/**
	 * @Description: 通过趋势获取评论
	 */
	List<ArticleAndTendencyDto> getByTendency(ArticleTendency articleTendency);
}
