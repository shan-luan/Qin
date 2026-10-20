package qin

import qin.BasicLang.*

/**just uninhabited,BUT STILL >: [[Nothing]],NOT SAME TO [[Nothing]](Although they have same meaning)
 * And can normally participate in type partial ordering.*/
type Chinglish = En.type & Zh.type

given chEnZh[L <: Chinglish]: Target[L] = new Target[L] {}

class QinTest extends munit.FunSuite {
  test("str resolves currentLang") {
    Qin.currentLang = De
    val l = "Hello".in(En) | "Hallo".in(De)
    assertEquals(l.str(), "Hallo")
  }

  test("str falls back to En") {
    Qin.currentLang = Zh
    val l = "Hello".in(En) | "Hallo".in(De)
    assertEquals(l.str(), "Hello")
  }

  test("str falls back to first when missing") {
    Qin.currentLang = En
    val l = "Hallo".in(De)
    assertEquals(l.str(), "Hallo")
  }

  test("str per declared targets fully translated") {
    Qin.currentLang = Zh
    val l = "Hello".in(En) | "你好".in(Zh)
    assertEquals(l.str(), "你好")
  }

  test("str per declared targets partially translated") {
    Qin.currentLang = En
    val l = "Hello".in(En) | "Hola".in(Es)
    assertEquals(l.str(), "Hello")
  }

  test("str with superset of declared targets") {
    Qin.currentLang = Zh
    val l = "Hello".in(En) |
      "Hallo".in(De) |
      "Hola".in(Es) |
      "Bonjour".in(Fr) |
      "こんにちは".in(Ja) |
      "你好".in(Zh)
    assertEquals(l.str(), "你好")
  }
}
