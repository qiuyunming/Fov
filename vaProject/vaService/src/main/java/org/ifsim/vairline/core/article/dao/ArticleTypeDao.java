package org.ifsim.vairline.core.article.dao;

import java.util.List;

import org.ifsim.vairline.core.article.domain.ArticleType;
import org.springframework.stereotype.Service;

public interface ArticleTypeDao {
	void insert(ArticleType articleType);

	void delete(ArticleType articleType);

	void update(ArticleType articleType);

	List<ArticleType> get(ArticleType articleType);

	ArticleType getById(ArticleType articleType);
}
