package com.aztlansoft.koua;

import android.app.ListActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.aztlansoft.koua.dao.DaoMaster;
import com.aztlansoft.koua.dao.DaoSession;
import com.aztlansoft.koua.dao.KouaMessageDao;
import com.aztlansoft.koua.dao.DaoMaster.DevOpenHelper;
import com.aztlansoft.koua.model.KouaMessage;
import java.util.List;

public class Records extends ListActivity
{
  private SQLiteDatabase db;
  private DaoMaster daoMaster;
  private DaoSession daoSession;
  private KouaMessageDao kouaMessageDao;
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    // setContentView(R.layout.record);

    DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "koua-db", null);
    db = helper.getWritableDatabase();
    daoMaster = new DaoMaster(db);
    daoSession = daoMaster.newSession();
    kouaMessageDao = daoSession.getKouaMessageDao();

    List<KouaMessage> list = kouaMessageDao.loadAll();
    RecordsArrayAdapter adapter = new RecordsArrayAdapter(this, list);
    setListAdapter(adapter);
  }
}
