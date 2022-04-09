package com.phoenix.core.http;

public interface IRateLimiting {
    int getRateLimitQuota();

    int getRateLimitRemaining();

    int getRateLimitReset();
}
