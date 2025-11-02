package com.example.xiaoxiangshop.demos.util;


//@Component
//public class MyScheduledTask {
//    @Autowired
//    RedisUtils redisUtils;
//    @Autowired
////    ITiziDao tiziDao;
//
//    @Scheduled(fixedDelay = 10000)
//    @Transactional
//    public void executeTask() {
//
//        Set<Object> post =redisUtils.zreverseRange("post", 0, -1);
//        for (Object p : post
//        ) {
//            Double score = redisUtils.zscore("post", p);
////            tiziDao.updateNum((int)p,score.intValue());
//        }
//
////        Set<String> keys = redisUtils.keys("*");
////        if (keys != null) {
////            for (String key : keys
////            ) {
////                if (key.length() < 3) {
////                    Integer o = (Integer) redisUtils.get(key);
////                    if (null != o && o > 0){
////                        tiziDao.updateNum( Integer.parseInt(key), o);
////                    }
////
////                }
////            }
////        }
//    }
//}
