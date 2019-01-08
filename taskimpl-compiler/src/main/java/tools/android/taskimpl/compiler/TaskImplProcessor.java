package tools.android.taskimpl.compiler;

import java.io.IOException;
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

        // for each javax.lang.model.element.Element annotated with the CustomAnnotation
        for (Element element : roundEnvironment.getElementsAnnotatedWith(CreateService.class)) {
            PackageElement packageElement = processingEnv.getElementUtils().getPackageOf(element);
            String packageName = packageElement.getQualifiedName().toString();

            StringBuilder builder = new StringBuilder()
                    .append("package ").append(packageName).append(";\n\n")
                    .append("public class GeneratedClass {\n\n") // open class
                    .append("\tpublic String getMessage() {\n") // open method
                    .append("\t\treturn \"");

            // this is appending to the return statement
            builder.append(packageName).append(" says hello!\\n");

            builder.append("\";\n") // end return
                    .append("\t}\n") // close method
                    .append("}\n"); // close class


            try { // write the file
                JavaFileObject source = processingEnv.getFiler().createSourceFile(packageName + ".GeneratedClass");


                Writer writer = source.openWriter();
                writer.write(builder.toString());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                // Note: calling e.printStackTrace() will print IO errors
                // that occur from the file already existing after its first run, this is normal
            }
        }






        return true;
    }
}
