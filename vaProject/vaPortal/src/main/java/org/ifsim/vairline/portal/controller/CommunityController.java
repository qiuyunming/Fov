package org.ifsim.vairline.portal.controller;

import java.util.List;
import java.util.Map;

import org.ifsim.vairline.common.consts.WebState;
import org.ifsim.vairline.common.page.Page;
import org.ifsim.vairline.common.storage.QiniuStorage;
import org.ifsim.vairline.common.storage.ThumbModel;
import org.ifsim.vairline.common.util.WebUtil;
import org.ifsim.vairline.core.article.domain.Article;
import org.ifsim.vairline.core.article.domain.ArticleComment;
import org.ifsim.vairline.core.article.domain.ArticleTendency;
import org.ifsim.vairline.core.article.domain.ArticleType;
import org.ifsim.vairline.core.article.service.IArticleCommentService;
import org.ifsim.vairline.core.article.service.IArticleService;
import org.ifsim.vairline.core.article.service.IArticleTendencyService;
import org.ifsim.vairline.core.article.service.IArticleTypeService;
import org.ifsim.vairline.core.statics.domain.UserRankDto;
import org.ifsim.vairline.core.statics.service.IUserRankDtoService;
import org.ifsim.vairline.core.user.domain.User;
import org.ifsim.vairline.core.user.service.IUserService;
import org.ifsim.vairline.portal.vo.TendencyVO;
import org.ifsim.vairline.web.auth.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 社区
 * @author shentong
 * @date 2018年1月7日 下午3:43:19
 * @version V1.0
 */
@Controller
@RequestMapping("/community")
public class CommunityController {

	@Autowired
	private IArticleTypeService articleTypeService;

	@Autowired
	private IArticleService articleService;

	@Autowired
	private IArticleCommentService articleCommentService;

	@Autowired
	private IArticleTendencyService articleTendencyService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IUserRankDtoService userRankDtoService;

	@Autowired
	private WebState webState;

	/**
	 * @Description: 社区中心
	 */
	@RequestMapping("/center")
	public String center(Map<String, Object> view) {
		view.put("typeList", articleTypeService.get(null));
		List<UserRankDto> userRankList = userRankDtoService.get(0, 5);
		for (UserRankDto item : userRankList) {
			item.setPhoto(QiniuStorage.getUrl(item.getPhoto(), ThumbModel.THUMB_48));
		}
		view.put("userRankList", userRankList);

		// 获取所有文章
		List<Article> articleList = articleService.get(null);

		for (Article item : articleList) {
			item.setContent(WebUtil.unEncodeHtml2(item.getContent()));
		}
		view.put("articleList", articleList);
		return "community/center";
	}

	/**
	 * @Description: 类型
	 */
	@RequestMapping("/{typeId}/{pageNum}")
	public String type(@PathVariable Integer typeId, @PathVariable Integer pageNum, Map<String, Object> view) {
		ArticleType currentArticleType = new ArticleType();
		currentArticleType.setId(typeId);
		currentArticleType = articleTypeService.getById(currentArticleType);
		Article articleEntity = new Article();
		articleEntity.setTypeId(typeId);
		// 获取此类型的所有文章
		// List<Article> articleList = articleService.get(articleEntity);
		Page<Article> page = articleService.getByPage(articleEntity, pageNum, 5);
		view.put("type", currentArticleType);
		view.put("page", page);
		return "community/type";
	}

	/**
	 * @Description: 编辑器
	 */
	@RequestMapping("/writer/{typeId}")
	public String writer(@PathVariable Integer typeId, Map<String, Object> view) {
		view.put("typeId", typeId);
		return "community/writer";
	}

	/**
	 * @Description: 文章提交
	 */
	@RequestMapping("/doWrite")
	public String doWrite(Article article) {
		if (article.getContent() != null && article.getTitle() != null && !article.getTitle().trim().equals("")
				&& !article.getContent().trim().equals("")) {
			article.setAuthor(CurrentUser.getUser().getUsername());
			article.setHead(CurrentUser.getUser().getPhoto());
			articleService.insert(article);
			webState.setDesc("操作成功");
		} else {
			webState.setDesc("操作失败");
		}
		return "redirect:/community/" + article.getTypeId() + "/1";
	}

