

package hadoop.whiffet.impala;

import java.io.IOException;
import java.io.Reader;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;





/** 
 * @author  puppy 
 * @date 2015年7月1日 上午11:27:10 
 * 
 */
public class TestMyBatis {  
    
    private static  SqlSessionFactory sqlSessionFactory;  
    private static Reader reader;   
  
    public static void main(String[] args) {
    	 try {  
             reader  = Resources.getResourceAsReader("back/mybatis-config.xml");  
             sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);  
         } catch (IOException e) {  
             e.printStackTrace();  
         }  
    	 SqlSession session = sqlSessionFactory.openSession();  
         try {  
//             List<GitvLogOriginal> list =  session.selectList("GitvLogOriginal.select");  
//             for(GitvLogOriginal glo:list){
//            	 System.out.println(glo.getIp());
//             }
           
         } finally {  
             session.close();  
         }  
	}
//    @Before  
//    public void init(){  
//        try {  
//            reader  = Resources.getResourceAsReader("mybatis-config.xml");  
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
//    }  
//      
//    @Test  
//    public void testQueryUser(){  
//        SqlSession session = sqlSessionFactory.openSession();  
//        try {  
//            GitvLogOriginal user = (GitvLogOriginal) session.selectOne("GitvLogOriginal.select");  
//            System.out.println(user);
//            System.out.println(user.getIp());  
//          
//        } finally {  
//            session.close();  
//        }  
//    }  
      
//    @Test  
//    public void testInsertUser(){  
//        SqlSession session = sqlSessionFactory.openSession();  
//        try {  
//            User user = new User() ;  
//            user.setUserName("abcde");  
//            user.setUserAge(15) ;  
//            user.setUserAddress("hangzhou/zhejiang");  
//            user.setPassword("123456");  
//            //返回值是记录条数  
//            int resultCount = session.insert("com.zf.mybatis.pojo.UserMapper.add", user );  
//            session.commit() ;  
//            System.out.printf("userID:%d,总记录条数:%d" , user.getId() , resultCount);  //获取插入对象的id  
//        } finally {  
//            session.close();  
//        }  
//    }  
//      
//    @Test  
//    public void testUpdateUser(){  
//        SqlSession session = sqlSessionFactory.openSession();  
//        try {  
//            User user = new User() ;  
//            user.setId(5) ;  
//            user.setUserName("updateName");  
//            user.setUserAge(101) ;  
//            user.setUserAddress("shenzhen/guangdong");  
//            user.setPassword("000000");  
//            //返回值是修改条数  
//            int updateCount = session.update("com.zf.mybatis.pojo.UserMapper.update", user );  
//            session.commit() ;  
//            System.out.printf("修改条数:%d" ,updateCount);    
//        } finally {  
//            session.close();  
//        }  
//    }  
//      
//    @Test  
//    public void testDelete(){  
//        SqlSession session = sqlSessionFactory.openSession();  
//        try {  
//            //返回值是删除条数  
//            int deleteCount = session.update("com.zf.mybatis.pojo.UserMapper.deleteById", 4 );  
//            session.commit() ;  
//            System.out.printf("删除条数:%d" ,deleteCount );    
//        } finally {  
//            session.close();  
//        }  
//    }  
//      
      
      
      
      
  
}  
