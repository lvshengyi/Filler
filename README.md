# Filler
用于类对象填充的轻量级工具
比如有这样的结构:
``` 
PojoA{
  PojoB{
    PojoC{
      String A;
    }
    
    Integer A;
  }
}
```
当PojoB为null时，所有基于PojoB的调用均将抛出NPE，通过此工具，则可以将所有非基础数据类型以外的对象均初始化，以此形成完整的结构。

# Change Log
V1.0
完成了初始版本
