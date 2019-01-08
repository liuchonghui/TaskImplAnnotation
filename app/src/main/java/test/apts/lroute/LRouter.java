package test.apts.lroute;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;

public class LRouter {


    private static LRouter lRouter;

    public static Context getContext() {
        return context;
    }



    private static Context context;

    private LRouter() {

    }

    public static void init(Application application) {
        context = application;
    }

    public static LRouter getInstance() {
        if (lRouter == null) {
            lRouter = new LRouter();
        }

        return lRouter;
    }

    public Postcard build(String path) {
        LRouterMap lRouterMap = new LRouterMap();
        HashMap<String, String> maps = lRouterMap.getMaps();
        Postcard postcard = new Postcard();
        String classNmae = maps.get(path);
        postcard.setPath(classNmae);
        return postcard;
    }
}
