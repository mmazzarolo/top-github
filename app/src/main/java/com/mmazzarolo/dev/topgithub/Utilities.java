package com.mmazzarolo.dev.topgithub;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.wnafee.vector.compat.ResourcesCompat;

import org.joda.time.DateTime;

/**
 * Created by Matteo on 01/09/2015.
 */
public class Utilities {

    public static boolean isConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static Drawable getDrawableFromLanguage(Context context, String language) {
        int drawableId;
        switch (language) {
            case "C":
                drawableId = R.drawable.vector_c;
                break;
            case "CoffeeScript":
                drawableId = R.drawable.vector_coffeescript;
                break;
            case "C++":
                drawableId = R.drawable.vector_cplusplus;
                break;
            case "C#":
                drawableId = R.drawable.vector_csharp;
                break;
            case "CSS":
                drawableId = R.drawable.vector_css;
                break;
            case "Erlang":
                drawableId = R.drawable.vector_erlang;
                break;
            case "Go":
                drawableId = R.drawable.vector_go;
                break;
            case "HTML":
                drawableId = R.drawable.vector_html;
                break;
            case "Java":
                drawableId = R.drawable.vector_java;
                break;
            case "JavaScript":
                drawableId = R.drawable.vector_javascript;
                break;
            case "PHP":
                drawableId = R.drawable.vector_php;
                break;
            case "Python":
                drawableId = R.drawable.vector_python;
                break;
            case "Ruby":
                drawableId = R.drawable.vector_ruby;
                break;
            default:
                drawableId = R.drawable.vector_generic;
                break;
        }
        return ResourcesCompat.getDrawable(context, drawableId);
    }

    public static String getCreatedDateFromPeriod(String period) {
        switch (period) {
            case "action_today":
                return new DateTime().minusDays(1).toString("yyyy-MM-dd");
            case "action_this_week":
                return new DateTime().minusDays(7).toString("yyyy-MM-dd");
            case "action_this_month":
                return new DateTime().minusMonths(1).toString("yyyy-MM-dd");
            case "action_this_year":
                return new DateTime().minusYears(1).toString("yyyy-MM-dd");
            default:
                return "";
        }
    }

    public static String getPeriodNameFromPeriod(String period) {
        switch (period) {
            case "action_today":
                return "today";
            case "action_this_week":
                return "last week";
            case "action_this_month":
                return "last month";
            case "action_this_year":
                return "last year";
            default:
                return "";
        }
    }
}
