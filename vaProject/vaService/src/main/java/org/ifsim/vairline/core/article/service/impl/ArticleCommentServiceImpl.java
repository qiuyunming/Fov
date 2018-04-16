package org.ifsim.vairline.core.article.service.impl;

import java.util.List;

import org.ifsim.vairline.core.article.dao.ArticleCommentDao;
import org.ifsim.vairline.core.article.domain.ArticleAndTendencyDto;
import org.ifsim.vairline.core.article.domain.ArticleComment;
import org.ifsim.vairline.core.article.domain.ArticleCommentGroupDto;
import org.ifsim.vairline.core.article.domain.ArticleTendency;
import org.ifsim.vairline.core.article.service.IArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Cacheable(value = "ArticleComment")
public class ArticleCommentServiceImpl implements IArticleCommentService {

	@Autowired
	private ArticleCommentDao articleCommentDao;

	@Override
	@CacheEvict(value = { "ArticleComment" }, allEntries = true)
	public void insert(ArticleComment articleComment) {
		articleCommentDao.insert(articleComment);
	}

	@Override
	@CacheEvict(value = { "ArticleComment" }, allEntries = true)
	public void delete(ArticleComment articleComment) {
		articleCommentDao.delete(articleComment);
	}

	@Override
	@CacheEvict(value = { "ArticleComment" }, allEntries = true)
	public void update(ArticleComment articleComment) {
		articleCommentDao.update(articleComment);
	}

	@Override
	public List<ArticleComment> get(ArticleComment articleComment) {
		return articleCommentDao.get(articleComment);
	}

	@Override
	public ArticleComment getById(ArticleComment articleComment) {
		return articleCommentDao.getById(articleComment);
	}

	@Override
	public List<ArticleCommentGroupDto> getCommentGroup(Integer articleId) {
		return articleCommentDao.getCommentGroup(articleId);
	}

	@Override
	public List<ArticleCommentGroupDto> getCommentGroupByCommentAndUser(Integer articleId, String username) {
		return articleCommentDao.getCommentGroupByCommentAndUser(articleId, username);
	}

	@Override
	public Integer getCount(ArticleComment articleComment) {
		return articleCommentDao.getCount(articleComment);
	}

	@Override
	@CacheEvict(value = { "ArticleComment" }, allEntries = true)
	public void updateByIdRange(ArticleComment articleComment, Integer[] ids) {
		articleCommentDao.updateByIdRange(articleComment, ids);
	}

	@Override
	public List<ArticleAndTendencyDto> getByTendency(ArticleTendency articleTendency) {
		return articleCommentDao.getByTendency(articleTendency);
	}

}
