FROM java:8
EXPOSE 9677

VOLUME /tmp
ADD coupon-management.jar  /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]