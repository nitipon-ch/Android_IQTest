package buu.informatics.s59160575.iqtest

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import buu.informatics.s59160575.iqtest.database.GameScore

fun formatScore(scores: List<GameScore>): Spanned {
    val sb = StringBuilder()
    var num = 1
    sb.apply {
        scores.forEach {
            if(num == 1){
                append("${"  "} \t${"Name"} \t\t\t\t\t\t\t\t\t\t\t\t${"IQScore"}<br>")
                append("<br>")
                append("${num} \t${it.username} \t\t\t\t\t\t\t\t\t\t${it.score}<br>")
                append("<br>")
            }else{
                append("${num} \t${it.username} \t\t\t\t\t\t\t\t\t\t${it.score}<br>")
                append("<br>")
            }

            num++
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}