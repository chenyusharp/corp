package com.xiazhenyu.diff;

import com.alibaba.fastjson.JSON;
import com.github.difflib.DiffUtils;
import com.github.difflib.algorithm.DiffAlgorithmListener;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;
import com.github.difflib.text.DiffRowGenerator;
import java.util.function.BiPredicate;
import lombok.Builder;
import lombok.Data;

/**
 * Date: 2023/4/24
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Diff {


    public static void main(String[] args) {
        DiffRowGenerator generator= DiffRowGenerator.create()
                .showInlineDiffs(true)
                .mergeOriginalRevised(true)
                .inlineDiffByWord(true)
                .oldTag(f->"~")
                .newTag(f->"**")
                .build();

        Student studentA= Student.builder()
                .name("xiazhenyu")
                .age(16)
                .address("hangzhou")
                .build();
        System.out.println(JSON.toJSONString(studentA));
        Student studentB= Student.builder()
                .name("xiazhenyuqq")
                .age(16)
                .address("wuhan")
                .build();
        System.out.println(JSON.toJSONString(studentB));
//        List<DiffRow> rows=generator.generateDiffRows(Arrays.asList("This is a test senctence."),Arrays.asList("This is a test for diffutils."));
//        System.out.println(rows.get(0).getOldLine());

        DiffAlgorithmListener diffAlgorithmListener= new DiffAlgorithmListener() {
            @Override
            public void diffStart() {

            }

            @Override
            public void diffStep(int value, int max) {

            }

            @Override
            public void diffEnd() {
                //执行保存操作
//                System.out.println("save");
            }
        };
        final Patch<String> diffInline = DiffUtils.diffInline(JSON.toJSONString(studentA), JSON.toJSONString(studentA));
//        final Patch<String> stringPatch = DiffUtils.diff(JSON.toJSONString(studentA), JSON.toJSONString(studentB),diffAlgorithmListener);
//        System.out.println(stringPatch);

        for (AbstractDelta<String> abstractDelta : diffInline.getDeltas()) {
            System.out.print(abstractDelta.getSource().toString());
            System.out.println(abstractDelta.getTarget().toString());
        }


    }


    @Data
    @Builder
    public static class  Student{
        String name;
        int age;
        String address;

    }

}