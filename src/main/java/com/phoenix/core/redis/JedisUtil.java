package com.phoenix.core.redis;


import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class JedisUtil {
    protected static final Logger logger = LoggerFactory.getLogger(JedisUtil.class);
    private static JedisPool jedisPool = null;

    public JedisUtil(JedisPool pool) {
        if (jedisPool == null) {
            Class var2 = JedisPool.class;
            synchronized(JedisPool.class) {
                if (jedisPool == null) {
                    jedisPool = pool;
                }
            }
        }

    }

    public static void destroy() {
        System.out.println("redis destroy");

        try {
            jedisPool.close();
        } catch (Exception var1) {
            var1.printStackTrace();
        }

        jedisPool = null;
    }

    public static boolean exists(String key) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            boolean var4 = jedis.exists(key);
            return var4;
        } catch (Exception var7) {
            logger.error("key: " + key + "; error:" + var7.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return false;
    }

    public static String set(String key, String value) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            String result = jedis.set(key, value);
            String var5 = result;
            return var5;
        } catch (Exception var8) {
            logger.error("key: " + key + "; error:" + var8.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return null;
    }

    public static String set(String key, String value, int timeOut) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            String var6 = jedis.setex(key, timeOut, value);
            return var6;
        } catch (Exception var9) {
            logger.error("key: " + key + "; error:" + var9.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return null;
    }

    public static Long decrBy(String key, long value) {
        int times = 0;

        Long result;
        for(result = null; (result = decrByvalue(key, value)) == null && times < 10; ++times) {
        }

        return result;
    }

    public static Long incrBy(String key, long value) {
        int times = 0;

        Long result;
        for(result = null; (result = incrByvalue(key, value)) == null && times < 10; ++times) {
        }

        return result;
    }

    public static Long hincrBy(String key, String field, long value) {
        int times = 0;

        Long result;
        for(result = null; (result = hincrByvalue(key, field, value)) == null && times < 10; ++times) {
        }

        return result;
    }

    private static Long hincrByvalue(String key, String field, long value) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            jedis.watch(new String[]{key});
            Transaction trans = jedis.multi();
            Response<Long> rsp = trans.hincrBy(key, field, value);
            List<Object> list = trans.exec();
            if (list != null && !list.isEmpty()) {
                jedis.unwatch();
                Long var9 = (Long)rsp.get();
                return var9;
            }

            return null;
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return null;
    }

    private static Long incrByvalue(String key, long value) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            jedis.watch(new String[]{key});
            Transaction trans = jedis.multi();
            Response<Long> rsp = trans.incrBy(key, value);
            List<Object> list = trans.exec();
            if (list != null && !list.isEmpty()) {
                jedis.unwatch();
                Long var8 = (Long)rsp.get();
                return var8;
            }

            return null;
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return null;
    }

    private static Long decrByvalue(String key, long value) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            jedis.watch(new String[]{key});
            Transaction trans = jedis.multi();
            Response<Long> rsp = trans.decrBy(key, value);
            List<Object> list = trans.exec();
            if (list != null && !list.isEmpty()) {
                jedis.unwatch();
                Long var8 = (Long)rsp.get();
                return var8;
            }

            return null;
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return null;
    }

    public static String get(String key) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            String var4 = jedis.get(key);
            return var4;
        } catch (Exception var7) {
            logger.error("key: " + key + "; error:" + var7.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return null;
    }

    public static Set<String> getKesByPattern(String pattern) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            Set var4 = jedis.keys(pattern);
            return var4;
        } catch (Exception var7) {
            logger.error("pattern: " + pattern + "; error:" + var7.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return null;
    }

    public static void delete(String key) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception var6) {
            logger.error("key: " + key + "; error:" + var6.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

    }

    public static long getTimeOutByKey(String key) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            long var4 = jedis.ttl(key);
            return var4;
        } catch (Exception var8) {
            logger.error("key: " + key + "; error:" + var8.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return 0L;
    }

    public static long refreshLiveTime(String key, int timeOut) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            long var5 = jedis.expire(key, timeOut);
            return var5;
        } catch (Exception var9) {
            logger.error("key: " + key + "; error:" + var9.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return 0L;
    }
}

