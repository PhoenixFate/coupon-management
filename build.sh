#!/bin/bash -l
# stop container
docker stop coupon;
# delete container
docker rm coupon;
# delete image
docker rmi coupon_image;
# cd /root/project/coupon
cd /root/project/coupon
# 构建镜像 .为当前目录的dockerfile
docker build -t coupon_image .;
#
docker run -d --name coupon  \
        -p 9677:9677 \
        --restart=always \
        coupon_image;