import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LvShengyI
 * @description Filler接口默认实现
 */
public class DefaultFiller implements Filler {

    /**
     * 对外屏蔽构造函数
     */
    private DefaultFiller(){}

    /**
     * 建造方法
     *
     * @return
     */
    public static DefaultFiller build(){
        return new DefaultFiller();
    }

    /**
     * 储存java基础数据类型
     * 这些数据类型是不会被继续向下填充的
     */
    private static final List<String> BASE_DATA_TYPE_CLASS = new ArrayList<>();

    static {
        BASE_DATA_TYPE_CLASS.add("java.lang.Byte");
        BASE_DATA_TYPE_CLASS.add("java.lang.Short");
        BASE_DATA_TYPE_CLASS.add("java.lang.Integer");
        BASE_DATA_TYPE_CLASS.add("java.lang.Long");
        BASE_DATA_TYPE_CLASS.add("java.lang.Float");
        BASE_DATA_TYPE_CLASS.add("java.lang.Double");
        BASE_DATA_TYPE_CLASS.add("java.lang.Boolean");
        BASE_DATA_TYPE_CLASS.add("java.lang.Character");
        BASE_DATA_TYPE_CLASS.add("java.lang.String");
    }

    @Override
    public <T> T fill(T obj) {
        final Class rootClass = obj.getClass();

        if (isBaseDataType(rootClass)) {
            return obj;
        }

        fillField(obj);

        return obj;
    }

    /**
     * 判断一个类是否是基础数据类型
     *
     * @param clz
     * @return
     */
    private Boolean isBaseDataType(Class clz) {
        return BASE_DATA_TYPE_CLASS.contains(clz.getName());
    }

    private void fillField(Object obj) {
        final Class rootClass = obj.getClass();
        final List<Field> unBaseDataTypeFieldList = filterUnBaseDataTypeFieldList(rootClass);

        for (Field field : unBaseDataTypeFieldList) {
            try {
                final Class fieldClass = field.getType();
                final Object instance = fieldClass.newInstance();

                field.set(obj, instance);

                fillField(instance);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }

    /**
     * 筛选一个类中的非基础数据类库的字段
     *
     * @param clz
     * @return
     */
    private List<Field> filterUnBaseDataTypeFieldList(Class clz) {
        return Arrays.stream(clz.getDeclaredFields())
                .filter(field -> !BASE_DATA_TYPE_CLASS.contains(field.getType().getName()))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
    }
}
