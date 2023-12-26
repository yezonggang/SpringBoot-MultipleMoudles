package com.completablefuture._03_CompletableFuture_start;


import com.completablefuture.SmallTool;import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class _01_supplyAsync {
    static final Logger logger = LoggerFactory.getLogger(_01_supplyAsync.class);
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 + 一碗米饭");

        logger.info("xxxx");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMillis(200);
            SmallTool.printTimeAndThread("厨师打饭");
            SmallTool.sleepMillis(10000);
            return "番茄炒蛋 + 米饭 做好了";
        }).exceptionally(Throwable::getMessage);

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s ,小白开吃", cf1.join()));
    }
}
