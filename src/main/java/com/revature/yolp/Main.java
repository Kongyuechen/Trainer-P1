package com.revature.yolp;

import com.revature.yolp.utils.ConnectionFactory;
import com.revature.yolp.utils.Router;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(c -> {
            c.contextPath = "/yolp";
        }).start(8080);

        Router.router(app);
    }
}
