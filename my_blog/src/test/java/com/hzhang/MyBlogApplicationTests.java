package com.hzhang;

import com.hzhang.dao.BlogDao;
import com.hzhang.dao.BlogTagDao;
import com.hzhang.dao.TagDao;
import com.hzhang.pojo.Blog;
import com.hzhang.pojo.BlogTag;
import com.hzhang.pojo.Tag;
import com.hzhang.pojo.Type;
import com.hzhang.service.BlogService;
import com.hzhang.service.TagService;
import com.hzhang.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;

import java.util.List;

@SpringBootTest
class MyBlogApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private BlogTagDao blogTagDao;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("token", "1111111");
        System.out.println(redisTemplate.opsForValue().get("token"));
    }
    @Test
    public void testAOP(){
        String password = "123456";
        System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));
    }

    @Test
    public void testBlogManage() throws NoSuchMethodException {
//        List<Blog> blogList = blogManageDao.findBlogList();
//        System.out.println(blogList.get(0).getTitle());
        /*Blog blog = new Blog();
        blog.setAppreciation(true);
        blog.setTitle("测试");
        blog.setContent("哈哈哈哈哈哈哈哈哈");
        blogManageDao.saveBlog(blog);*/
//        Blog blogById = blogManageDao.findBlogById(1L);
//        System.out.println(blogById);
//        BlogManageQueryVo queryVo = new BlogManageQueryVo();
//        PageInfo<Blog> blogList = blogService.findBlogList(queryVo.getCurrentPage(), queryVo.getPageSize(), queryVo);
//        System.out.println(blogList);
//        Class cls = TypeService.class;
//        Method method = cls.getMethod("deleteType", Long.class);
//        Optional<ClearRedisCache> annotation = AnnotationUtils.findAnnotation(method, ClearRedisCache.class);
//        assertTrue(annotation.isPresent());
//        Class<?>[] cascade = annotation.get().cascade();
//        System.out.println(cascade[0].getName());
//        String s = cls.getPackageName() + ".impl." + cls.getSimpleName();
//        System.out.println(s);
        // Creating an integer array

//        Blog blogById = blogService.findBlogById(1L);
//        System.out.println(blogById);
        System.out.println(System.currentTimeMillis());
    }
//    @Test
//    public void testPageHelper() {
//        PageHelper.startPage(1, 10);
//        List<Type> typeList = typeService.findTypeList();
//        PageInfo<Type> pageInfo = new PageInfo<>(typeList);
//        System.out.println(pageInfo);
//    }
//    @Test
//    public void testSQL() {
//        Type type1 = typeService.findType(Long.parseLong("2"));
//        System.out.println(type1);
//    }

    @Test
    public void testBlogTag() {
//        List<BlogTag> blogTagByTagId = blogTagDao.findBlogTagByBlogId(1L);
//        blogTagByTagId.forEach(x -> {
//            System.out.println(x.getBlogId());
//        });
//        Blog blogById = blogDao.findBlogById(1L);
//        blogById.getType();
//        List<Tag> tagList = blogById.getTagList();
//        System.out.println(tagList);
//        List<Type> topTypeList = typeService.findTopTypeList(2);
//        System.out.println(topTypeList);
        List<Blog> searchBlog = blogDao.findSearchBlog("哈哈");
        System.out.println(searchBlog.size());
//        System.out.println(blogTagByTagId);
//        blogTagByTagId.forEach(x -> {
//            System.out.println();
//        });
    }
}
