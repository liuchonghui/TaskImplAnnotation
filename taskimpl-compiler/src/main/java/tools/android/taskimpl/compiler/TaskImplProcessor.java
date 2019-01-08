package tools.android.taskimpl.compiler;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import tools.android.taskimpl.annotation.CreateService;

@SupportedAnnotationTypes("tools.android.taskimpl.annotation.CreateService")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class TaskImplPorcessor extends AbstractProcessor {

    @Override
    public boolean process(set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
