package junit;

import com.xiazhenyu.annotaion.EpScheduled;
import com.xiazhenyu.annotaion.Scheduled;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * Date: 2023/8/13
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class EpScheduledTest {


    @Test
    public void findMethodAnnotationOnLeaf() throws Exception {
        Method m = Scheduled.class.getMethod("testScheduled");
        final List<EpScheduled> annotations = new ArrayList<>(AnnotationUtils.getRepeatableAnnotations(m, EpScheduled.class));

        System.out.println(annotations.get(0).name());
        System.out.println(annotations.get(1).name());
    }


}