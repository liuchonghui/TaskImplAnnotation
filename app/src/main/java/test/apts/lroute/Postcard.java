package test.apts.lroute;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

public class Postcard {
    private String path;
    private String packageName;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void navigation() {
        if (!TextUtils.isEmpty(path)) {
            int pos = path.lastIndexOf(".");
            packageName = path.substring(0, pos);
            Intent intent = new Intent();
            ComponentName componentName = new ComponentName(packageName, path);
            intent.setComponent(componentName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            LRouter.getContext().startActivity(intent);
        } else {
            return;
        }
    }
}
