package com.revature.yolp;

import com.revature.yolp.utils.ConnectionFactory;
import com.revature.yolp.utils.Router;
import io.javalin.Javalin;

/* purpose of this class is to start our application */
public class MainDriver {
    public static void main(String[] args) {
        Javalin app = Javalin.create(c -> {
            c.contextPath = "/yolp";
        }).start(8080);

        Router.router(app);
    }
}
