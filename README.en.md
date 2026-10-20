[中文](README.md) | **English**

![logo](img.png)

> One script for all under heaven.

QIN is a minimal (only 70 lines) internationalization (i18n) library for Scala 3. It lets you define text with support for multiple languages and pick the right one at runtime based on the current language.
It also lets you declare which languages you expect to be translated. If any are missing, you get a warning at compile time.

## Quick Start

Copy `src/main/scala/qin` into your project.

### Example

```scala
import qin.*
import qin.BasicLang.*

// 1. Declare the languages you expect to be translated.
// BasicLang ships with a few built-in language enums; you can extend Lang yourself to support more.
given chEnZh[L <: En.type & Zh.type]: Target[L] = new Target[L] {}

// 2. Combine localized texts
val greeting =
  "Hello".in(En) |
    "你好".in(Zh)

val wipText =
  "World".in(En)

// 3. Retrieve a value
Qin.currentLang = Zh // set the current language
greeting.str() // "你好"
wipText.str() // "World", with a compile-time warning.
```

## Caveats

- Not thread-safe.
- Only use the `in` extension method with string literals. Internally it caches `Localized` objects keyed by the string and holds strong references to both keys and values. Dynamically generated strings will cause memory leaks.

## Misc
- See the header of `src/main/scala/qin` for the license.
- The name means "Qin", an ancient Chinese state that existed from 221–206 BC, famous for its policy of unifying the language.
- The name can also be read as **Q**uick **I**18**N**.
