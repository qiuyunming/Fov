package org.ifsim.vairline.core.article.service;

import java.util.List;

import org.ifsim.vairline.core.article.domain.ArticleType;

public interface IArticleTypeService {
	void insert(ArticleType articleType);

	void delete(ArticleType articleType);

	void update(ArticleType articleType);

	List<ArticleType> get(ArticleType articleType);

	ArticleType getById(ArticleType articleType);
}
