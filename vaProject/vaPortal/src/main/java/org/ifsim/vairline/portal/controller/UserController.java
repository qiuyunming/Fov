package org.ifsim.vairline.portal.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.ifsim.vairline.common.consts.ImgData;
import org.ifsim.vairline.common.consts.WebState;
import org.ifsim.vairline.common.storage.QiniuStorage;
import org.ifsim.vairline.common.storage.ThumbModel;
import org.ifsim.vairline.common.util.ShiroUtil;
import org.ifsim.vairline.common.util.WebUtil;
import org.ifsim.vairline.core.article.domain.Article;
import org.ifsim.vairline.core.article.domain.ArticleAndTendencyDto;
import org.ifsim.vairline.core.article.domain.ArticleComment;
import org.ifsim.vairline.core.article.domain.ArticleTendency;
import org.ifsim.vairline.core.article.service.IArticleCommentService;
import org.ifsim.vairline.core.article.service.IArticleService;
import org.ifsim.vairline.core.article.service.IArticleTendencyService;
import org.ifsim.vairline.core.company.domain.CompanyStaff;
import org.ifsim.vairline.core.company.service.ICompanyStaffService;
import org.ifsim.vairline.core.flight.domain.Flight;
import org.ifsim.vairline.core.flight.domain.FlightPlanDto;
import org.ifsim.vairline.core.flight.service.IFlightService;
import org.ifsim.vairline.core.user.domain.User;
import org.ifsim.vairline.core.user.service.IUserRoleService;
import org.ifsim.vairline.core.user.service.IUserService;
import org.ifsim.vairline.web.auth.CipherUtil;
import org.ifsim.vairline.web.auth.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IFlightService flightService;

    @Autowired
    private ICompanyStaffService companyStaffService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private IArticleTendencyService articleTendencyService;

    @Autowired
    private IArticleCommentService articleCommentService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private WebState webState;

    /**
     * @Description: 用户档案
     */
    @RequestMapping("/user_info")
    public String userCenter(Map<String, Object> view) {
        // 设置当前用户
        view.put("currentUser", userService.getCurrentUser());

        // 获取当前用户的航班
        Flight flight = new Flight();
        flight.setPilotUsername(CurrentUser.getUser().getUsername());
        List<FlightPlanDto> FlightPlanDtoList = flightService.getByFlightAndPlan(flight, null);

        view.put("FlightPlanDtoList", FlightPlanDtoList);

        return "user/center";
    }

    /**
     * @Description: 消息中心
     */
    @RequestMapping("/message")
    public String message(Map<String, Object> view) {

        // 获取当前用户的招募邀请
        CompanyStaff staffEntity = new CompanyStaff();
        staffEntity.setIsInvited(true);
        staffEntity.setUsername(CurrentUser.getUser().getUsername());
        List<CompanyStaff> invitedList = companyStaffService.getCompanyStaff(staffEntity);
        view.put("invitedList", invitedList);

        // 获取当前用户收到的评论
        ArticleComment commentEntity = new ArticleComment();
        commentEntity.setTargetUsername(CurrentUser.getUser().getUsername());
        List<ArticleComment> commentList = articleCommentService.get(commentEntity);
        view.put("commentList", commentList);

        ArticleTendency tendencyEntity = new ArticleTendency();
        tendencyEntity.setTargetUsername(CurrentUser.getUser().getUsername());

        // 评论的id
        Integer ids[] = new Integer[commentList.size()];
        for (int i = 0; i < commentList.size(); i++) {
            ids[i] = commentList.get(i).getId();
        }

        if (ids.length > 0) {
            commentEntity = new ArticleComment();
            // 设置评论为已查看
            commentEntity.setIsViewed(true);
            articleCommentService.updateByIdRange(commentEntity, ids);
        }
        // 获取所有的喜欢，包括文章和评论
        tendencyEntity = new ArticleTendency();
        tendencyEntity.setTargetUsername(CurrentUser.getUser().getUsername());
        tendencyEntity.setType(0);
        List<ArticleAndTendencyDto> likelist = articleCommentService.getByTendency(tendencyEntity);
        likelist.addAll(articleService.getByTendency(tendencyEntity));
        Collections.sort(likelist, new ArticleAndTendencyDtoComparator());
        view.put("likelist", likelist);

        // 查找没有被查看过的赞
        tendencyEntity.setIsViewed(false);
        view.put("countOfLike", articleTendencyService.getCount(tendencyEntity));

        return "user/message";
    }

    /**
     * @Description: 同意或拒绝加入某公司
     */
    @RequestMapping("/update_state")
    public @ResponseBody WebState updateState(CompanyStaff companyStaff) {

        CompanyStaff targetStaff = companyStaffService.getCompanyStaffById(companyStaff);
        // 判断当前操作是否为当前用户
        if (targetStaff.getUsername().equals(CurrentUser.getUser().getUsername()) && targetStaff.getIsInvited()) {
            companyStaffService.updateCompanyStaff(companyStaff);
            // 若是已通过邀请，则添加“飞行员”身份
            if (targetStaff.getState() != null && targetStaff.getState() == 1) {
                userRoleService.createOrUpdate(companyStaff.getUsername(), "company_post", 19);
                userRoleService.createOrUpdate(companyStaff.getUsername(), "company_level", 9);
            }
            webState.setDesc("操作成功");
        } else {
            webState.setDesc("操作失败");
        }
        return webState;
    }

    /**
     * @Description: 修改头像
     */
    @RequestMapping("/update_photo")
    public @ResponseBody ImgData updatePhoto(@RequestParam MultipartFile photo) {
        ImgData imgData = new ImgData();
        try {
            if (photo.getBytes().length > 0) {
                String key = QiniuStorage.uploadImage(photo.getBytes());
                User currentUser = new User();
                currentUser.setId(CurrentUser.getUser().getId());
                currentUser.setPhoto(key);
                userService.updateUser(currentUser);
                imgData.setUrl(QiniuStorage.getUrl(key, ThumbModel.THUMB_256));
                imgData.setSuccess(true);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgData;
    }

    /**
     * @Description: 修改用户信息
     */
    @RequestMapping("/update_user")
    public @ResponseBody WebState updateUser(User currentUser, String newPassword) {
        if (currentUser.getPassword() == null || currentUser.getPassword().trim().equals("") || newPassword == null || newPassword.trim().equals("")) {
            webState.setDesc("fail");
            return webState;
        }

        if (CurrentUser.getUser().getPassword().equals(CipherUtil.generatePassword(newPassword.trim()))) {
            currentUser.setId(CurrentUser.getUser().getId());
            currentUser.setPassword(CipherUtil.generatePassword(newPassword.trim()));
            userService.updateUser(currentUser);
            webState.setDesc("success");
        } else {
            webState.setDesc("fail");
        }
        return webState;
    }

    /**
     * @Description: 获取用户文章
     */
    @RequestMapping("/user_article")
    public String userArticle(Map<String, Object> view) {
        // 设置当前用户
        view.put("currentUser", userService.getCurrentUser());

        Article articleEntity = new Article();
        articleEntity.setAuthor(CurrentUser.getUser().getUsername());
        // 获取此类型的所有文章
        List<Article> articleList = articleService.get(articleEntity);

        view.put("articleList", articleList);
        return "user/user_article";
    }

    /**
     * @Description: 删除文章
     */
    @RequestMapping("/delete_article")
    public @ResponseBody WebState deleteArticle(Article targetArticle) {
        targetArticle = articleService.getById(targetArticle);
        if (targetArticle != null && targetArticle.getAuthor().equals(CurrentUser.getUser().getUsername())) {
            articleService.delete(targetArticle);
            webState.setDesc("删除成功");
        } else {
            webState.setDesc("删除失败");
        }
        return webState;
    }

    /**
     * @Description: 文章编辑器
     */
    @RequestMapping("/update_article/{id}")
    public String updateArticle(@PathVariable Integer id, Map<String, Object> view) {
        Article targetArticle = new Article();
        targetArticle.setId(id);
        targetArticle = articleService.getById(targetArticle);
        targetArticle.setContent(WebUtil.unEncodeHtml(targetArticle.getContent()));
        view.put("article", targetArticle);
        return "user/article_updater";
    }

    /**
     * @Description: 更新文章page
     */
    @RequestMapping("/do_update_article")
    public String doUpdateArticle(Article newArticle) {
        Article oldArticle = articleService.getById(newArticle);
        if (oldArticle.getAuthor().equals(CurrentUser.getUser().getUsername())) {
            newArticle.setHead(CurrentUser.getUser().getPhoto());
            newArticle.setContentText(WebUtil.delHtmlTag(newArticle.getContent()));
            articleService.update(newArticle);
        } else {
            return "error";
        }
        return "redirect:/user/user_article";
    }

    /**
     * @Description: 用户收藏
     */
    @RequestMapping("/user_collection")
    public String userCollection(Map<String, Object> view) {
        // 设置当前用户
        view.put("currentUser", userService.getCurrentUser());

        ArticleTendency tendencyEntity = new ArticleTendency();
        tendencyEntity.setUsername(CurrentUser.getUser().getUsername());
        tendencyEntity.setType(1);
        List<ArticleAndTendencyDto> collections = articleService.getByTendency(tendencyEntity);
        view.put("collections", collections);

        return "user/user_collection";
    }

    /**
     * @Description: 删除趋势
     */
    @RequestMapping("/delete_tendency")
    public @ResponseBody WebState deleteTendency(ArticleTendency targetTendency) {
        targetTendency = articleTendencyService.getById(targetTendency);
        if (targetTendency != null && targetTendency.getUsername().equals(CurrentUser.getUser().getUsername())) {
            articleTendencyService.delete(targetTendency);
            webState.setDesc("删除成功");
        } else {
            webState.setDesc("删除失败");
        }
        return webState;
    }

    /**
     * @Description: 一个文章评论比较器
     * @author shentong
     * @date 2018年1月23日 下午4:08:31
     * @version V1.0
     */
    private class ArticleAndTendencyDtoComparator implements Comparator<ArticleAndTendencyDto> {

        @Override
        public int compare(ArticleAndTendencyDto o1, ArticleAndTendencyDto o2) {
            if (o2.getArticleTendency().getCreateTime().getTime() - o1.getArticleTendency().getCreateTime().getTime() > 0) {
                return 1;
            } else if (o2.getArticleTendency().getCreateTime().getTime() - o1.getArticleTendency().getCreateTime().getTime() < 0) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    /**
     * @Description: 查看喜欢
     */
    @RequestMapping("/view_like")
    public @ResponseBody WebState viewLike() {
        ArticleTendency tendencyEntity = new ArticleTendency();
        tendencyEntity.setTargetUsername(CurrentUser.getUser().getUsername());
        tendencyEntity.setType(0);
        tendencyEntity.setIsViewed(false);
        List<ArticleTendency> likeList = articleTendencyService.get(tendencyEntity);
        Integer ids[] = new Integer[likeList.size()];
        for (int i = 0; i < likeList.size(); i++) {
            ids[i] = likeList.get(i).getId();
        }
        if (ids.length > 0) {
            // 将喜欢设置为已查看
            tendencyEntity.setIsViewed(true);
            articleTendencyService.updateByIdRange(tendencyEntity, ids);
            webState.setDesc("success");
        } else {
            webState.setDesc("false");
        }
        return webState;
    }
}
