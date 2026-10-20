**中文** | [English](README.en.md)

![logo](img.png)

>书同文。

QIN 是一个极简(仅70行)的 Scala 3 国际化（i18n）库。它允许你定义有多种语言支持的文本，运行时根据当前语言取出合适的。
此外，还支持定义预计翻译的语言种类。如果缺失，会在编译期间警告。

## 快速开始

把 `src/main/scala/qin` 复制进你的项目。

### 示例

```scala
import qin.*
import qin.BasicLang.*

// 1.定义预计翻译的语言种类。
// 在BasicLang里内建了一些语言枚举，你可以自行继承Lang支持更多。
given chEnZh[L <: En.type & Zh.type]: Target[L] = new Target[L] {}

// 2. 拼接多语言文案
val greeting =
  "Hello".in(En) |
    "你好".in(Zh)

val wipText =
  "World".in(En)

// 3. 取值
Qin.currentLang = Zh // 设置当前使用的语言
greeting.str() // "你好"
wipText.str() // "World",并伴有编译期的警告。
```

## 注意事项

- 无线程安全。
- 请只对字符串字面量使用in扩展方法，其内部会以字符串为键缓存Localized对象并强引用键和值。对于动态生成的字符串会导致内存泄漏。

## 其余
- 许可证见`src/main/scala/qin`开头。
- 名字的意思是“秦”。存在于221–206BC的中国古国。其以统一语言的政策而闻名。
- 名字也可以理解为**Q**uick **I**18**N**。