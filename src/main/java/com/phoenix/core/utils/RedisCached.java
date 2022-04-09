package com.phoenix.core.utils;



import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

public class RedisCached {
    Logger log = LoggerFactory.getLogger(RedisCached.class);
    private JedisPool pool = null;

    public RedisCached(String host, String port, String auth, String timeout) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(-1);
        config.setMinIdle(-1);
        config.setMaxWaitMillis(10000L);
        config.setTestOnBorrow(false);
        this.pool = new JedisPool(config, host, Integer.parseInt(port), Integer.parseInt(timeout), auth);
    }

    public RedisCached(String host, String port) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(-1);
        config.setMinIdle(-1);
        config.setMaxWaitMillis(10000L);
        config.setTestOnBorrow(false);
        this.pool = new JedisPool(config, host, Integer.parseInt(port));
    }

    private <T> Object execute(RedisCached.JedisAction<T> jedisAction) {
        Jedis jedis = null;
        boolean broken = false;

        Object var6;
        try {
            jedis = this.pool.getResource();
            var6 = jedisAction.action(jedis);
        } catch (JedisConnectionException var9) {
            this.log.error("Redis connection lost.", var9);
            broken = true;
            throw var9;
        } finally {
            this.closeResource(jedis, broken);
        }

        return var6;
    }

    public Jedis getJedis() {
        Jedis jedis = null;

        try {
            jedis = this.pool.getResource();
            if (jedis == null) {
                throw new JedisException("获取jedis的对象为空！");
            }
        } catch (JedisConnectionException var3) {
            this.closeResource(jedis, false);
        }

        return jedis;
    }

    public void closeJedis(Jedis jedis) {
        this.closeResource(jedis, true);
    }

    private void closeResource(Jedis jedis, boolean broken) {
        if (jedis != null) {
            if (broken) {
                this.pool.returnBrokenResource(jedis);
            } else {
                this.pool.returnResource(jedis);
            }
        }

    }

    public boolean exists(final String key) {
        return (Boolean)this.execute(new RedisCached.JedisAction<Boolean>() {
            public Boolean action(Jedis jedis) {
                return jedis.exists(key);
            }
        });
    }

    public String type(final String key) {
        return (String)this.execute(new RedisCached.JedisAction<String>() {
            public String action(Jedis jedis) {
                return jedis.type(key);
            }
        });
    }

    public long delete(final String... keys) {
        return (Long)this.execute(new RedisCached.JedisAction<Long>() {
            public Long action(Jedis jedis) {
                return jedis.del(keys);
            }
        });
    }

    public long ttl(final String key) {
        return (Long)this.execute(new RedisCached.JedisAction<Long>() {
            public Long action(Jedis jedis) {
                return jedis.ttl(key);
            }
        });
    }

    public Set<String> keys(final String pattern) {
        return (Set)this.execute(new RedisCached.JedisAction<Set<String>>() {
            public Set<String> action(Jedis jedis) {
                return jedis.keys(pattern);
            }
        });
    }

    public long expire(final String key, final int expire) {
        return (Long)this.execute(new RedisCached.JedisAction<Long>() {
            public Long action(Jedis jedis) {
                return jedis.expire(key, expire);
            }
        });
    }

    public long expireAt(final String key, final long unixTime) {
        return (Long)this.execute(new RedisCached.JedisAction<Long>() {
            public Long action(Jedis jedis) {
                return jedis.expireAt(key, unixTime);
            }
        });
    }

    public boolean hexists(final String key, final String field) {
        return (Boolean)this.execute(new RedisCached.JedisAction<Boolean>() {
            public Boolean action(Jedis jedis) {
                return jedis.hexists(key, field);
            }
        });
    }

    public String hget(final String key, final String field) {
        return (String)this.execute(new RedisCached.JedisAction<String>() {
            public String action(Jedis jedis) {
                return jedis.hget(key, field);
            }
        });
    }

    public Map<String, String> hgetAll(final String key) {
        return (Map)this.execute(new RedisCached.JedisAction<Map<String, String>>() {
            public Map<String, String> action(Jedis jedis) {
                return jedis.hgetAll(key);
            }
        });
    }

    public long hset(final String key, final String field, final String value) {
        return (Long)this.execute(new RedisCached.JedisAction<Long>() {
            public Long action(Jedis jedis) {
                return jedis.hset(key, field, value);
            }
        });
    }

    public long hincrBy(final String key, final String field, final long val) {
        return (Long)this.execute(new RedisCached.JedisAction<Long>() {
            public Long action(Jedis jedis) {
                return jedis.hincrBy(key, field, val);
            }
        });
    }

    public List<String> hmget(final String key, final String... fields) {
        return (List)this.execute(new RedisCached.JedisAction<List<String>>() {
            public List<String> action(Jedis jedis) {
                return jedis.hmget(key, fields);
            }
        });
    }

    public String hmset(final String key, final Map<String, String> paramMap) {
        return (String)this.execute(new RedisCached.JedisAction<String>() {
            public String action(Jedis jedis) {
                return jedis.hmset(key, paramMap);
            }
        });
    }

    public long hdels(final String key, final String... fields) {
        return (Long)this.execute(new RedisCached.JedisAction<Long>() {
            public Long action(Jedis jedis) {
                return jedis.hdel(key, fields);
            }
        });
    }

    public long sadd(final String key, final String... members) {
        return (Long)this.execute(new RedisCached.JedisAction<Long>() {
            public Long action(Jedis jedis) {
                return jedis.sadd(key, members);
            }
        });
    }

    public long scard(final String key) {
        return (Long)this.execute(new RedisCached.JedisAction<Long>() {
            public Long action(Jedis jedis) {
                return jedis.scard(key);
            }
        });
    }

    public String spop(final String key) {
        return (String)this.execute(new RedisCached.JedisAction<String>() {
            public String action(Jedis jedis) {
                return jedis.spop(key);
            }
        });
    }

    public Set<String> smembers(String key) {
        Jedis jedis = null;

        Set var5;
        try {
            jedis = this.pool.getResource();
            var5 = jedis.smembers(key);
        } catch (Exception var8) {
            throw new RuntimeException();
        } finally {
            this.pool.returnResource(jedis);
        }

        return var5;
    }

    public String get(final String key) {
        return (String)this.execute(new RedisCached.JedisAction<String>() {
            public String action(Jedis jedis) {
                return jedis.get(key);
            }
        });
    }

    public String set(final String key, final String val) {
        return (String)this.execute(new RedisCached.JedisAction<String>() {
            public String action(Jedis jedis) {
                return jedis.set(key, val);
            }
        });
    }

    public <T> String setT(final String key, final T t) {
        return (String)this.execute(new RedisCached.JedisAction<String>() {
            public String action(Jedis jedis) {
                return jedis.set(key.getBytes(), SerializeUtil.serialize(t));
            }
        });
    }

