package com.w3bshark.tictacnope;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.widget.Toast;

/**
 * Created by tmccraw on 2/1/2015.
 */
public class Util {

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

//    public static boolean showNextTrashTalkToast(Context context, int turn) {
//        if (context == null || turn == 0) {
//            return false;
//        }
//
//        String msg;
//        switch (turn) {
//            case 1:
//                msg = context.getString(R.string.trash_talk_1);
//                break;
//            case 3:
//                msg = context.getString(R.string.trash_talk_2);
//                break;
//            case 5:
//                msg = context.getString(R.string.trash_talk_3);
//                break;
//            case 7:
//                msg = context.getString(R.string.trash_talk_4);
//                break;
//            case 9:
//                msg = context.getString(R.string.trash_talk_5);
//                break;
//            case 10:
//                msg = context.getString(R.string.trash_talk_6);
//                break;
//            case 11:
//                msg = context.getString(R.string.trash_talk_7);
//                break;
//            default:
//                msg = context.getString(R.string.trash_talk_1);
//                break;
//        }
//        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
//        return true;
//    }
}
