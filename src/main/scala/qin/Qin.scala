package qin

import qin.BasicLang.*

import scala.reflect.Enum

trait Lang extends Enum {
}

/**
 * See [[https://w3techs.com/technologies/overview/content_language]].
 *
 * Top 5.
 *
 * I added Chinese because I am ;)
 */
enum BasicLang extends Lang {
  case De //German
  case En //English
  case Es //Spanish
  case Fr //French
  case Ja //Japanese
  case Zh //Chinese
}

trait Complete[L <: Lang]

object Complete {
  @deprecated("Translation missing. This will fall back to English or the first defined language at runtime.")
  given incomplete[L <: Lang]: Complete[L] = new Complete[L] {}
}

case class Localized[L <: Lang](supported: Map[Lang, String]) {
  def |[L2 <: Lang](l: Localized[L2]): Localized[L & L2] = {
    Localized[L & L2](supported ++ l.supported)
  }

  def str()(using Complete[L]): String =
    supported.get(Qin.currentLang).orElse(supported.get(En)).getOrElse(supported.head._2)
}

extension (t: String)
  def in(l: Lang): Localized[l.type] = Localized[l.type](Map(l -> t))

object Qin {
  var currentLang: Lang = En
}
