package com.hzhang;

import com.hzhang.dao.profile.BlogManageDao;
import com.hzhang.service.profile.BlogManageService;
import com.hzhang.service.TypeService;
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
    private BlogManageDao blogManageDao;

    @Autowired
    private BlogManageService blogManageService;

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
//        PageInfo<Blog> blogList = blogManageService.findBlogList(queryVo.getCurrentPage(), queryVo.getPageSize(), queryVo);
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

//        Blog blogById = blogManageService.findBlogById(1L);
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
}
