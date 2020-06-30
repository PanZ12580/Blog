package com.hzhang;

import com.hzhang.dao.BlogDao;
import com.hzhang.dao.BlogTagDao;
import com.hzhang.dao.TagDao;
import com.hzhang.pojo.*;
import com.hzhang.service.BlogService;
import com.hzhang.service.TagService;
import com.hzhang.service.TypeService;
import com.hzhang.service.UVStatisticService;
import com.hzhang.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;

@SpringBootTest
class MyBlogApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private TypeService typeService;

    @Autowired
    private RedisUtil redisUtil;

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

    @Autowired
    private UVStatisticService uvStatisticService;

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
//        List<Blog> searchBlog = blogDao.findSearchBlog("哈哈");
//        System.out.println(searchBlog.size());
//        String s = "大大";
//        char a = s.charAt(0), b = s.charAt(1);
//        System.out.println(a + "==============>" +  b);
//        Long pfadd = redisUtil.pfadd("1", "127.4.2.1");
//        System.out.println(pfadd);
//        System.out.println(redisUtil.pfcount("1"));
//        PageInfo<Blog> homeBlogList = blogService.findHomeBlogList(1, 10);
//        homeBlogList.getList().forEach(item -> {item.setViews(100L);});
//        System.out.println(homeBlogList.getList().get(0).getViews());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//        List<Blog> blogList = blogDao.findArchivesBlogList();
//        List<String> collect = blogList.stream().map(b -> sdf.format(b.getCreateTime())).collect(Collectors.toList());
//        Long t = 121313212L;
//        System.out.println(blogList.get(0).getCreateTime());
//        System.out.println(collect);
//        Map<String, Object> archivesBlogList = blogService.findArchivesBlogMap();
//        archivesBlogList.forEach((k, v) -> {
//            System.out.println(k + "====>" + v);
//        });
//        System.out.println(blogTagByTagId);
//        blogTagByTagId.forEach(x -> {
//            System.out.println();
//        });
    }

    @Test
    public void testUV() {
        UVStatistic statistic = uvStatisticService.getStatistic(7);
        System.out.println(statistic);
    }
}
