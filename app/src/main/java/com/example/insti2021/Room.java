package com.example.insti2021;

import android.content.Context;

import com.example.insti2021.dao.DataBaseRoom;
import com.example.insti2021.dao.ProductRoomDao;

public class Room {
    public  static ProductRoomDao dao ;

    public static ProductRoomDao getDao(Context context) {
        dao= DataBaseRoom.getInstance(context).productRoomDao();
        return dao;
    }
}
