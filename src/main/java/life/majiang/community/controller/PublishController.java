package life.majiang.community.controller;

import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            Model model){

        //如果页面中没有输入问题标题
        if (title == null || title.equals("")){
            model.addAttribute("error","标题不能为空");  //返回错误信息
            return "publish";
        }
        //如果有标题，存入model中，用于回显
        model.addAttribute("title",title);

        //同上
        if (description == null || description.equals("")){
            model.addAttribute("error","问题详细描述不能为空");
            return "publish";
        }
        model.addAttribute("description",description);

        //同上
        if (tag == null || tag.equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        model.addAttribute("tag",tag);

        //从session获取user
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {  //如果user==null，说明未登录
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);

        question.setCreator(user.getId());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_create());
        questionMapper.create(question); //在数据库中添加问题
        return "redirect:/";  //返回首页，添加问题
    }
}
