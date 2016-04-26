package com.jiaxy.tgd;

import org.junit.Test;

import java.util.Date;

/**
 * Title: <br>
 * <p>
 * Description: <br>
 * </p>
 * <br>
 *
 * @author <a href=mailto:taobaorun@gmail.com>taobaorun</a>
 *         <br>
 * @since 2016/04/26 22:19
 */
public class RateLimiterTest {


    @Test
    public void testTBGetToken() throws Exception {
        RateLimiter limiter = RateLimiter.builder().
                withToekPerSecond(1).
                withType(RateLimiter.RateLimiterType.TB).
                build();
        for ( int i = 0 ;i < 10;i++){
            limiter.getToken(1);
            System.out.println(i+".================="+new Date());
        }
    }

    @Test
    public void testFFTBGetToken() throws Exception {
        RateLimiter limiter = RateLimiter.builder().
                withToekPerSecond(1).
                withType(RateLimiter.RateLimiterType.FFTB).
                build();
        for ( long i = 0 ;i < Long.MAX_VALUE;i++){
            try {
                limiter.getToken(1);
                System.out.println(i+".================="+new Date());
            } catch (Exception e){
                Thread.sleep(500);
//                e.printStackTrace()
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    public void testMultiFFBGetToken() throws Exception {
        final RateLimiter limiter = RateLimiter.builder().
                withToekPerSecond(1).
                withType(RateLimiter.RateLimiterType.FFTB).
                build();
        for ( int j = 0 ;j < 3;j++){
             new Thread(new Runnable() {
                public void run() {
                     for ( long i = 0 ;i < Long.MAX_VALUE;i++){
                        try {
                            limiter.getToken(1);
                            System.out.println(i+".================="+new Date());
                        } catch (Exception e){
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e1) {
                            }
                        }
                    }
                }
            }).start();
        }
    }
}