	/**
	 * @Description: 文章详情页
	 */
	@RequestMapping("/article/{articleId}")
	public String articleDetail(@PathVariable Integer articleId, Map<String, Object> view) {
		// 文章
		Article targetArticle = new Article();
		targetArticle.setId(articleId);
		targetArticle = articleService.getById(targetArticle);

		if (targetArticle == null) {
			return "error";
		}
		targetArticle.setContent(WebUtil.unEncodeHtml(targetArticle.getContent()));
		view.put("article", targetArticle);

		String username = null;
		if (CurrentUser.getUser() != null) {
			username = CurrentUser.getUser().getUsername();
		}
		// 所有评论
		view.put("commentList", articleCommentService.getCommentGroupByCommentAndUser(articleId, username));

		// 喜欢和收藏趋势
		TendencyVO articleLikeVO = new TendencyVO();
		TendencyVO articlecollectVO = new TendencyVO();

		ArticleTendency currentArticleTendencyEntity = new ArticleTendency();
		currentArticleTendencyEntity.setTargetId(articleId);
		currentArticleTendencyEntity.setIsComment(false);
		currentArticleTendencyEntity.setType(0);

		articleLikeVO.setCount(articleTendencyService.getCount(currentArticleTendencyEntity));
		currentArticleTendencyEntity.setUsername(username);
		articleLikeVO.setIsExisted(articleTendencyService.getCount(currentArticleTendencyEntity));
		view.put("articleLikeVO", articleLikeVO);

		currentArticleTendencyEntity.setType(1);
		articlecollectVO.setIsExisted(articleTendencyService.getCount(currentArticleTendencyEntity));
		view.put("articlecollectVO", articlecollectVO);

		// 当前用户
		User currentUser = CurrentUser.getUser();
		if(currentUser!=null) {
			currentUser = userService.getUserById(currentUser);
			currentUser.setPhoto(QiniuStorage.getUrl(currentUser.getPhoto(), ThumbModel.THUMB_48));
			view.put("currentUser", currentUser);
		}
		return "community/article";
	}

	/**
	 * @Description: 提交评论
	 */
	@RequestMapping("/submit_comment")
	public @ResponseBody ArticleComment submitComment(ArticleComment newArticleComment, Map<String, Object> view) {

		if (newArticleComment.getArticleId() != null && newArticleComment.getContent() != null
				&& ((newArticleComment.getTargetUsername() != null && newArticleComment.getParentId() == 0)
						|| (newArticleComment.getTargetUsername() != null && newArticleComment.getParentId() != 0
								&& !newArticleComment.getTargetUsername()
										.equals(CurrentUser.getUser().getUsername())))) {

			newArticleComment.setUsername(CurrentUser.getUser().getUsername());
			newArticleComment.setPhoto(CurrentUser.getUser().getPhoto());
			newArticleComment.setIsViewed(false);
			articleCommentService.insert(newArticleComment);
			return newArticleComment;
		} else {
			return null;
		}
	}

	/**
	 * @Description: 删除评论
	 */
	@RequestMapping("/delete_comment")
	public @ResponseBody WebState deleteComment(ArticleComment targetComment, Map<String, Object> view) {
		targetComment = articleCommentService.getById(targetComment);
		if (targetComment.getUsername().equals(CurrentUser.getUser().getUsername())) {
			articleCommentService.delete(targetComment);
			webState.setDesc("删除成功");
		} else {
			webState.setDesc("删除失败");
		}
		return webState;
	}

	/**
	 * @Description: 趋向（文章、评论）
	 */
	@RequestMapping("/do_tendency")
	public @ResponseBody WebState doTendency(ArticleTendency newArticleTendency) {

		if (newArticleTendency.getTargetId() == null || newArticleTendency.getType() == null) {
			return null;
		}
		newArticleTendency.setUsername(CurrentUser.getUser().getUsername());
		List<ArticleTendency> tendencyList = articleTendencyService.get(newArticleTendency);
		// 增加和取消
		if (tendencyList.isEmpty() && newArticleTendency.getTargetId() != null
				&& newArticleTendency.getType() != null) {
			newArticleTendency.setPhoto(CurrentUser.getUser().getPhoto());
			articleTendencyService.insert(newArticleTendency);
			webState.setDesc("create");
		} else {
			articleTendencyService.delete(tendencyList.get(0));
			webState.setDesc("cancle");
		}

		return webState;
	}

}
