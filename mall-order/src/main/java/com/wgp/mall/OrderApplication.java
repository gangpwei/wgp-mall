/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wgp.mall;

import java.util.concurrent.CountDownLatch;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wgp.mall.order.mapper")
@EnableDubbo(scanBasePackages = {"com.wgp.mall.order.service"})
public class OrderApplication {
    public static void main(String[] args) throws Exception {

        //ApplicationHome home = new ApplicationHome(OrderApplication.class);
        //File jarFile = home.getSource();
        //String dirPath = jarFile.getParentFile().toString();
        //String filePath = dirPath + File.separator + ".dubbo";
        //System.out.println(filePath);
        //System.setProperty("dubbo.meta.cache.filePath", filePath);
        //System.setProperty("dubbo.mapping.cache.filePath",filePath);

        SpringApplication.run(OrderApplication.class, args);
        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
