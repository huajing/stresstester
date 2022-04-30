# Java实现压测的工具
参考
```xml
<dependency>
    <groupId>com.taobao</groupId>
    <artifactId>stresstester</artifactId>
    <version>1.0</version>
</dependency>
```
只是一个基础的功能，使用方法如
```java
public static void main(String[] args) {
        StressTestUtils.test(10, 100, ()->{
            //String s = HttpUtil.get("url here");
            return null;
        });
    }
```
虽然有loadrunner，jmeter这些优秀的测试工具，但作为一个java程序员
能用java去实现，感觉控制感更强一些，记得最开始用nginx作为反向代理服务器时
只能用nginx的语法写，自定义化不够强，虽然它支持扩展，但不能用java语言，
后来使用微服务开发用zuul,gateway这些网关服务后，感觉使用起来很棒啊，就把nginx
去掉了

# 构造自动化测试？？