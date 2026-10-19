package qin

import qin.BasicLang.*

import scala.collection.{immutable, mutable}
import scala.collection.mutable.{HashMap, Map}
import scala.reflect.Enum

trait Lang extends Enum {
  val localizedCache: mutable.Map[String, Localized[this.type]] = new mutable.HashMap()
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
  /**
   * If you define any more specific and "matching" Complete Given, this won’t be used here.
   */
  @deprecated("Translation missing. This will fall back to English or the first defined language at runtime.")
  given incomplete[L <: Lang]: Complete[L] = new Complete[L] {}
}

case class Localized[L <: Lang](supported: immutable.Map[Lang, String]) {
  def |[L2 <: Lang](l: Localized[L2]): Localized[L & L2] = {
    Localized[L & L2](supported ++ l.supported)
  }

  def str()(using Complete[L]): String =
    supported.get(Qin.currentLang).orElse(supported.get(En)).getOrElse(supported.head._2)
}

extension (t: String)
  def in(l: Lang): Localized[l.type] = l.localizedCache.getOrElseUpdate(t,Localized[l.type](immutable.Map(l -> t)))

object Qin {
  var currentLang: Lang = En
}
