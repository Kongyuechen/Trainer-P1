package com.revature.yolp;

import com.revature.yolp.utils.ConnectionFactory;
import com.revature.yolp.utils.Router;
import io.javalin.Javalin;

/* our driver class (start java application) */
public class Main {
    public static void main(String[] args) {
        /* Our api will be hosted on port 8080 */
        Javalin app = Javalin.create(c -> {
            c.contextPath = "/yolp";
        }).start(8080);

        /* this class will handle all of our routing aka path mappings */
        Router.router(app);
    }
}
