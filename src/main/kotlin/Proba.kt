import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
   var s : String
   val p : Process
   try {
       p = Runtime.getRuntime().exec("whoami")
       val br = BufferedReader(
           InputStreamReader(p.inputStream)
       )

       println(br.readText())

       p.waitFor();
       System.out.println ("exit: " + p.exitValue());
       p.destroy();
   }
   catch (e : Exception){
       println(e.toString())
   }
}