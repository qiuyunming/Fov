package org.ifsim.vairline.core.article.service.impl;

import java.util.List;

import org.ifsim.vairline.core.article.dao.ArticleTendencyDao;
import org.ifsim.vairline.core.article.domain.ArticleTendency;
import org.ifsim.vairline.core.article.service.IArticleTendencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
@Service
@Cacheable("ArticleTendency")
public class ArticleTendencyServiceImpl implements IArticleTendencyService {

	@Autowired
	private ArticleTendencyDao articleTendencyDao;

	@Override
	@CacheEvict(value= {"ArticleTendency","ArticleComment"},allEntries=true)
	public void insert(ArticleTendency articleTendency) {
		articleTendencyDao.insert(articleTendency);
	}

	@Override
	@CacheEvict(value= {"ArticleTendency","ArticleComment"},allEntries=true)
	public void delete(ArticleTendency articleTendency) {
		articleTendencyDao.delete(articleTendency);
	}

	@Override
	@CacheEvict(value= {"ArticleTendency","ArticleComment"},allEntries=true)
	public void update(ArticleTendency articleTendency) {
		articleTendencyDao.update(articleTendency);
	}

	@Override
	public List<ArticleTendency> get(ArticleTendency articleTendency) {
		return articleTendencyDao.get(articleTendency);
	}

	@Override
	public ArticleTendency getById(ArticleTendency articleTendency) {
		return articleTendencyDao.getById(articleTendency);
	}

	@Override
	public Integer getCount(ArticleTendency articleTendency) {
		return articleTendencyDao.getCount(articleTendency);
	}

	@Override
	@CacheEvict(value= {"ArticleTendency","ArticleComment"},allEntries=true)
	public void updateByIdRange(ArticleTendency articleTendency, Integer[] ids) {
		articleTendencyDao.updateByIdRange(articleTendency, ids);
	}

}
