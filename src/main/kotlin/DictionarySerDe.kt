import sun.security.pkcs.PKCS8Key.parseKey

/**
 * Created by rratti on 2/14/17.
 */


fun serialize(v: Map<String, Any>): String {
  val sb = StringBuilder()

  fun serialize(c: Any?) {
    when (c) {
      is Map<*, *> -> {
        sb.append('{')
        c.forEach { k, v ->
          sb.append(k).append(':')
          serialize(v)
          sb.append(',')
        }
        sb.replace(sb.length - 1, sb.length, "}")
      }
      else -> sb.append(c.toString())
    }
  }

  serialize(v)
  return sb.toString()
}

fun deserialize(s: String): Map<String, Any> {
  fun deserKey(i: Int): Pair<String, Int> {
    val j = s.indexOf(':', i)
    return s.substring(i..j-1) to j
  }

  fun deserVal(idx: Int): Pair<Any, Int> {
    if (s.get(idx) == '{')  {
      val m = mutableMapOf<String, Any>()
      var j = idx
      while (s.get(j) != '}') {
        val (k, kj) = deserKey(j + 1)
        val (v, vj) = deserVal(kj + 1)
        m.put(k, v)
        j = vj
      }
      return m to j
    } else {
      var valEndIdx = s.indexOf(',', idx)
      val j = if (valEndIdx == -1) s.indexOf('}', idx) else valEndIdx
      val v = s.substring(idx, j)
      return v to j
    }
  }

  val (r, i) = deserVal(0)
  if (r is Map<*, *>) {
    return r as Map<String, Any>
  } else {
    throw RuntimeException("Failed to deserialize string: $s")
  }
}

fun main(args: Array<String>) {
  println(deserialize(serialize(mapOf("x" to mapOf("y" to "z")))))
}