//    public <T> T hsetT(final String key, final String field, final T t) {
//        return this.execute(new RedisCached.JedisAction<T>() {
//            public T action(Jedis jedis) {
//                return jedis.hset(key.getBytes(), field.getBytes(), SerializeUtil.serialize(t));
//            }
//        });
//    }

//    public <T> T getT(final String key, Class<T> t) {
//        return this.execute(new RedisCached.JedisAction<T>() {
//            public T action(Jedis jedis) {
//                byte[] bytes = jedis.get(key.getBytes());
//                T t = SerializeUtil.unserialize(bytes);
//                return t;
//            }
//        });
//    }

    public Object incr(final String key) {
        return this.execute(new RedisCached.JedisAction() {
            public Long action(Jedis jedis) {
                return jedis.incr(key);
            }
        });
    }

    public Object getIncr(final String key) {
        return this.execute(new RedisCached.JedisAction() {
            public String action(Jedis jedis) {
                return jedis.get(key);
            }
        });
    }

    public Object getKeys(final String keys) {
        return this.execute(new RedisCached.JedisAction() {
            public Set<String> action(Jedis jedis) {
                return jedis.keys(keys);
            }
        });
    }

    interface JedisAction<T> {
        T action(Jedis var1);
    }

    interface JedisActionNoResult {
        void action(Jedis var1);
    }
}
