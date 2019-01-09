package tools.android.taskimpl.compiler;

import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

import tools.android.taskimpl.annotation.CreateService;

@SupportedAnnotationTypes("tools.android.taskimpl.annotation.CreateService")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class TaskImplProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        for (Element element : roundEnvironment.getElementsAnnotatedWith(CreateService.class)) {
            PackageElement packageElement = processingEnv.getElementUtils().getPackageOf(element);
            CreateService annotation = element.getAnnotation(CreateService.class);

            String PACKAGENAME = packageElement.getQualifiedName().toString();
            String CLASS_TASKIMPL = annotation.taskimpl();
            String TAG = annotation.tag();

            StringBuilder builder = new StringBuilder()
                    .append("package ").append(PACKAGENAME).append(";\n\n")
                    .append("import android.app.IntentService;\n")
                    .append("import android.compact.impl.TaskCallback;\n")
                    .append("import android.compact.impl.TaskImpl;\n")
                    .append("import android.compact.impl.TaskPayload;\n")
                    .append("import android.content.Intent;\n")
                    .append("import android.net.Uri;\n")
                    .append("import android.os.Bundle;\n\n")
                    .append("import com.google.gson.Gson;\n\n")
                    .append("public class TaskImplService extends IntentService {\n\n")
                    .append("\tpublic static String TAG = \"").append(TAG).append("\";\n\n")
                    .append("\tpublic TaskImplService() {\n")
                    .append("\t\tthis(\"\");\n")
                    .append("\t}\n\n")
                    .append("\tpublic TaskImplService(String name) {\n")
                    .append("\t\tsuper(name);\n")
                    .append("\t}\n\n")
                    .append("\tprotected TaskImpl createTask() {\n")
                    .append("\t\treturn new ").append(CLASS_TASKIMPL).append("(getApplicationContext());\n")
                    .append("\t}\n\n")
                    .append("\t@Override\n")
                    .append("\tpublic void onHandleIntent(Intent intent) {\n")
                    .append("\t\tBundle extra = intent.getExtras();\n")
                    .append("\t\tif (extra == null) return;\n")
                    .append("\t\tTaskPayload payload = null;\n")
                    .append("\t\tObject obj = extra.get(\"taskpayload\");\n")
                    .append("\t\tif (obj instanceof TaskPayload) payload = (TaskPayload) obj;\n")
                    .append("\t\tif (payload == null) return;\n")
                    .append("\t\tif (payload.identify == null || payload.auth == null) return;\n")
                    .append("\t\tcreateTask().run(getApplicationContext(), payload, new TaskCallback() {\n")
                    .append("\t\t\t@Override\n")
                    .append("\t\t\tpublic void onResult(TaskPayload taskPayload) {\n")
                    .append("\t\t\t\tBundle extras = new Bundle();\n")
                    .append("\t\t\t\textras.putString(\"action\", \"setTaskPayload\");\n")
                    .append("\t\t\t\textras.putString(\"extra_json\", new Gson().toJson(taskPayload));\n")
                    .append("\t\t\t\tUri uri = Uri.parse(\"content://\" + taskPayload.auth);\n")
                    .append("\t\t\t\ttry {\n")
                    .append("\t\t\t\t\tgetApplicationContext().getContentResolver().call(uri, \"sendMessagesToHost\", null, extras);\n")
                    .append("\t\t\t\t} catch (Throwable t) {\n")
                    .append("\t\t\t\t\tt.printStackTrace();\n")
                    .append("\t\t\t\t}\n")
                    .append("\t\t\t}\n")
                    .append("\t\t});\n")
                    .append("\t}\n")
                    .append("}\n");


            try {
                JavaFileObject source = processingEnv.getFiler().createSourceFile(PACKAGENAME + ".TaskImplService");
                Writer writer = source.openWriter();
                writer.write(builder.toString());
                writer.flush();
                writer.close();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return true;
    }
}
