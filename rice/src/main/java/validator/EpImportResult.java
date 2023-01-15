package validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

/**
 * Date: 2023/1/14
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class EpImportResult<T> {

    private List<T> validBeanList = new ArrayList<>();


    private Map<Integer, ImportRowErrorVO> errorVOMap = new LinkedHashMap<>();


    private Map<Integer, T> hyperLinkBeanMap = new LinkedHashMap<>(10000);


    public boolean isPassed() {
        return errorVOMap.isEmpty();
    }


    public List<T> getValidBeanList() {
        return validBeanList;
    }

    public void setValidBeanList(List<T> validBeanList) {
        this.validBeanList = validBeanList;
    }


    public String getErrorMsg() {
        if (!CollectionUtils.isEmpty(errorVOMap.keySet())) {
            Collection<ImportRowErrorVO> values = errorVOMap.values();
            ArrayList<String> collect = values.stream().map(ImportRowErrorVO::getErrorMsg)
                    .collect(Collectors.toCollection(ArrayList::new));
            return collect.toString();
        } else {
            return null;
        }
    }

    public Map<Integer, ImportRowErrorVO> getErrorVOMap() {
        return errorVOMap;
    }

    public Map<Integer, T> getHyperLinkBeanMap() {
        return hyperLinkBeanMap;
    }

}