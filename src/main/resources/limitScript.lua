-- 限流 key, lua下标从 1 开始
local key = KEYS[1]
-- 限流限流时间范围和限流次数
local limit = tonumber(ARGV[1])
local perSeconds = tonumber(ARGV[2])
-- 获取当前流量大小
local currentLimit = tonumber(redis.call('get', key) or 0)

-- 判断限流
if currentLimit + 1 > limit then
    -- 达到限流大小 返回0
    return 0;
else
    -- 没有达到阈值 value + 1
    redis.call('INCRBY', key, 1)
    -- EXPIRE的单位是秒
    redis.call('EXPIRE', key, perSeconds)
    -- 返回当前流量大小
    return currentLimit + 1
end
